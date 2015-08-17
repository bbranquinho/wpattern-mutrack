'use strict';

angular.module('mutrack')
  .controller('UserCtrl', function ($scope, UserSrv) {
    $scope.users = {};

    UserSrv.findUsers($scope);
});
