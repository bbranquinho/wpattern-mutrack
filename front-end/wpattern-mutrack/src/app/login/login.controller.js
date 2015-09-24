'use strict';

angular.module('mutrack')
  .controller('LoginCtrl', function($scope, LoginSrv, userDetails) {
    userDetails.authorities = [];
    userDetails.token = '';

    $scope.login = function(email, password) {
      LoginSrv.login(email, password);
    };
});
