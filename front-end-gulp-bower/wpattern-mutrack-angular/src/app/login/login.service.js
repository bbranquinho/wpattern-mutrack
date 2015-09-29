'use strict';

angular.module('mutrack')
  .factory('LoginSrv', function($http, $location, $cookies, $timeout, ngNotify, userDetails, REST_URL) {
    var loginUrl = REST_URL.PUBLIC_PATH + '/auth';
    var loginFactory = {};
    var timeoutSchedule;

    loginFactory.logout = function() {
      // Remove the token of the cookie.
      $cookies.remove('x-auth-token');

      // Execute the logout and redirect to homepage.
      userDetails.authorities = [];
      userDetails.token = '';

      $location.path('/home');

      if (timeoutSchedule !== undefined) {
        $timeout.cancel(timeoutSchedule);
        timeoutSchedule = undefined;
      }
    };

    loginFactory.login = function(email, password) {
      var requestParams = {
        method: 'POST',
        url: loginUrl,
        headers: { 'Content-Type': 'application/json' },
        data: { email: email, password: password }
      };

      $http(requestParams)
        .then(function(response) {
          if ((response.data.errorMessage !== undefined) && (response.data.errorMessage.trim().length > 0)) {
            ngNotify.set('Usuário e/ou senha inválido(s).', 'error');
          } else {
            userDetails.token = response.data.token;
            userDetails.authorities = response.data.authorities;

            // Set a time/date to expire the token.
            var timestamp = userDetails.token.split(':')[1];
            var expireDate = new Date(parseInt(timestamp));

            $cookies.putObject('x-auth-token', userDetails, {
              expires : expireDate
            });

            // Set a timeout to be called when the token expire.
            var currentDate = new Date();
            var timeToExpire = expireDate - currentDate;

            timeoutSchedule = $timeout(function() {
              loginFactory.logout();
              ngNotify.set('Sessão expirada, acesse novamente o sistema.', 'info');
            }, timeToExpire);

            ngNotify.set('Sucesso ao acessar o sistema.', 'info');
            
            // Redirect to package view.
            $location.path('/package');
          }
        });
    };

    return loginFactory;
  });
