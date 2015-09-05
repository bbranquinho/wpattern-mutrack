'use strict';

var intervalTrack = 30 * 60 * 1000; // 30 minutes

var ignorePackageStatus = [1];

angular.module('mutrack')
  .factory('TrackSrv', function($http, ngNotify, REST_URL) {
    var url = REST_URL.PUBLIC_PATH + '/track';
    var trackFactory = {};

    trackFactory.getPackages = function() {
      return $http.get(url);
    };

    trackFactory.trackLastEvent = function(pack) {
      pack.lastStatusChecking = true;

      $http.get(url + '/lastevent/' + pack.code)
        .success(function(data) {
          pack.lastStatus = data.events[0].description;
          pack.type = data.events[0].type;
          pack.date = new Date(parseInt(data.events[0].date));
          pack.status = data.events[0].status;
          pack.lastStatusChecking = false;
        })
        .error(function(erro) {
          pack.lastStatusChecking = false;
          ngNotify.set('Erro ao buscar o status do pacote: ' + erro, 'error');
        });
    };

    trackFactory.selectPackagesToTrack = function(packages) {
      var packagesToVerify = [];

      for (var pack in packages) {
        if (ignorePackageStatus.indexOf(packages[pack].status) < 0) {
          packagesToVerify.push(packages[pack]);
        }
      }

      return packagesToVerify;
    };

    trackFactory.trackMultipleLastEvent = function(packages, callbackEvent) {
      var packageCodes = [];

      for (var pack in packages) {
        packages[pack].lastStatusChecking = true;
        packageCodes.push(packages[pack].code);
      }

      var requestParams = {
        method: 'POST',
        url: url + '/lastevent',
        headers: { 'Content-Type': 'application/json' },
        data: packageCodes
      };

      $http(requestParams)
        .success(function(data) {
          var isUpdated = false;

          for (var indexData in data) {
            for (var indexPackage in packages) {
              if (data[indexData].packageCode === packages[indexPackage].code) {
                isUpdated = true;
                packages[indexPackage].lastStatusChecking = false;
                packages[indexPackage].lastStatus = data[indexData].events[0].description;
                packages[indexPackage].date = new Date(parseInt(data[indexData].events[0].date));
                packages[indexPackage].status = data[indexData].events[0].status;
              }
            }
          }

          if (isUpdated) {
            ngNotify.set('Status dos pacotes atualizados com sucesso!', 'success');
          }

          callbackEvent();
        })
        .error(function(erro) {
          for (var indexPackage in packages) {
            packages[indexPackage].lastStatusChecking = false;
          }

          ngNotify.set('Erro ao atualizar os status dos pacotes! Erro: ' + erro, 'error');

          callbackEvent();
        });
    };

    return trackFactory;
});

angular.module('mutrack')
  .service('SchedulerTrackSrv', function($timeout, TrackSrv) {
    var schedulerFactory = {};
    var packages = {};
    var updateTime = {};

    // Manage the timeout.
    function countdown() {
      if (updateTime.value <= 0) {
        $timeout.cancel(schedulerFactory.timeout);

        TrackSrv.trackMultipleLastEvent(TrackSrv.selectPackagesToTrack(packages), schedulerFactory.reset);
      } else {
        updateTime.value--;

        schedulerFactory.timeout = $timeout(countdown, 1000);
      }
    }

    // Manage the scheduler.
    schedulerFactory.reset = function() {
      schedulerFactory.stop();

      updateTime.value = intervalTrack / 1000; // Convert milliseconds to seconds.
      countdown();
    };

    schedulerFactory.start = function() {
      schedulerFactory.stop();

      updateTime.value = 0; // Convert milliseconds to seconds.
      countdown();
    };

    schedulerFactory.stop = function() {
      updateTime.value = undefined;
      $timeout.cancel(schedulerFactory.timeout);
    };

    schedulerFactory.configScheduler = function(packs, uTime) {
      packages = packs;
      updateTime = uTime;
    };

    return schedulerFactory;
});
