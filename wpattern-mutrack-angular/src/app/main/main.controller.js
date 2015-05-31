'use strict';

angular.module('mutrack')
  .controller('MainCtrl', function ($scope, localStorageService) {
    var packagesInStore = localStorageService.get('packages');

    $scope.packages = packagesInStore || [];

    $scope.$watch('packages', function () {
      localStorageService.set('packages', $scope.packages);
    }, true);

    $scope.savePackage = function() {
      $scope.packages.push($scope.package);
      $scope.package = {};
    };

    $scope.deletePackage = function(pack) {
      var indexOf = $scope.packages.indexOf(pack);

      $scope.packages.splice(indexOf, 1);
    };
});
