'use strict';

angular.module('mutrack')
  .service('configSrv', function($http, $location, ngNotify, REST_URL) {
    var url = REST_URL.PRIVATE_PATH + '/user/change/password';
    var configFactory = {};

    configFactory.changePassword = function(user) {
      var requestParams = {
        method: 'PUT',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: user
      };

      $http(requestParams)
        .success(function() {
          // Redirect to package view.
          $location.path('/logout');

          ngNotify.set('Senha alterada com sucesso.', 'success');
        })
        .error(function() {
          ngNotify.set('Erro alterar a senha.', 'error');
        });
    };

    return configFactory;
  });
