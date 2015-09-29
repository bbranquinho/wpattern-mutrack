'use strict';

angular.module('mutrack')
  .controller('LoginTemplateCtrl', function($scope, $location, LoginSrv, userDetails) {
    $scope.isAuthenticated = function() {
      return !(!userDetails.token || 0 === userDetails.token.length);
    };

    $scope.login = function() {
      $location.path('/login');
    };

    $scope.logout = function() {
      LoginSrv.logout();
    };
  });
