'use strict';

angular.module('mutrack')
  .controller('LoginCtrl', function($scope, LoginSrv) {
    $scope.login = function(email, password) {
      LoginSrv.login(email, password);
    };
});
