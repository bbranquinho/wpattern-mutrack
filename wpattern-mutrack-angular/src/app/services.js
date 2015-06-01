'use strict';

var ignorePackageStatus = [1];

angular.module('mutrack')
  .factory('PackageSrv', function($http, ngNotify, REST_URL) {
    var url = REST_URL.ROOT + '/package';
    var dataFactory = {};

    dataFactory.getPackages = function() {
      return $http.get(url);
    };

    dataFactory.trackLastEvent = function(pack) {
      pack.lastStatusChecking = true;

      $http.get(url + '/tracker/lastevent/' + pack.code)
        .success(function(data) {
          pack.lastStatus = data.events[0].description;
          pack.date = new Date(parseInt(data.events[0].date));
          pack.status = data.events[0].status;
          pack.lastStatusChecking = false;
        })
        .error(function(erro) {
          pack.lastStatusChecking = false;
          ngNotify.set('Erro ao buscar o status do pacote: ' + erro, 'error');
        });
    };

    dataFactory.selectPackagesToTrack = function(packages) {
      var packagesToVerify = [];

      for (var pack in packages) {
        if (ignorePackageStatus.indexOf(packages[pack].status) < 0) {
          packagesToVerify.push(packages[pack]);
        }
      }

      return packagesToVerify;
    };

    dataFactory.trackMultipleLastEvent = function(packages) {
      if ((packages === null) || (packages.length <= 0)) {
        return;
      }

      var packageCodes = [];

      for (var pack in packages) {
        packages[pack].lastStatusChecking = true;
        packageCodes.push(packages[pack].code);
      }

      var requestParams = {
        method: 'POST',
        url: url + '/tracker/lastevent',
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
        })
        .error(function(erro) {
          for (var indexPackage in packages) {
            packages[indexPackage].lastStatusChecking = false;
          }
          ngNotify.set('Erro ao atualizar os status dos pacotes! Erro: ' + erro, 'error');
        });
    };

    return dataFactory;
});
