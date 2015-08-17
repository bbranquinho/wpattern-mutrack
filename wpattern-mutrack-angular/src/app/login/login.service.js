'use strict';

angular.module('mutrack')
  .factory('LoginSrv', function($http, userDetails, REST_URL) {
    var loginUrl = REST_URL.PUBLIC_PATH + '/auth';
    var loginFactory = {};

    loginFactory.login = function(email, password) {
      var requestParams = {
        method: 'POST',
        url: loginUrl,
        headers: { 'Content-Type': 'application/json' },
        data: { email: email, password: password }
      };

      $http(requestParams)
        .success(function(data) {
          userDetails.token = data.token;
          userDetails.authorities = data.authorities;
        })
        .error(function(erro) {
          console.log(erro);
        });
    };

    return loginFactory;
  });
