'use strict';
// More about modal: https://angular-ui.github.io/bootstrap/

angular.module('mutrack').controller('TrackModalCtrl', function ($scope, $modalInstance, pack) {

  if (pack === undefined) {
    $scope.package = { 'enabledCode':false };
  } else {
    $scope.package = {
      'enabledCode':true,
      'code':pack.code,
      'name':pack.name,
      'description':pack.description
    };
  }

  $scope.save = function () {
    $modalInstance.close($scope.package);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});
