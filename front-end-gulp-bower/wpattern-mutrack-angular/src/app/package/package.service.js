'use strict';

angular.module('mutrack')
  .factory('PackageSrv', function($http, TrackSrv, ngNotify, REST_URL) {
    var url = REST_URL.PRIVATE_PATH + '/package';
    var packageFactory = {};

    packageFactory.retrievePackages = function(packages) {
      $http.get(url)
        .success(function(data) {
          packages.push.apply(packages, data);
        })
        .error(function() {
        });
    };

    packageFactory.update = function(pack, updatedPack) {
      delete updatedPack.enabledCode;
      updatedPack.id = pack.id;

      var requestParams = {
        method: 'PUT',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: updatedPack
      };

      $http(requestParams)
        .success(function() {
          pack.name = updatedPack.name;
          pack.description = updatedPack.description;

          ngNotify.set('Pacote \'' + updatedPack.code + '\' atualizado com sucesso!', 'success');
        })
        .error(function(erro) {
          ngNotify.set('Erro ao atualizar o pacote: ' + erro, 'error');
        });
    };

    packageFactory.save = function(packages, pack) {
      delete pack.enabledCode;

      var requestParams = {
        method: 'POST',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: pack
      };

      $http(requestParams)
        .success(function(newPack) {
          newPack.code = newPack.code.toUpperCase();
          packages.push(newPack);
          ngNotify.set('Pacote \'' + newPack.code + '\' salvo, buscando o último status!', 'success');
          TrackSrv.trackLastEvent(newPack);
        })
        .error(function(erro) {
          ngNotify.set('Erro ao adicionar o novo pacote: ' + erro, 'error');
        });
    };

    packageFactory.delete = function(packages, pack) {
      var requestParams = {
        method: 'DELETE',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: { 'id': pack.id }
      };

      $http(requestParams)
        .success(function() {
          packages.splice(packages.indexOf(pack), 1);

          ngNotify.set('Pacote ' + pack.code + ' deletado com sucesso!', 'success');
        })
        .error(function(erro) {
          ngNotify.set('Erro ao deletar o pacote ' + pack.code + ': ' + erro, 'error');
        });
    };

    return packageFactory;
  });
