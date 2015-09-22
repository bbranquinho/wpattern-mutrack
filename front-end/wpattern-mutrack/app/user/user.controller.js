'use strict';

angular.module('mutrack')
  .controller('UserCtrl', function ($scope, UserSrv) {
    $scope.user = {};
    $scope.user.permission = { admin:false, user:false };

    $scope.users = {};
    $scope.showAddEditUser = false;

    $scope.save = function(user) {
      user.permissions = [];

      if (user.permission === undefined) {
        user.permission = {};
      }
      if (user.permission.admin) {
        user.permissions.push({ permission:'ADMIN' });
      }
      if (user.permission.user) {
        user.permissions.push({ permission:'USER' });
      }

      if (user.id === undefined) {
        UserSrv.addUser($scope.users, user, function() {
          $scope.hide();
        });
      } else {
        UserSrv.updateUser($scope.users, user, function() {
          $scope.hide();
        });
      }
    };

    $scope.update = function(user) {
      $scope.user.id = user.id;
      $scope.user.name = user.name;
      $scope.user.email = user.email;
      $scope.user.permission = user.permission;

      $scope.show();
    };

    $scope.delete = function(user) {
      UserSrv.deleteUser($scope.users, user);
    };

    $scope.show = function() {
      $scope.showAddEditUser = true;
    };

    $scope.hide = function() {
      $scope.showAddEditUser = false;
      $scope.user = {};
    };

    UserSrv.retrieveUsers($scope);
});
