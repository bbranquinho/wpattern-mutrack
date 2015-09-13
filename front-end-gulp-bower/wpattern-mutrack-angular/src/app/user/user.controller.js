'use strict';

angular.module('mutrack')
  .controller('UserCtrl', function ($scope, UserSrv, ngNotify) {
    $scope.user = {};
    $scope.users = {};
    $scope.showAddEditUser = false;

    $scope.save = function(user) {
      if (user.password === user.confirmPassword) {
        //UserSrv.addUser($scope.users, user);
        //UserSrv.updateUser(user);
      } else {
        ngNotify.set('Senha e confirmar senha não podem ser diferentes!', 'error');
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
