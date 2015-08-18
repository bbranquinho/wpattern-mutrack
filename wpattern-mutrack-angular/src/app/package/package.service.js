'use strict';

angular.module('mutrack')
  .factory('PackageSrv', function($http, TrackSrv, ngNotify, REST_URL) {
    var url = REST_URL.PRIVATE_PATH + '/package';
    var packageFactory = {};

    // A better/right way to pass 'packages' is use a promisse.
    packageFactory.retrievePackages = function(scope) {
      $http.get(url + '/user')
        .success(function(data) {
          scope.packages = data;
        })
        .error(function(error) {
          console.log(error);
        });
    };

    // A better/right way to pass 'packages' is use a promisse.
    packageFactory.save = function(scope, pack) {
      delete pack['enabledCode'];

      var requestParams = {
        method: 'POST',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: pack
      };

      $http(requestParams)
        .success(function(data) {
          data.code = data.code.toUpperCase();
          scope.packages.push(pack);
          ngNotify.set('Pacote \'' + pack.code + '\' salvo, buscando o último status!', 'success');
          TrackSrv.trackLastEvent(pack);
        })
        .error(function(erro) {
          ngNotify.set('Erro ao adicionar o novo pacote: ' + erro, 'error');
        });
    };

    // A better/right way to pass 'packages' is use a promisse.
    packageFactory.delete = function(scope, pack) {
      var requestParams = {
        method: 'DELETE',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: { 'id': pack.id }
      };

      $http(requestParams)
        .success(function(data) {
          var indexOf = scope.packages.indexOf(pack);
          scope.packages.splice(indexOf, 1);

          ngNotify.set('Pacote ' + pack.code + ' deletado com sucesso!', 'success');
        })
        .error(function(erro) {
          ngNotify.set('Erro ao deletar o pacote ' + pack.code + ': ' + erro, 'error');
        });
    };

    return packageFactory;
  });
