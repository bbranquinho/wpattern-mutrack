'use strict';

var intervalTrack = 1 * 20 * 1000; // 15 minutes

var ignorePackageStatus = [1];

angular.module('mutrack')
  .factory('TrackSrv', function($http, ngNotify, REST_URL) {
    var url = REST_URL.PUBLIC_PATH + '/track';
    var trackFactory = {};

    trackFactory.intervalTrack = intervalTrack;

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

    trackFactory.trackMultipleLastEvent = function(packages, callbackTrigger) {
      if ((packages === null) || (packages.length <= 0)) {
        callbackTrigger();
        return;
      }

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

          callbackTrigger();
        })
        .error(function(erro) {
          for (var indexPackage in packages) {
            packages[indexPackage].lastStatusChecking = false;
          }

          callbackTrigger();

          ngNotify.set('Erro ao atualizar os status dos pacotes! Erro: ' + erro, 'error');
        });
    };

    return trackFactory;
});

angular.module('mutrack')
  .service('SchedulerTrackSrv', function($interval, TrackSrv) {
    var schedulerFactory = {};
    var promise;

    // start a process to track a list of packages.
    var trackPackages = function(packages, callbackTrigger) {
      var packagesToVerify = TrackSrv.selectPackagesToTrack(packages);

      TrackSrv.trackMultipleLastEvent(packagesToVerify, callbackTrigger);
    };

    // starts the interval
    schedulerFactory.start = function(packages, callbackTrigger) {
      // stops any running interval to avoid two intervals running at the same time
      //schedulerFactory.stop();

      promise = $interval(trackPackages(packages, callbackTrigger), intervalTrack);
    };

    // stops the interval
    schedulerFactory.stop = function() {
      $interval.cancel(promise);
    };

    return schedulerFactory;
});
