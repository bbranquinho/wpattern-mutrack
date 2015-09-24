'use strict';

angular.module('mutrack')
  .controller('ConfigCtrl', function($scope, configSrv) {
    $scope.changePassword = function(user) {
      configSrv.changePassword(user);
    };
});
