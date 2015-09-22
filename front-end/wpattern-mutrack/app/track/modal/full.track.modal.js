'use strict';
// More about modal: https://angular-ui.github.io/bootstrap/

angular.module('mutrack').controller('FullTrackModalCtrl', function ($scope, $http, $modalInstance, code, TrackModalSrv) {

  $scope.events = [];
  $scope.code = code;

  $scope.close = function () {
    $modalInstance.close();
  };

  TrackModalSrv.refreshEvents($scope, $http, code);
});
