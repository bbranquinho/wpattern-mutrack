'use strict';

angular.module('mutrack')
  .controller('PackageCtrl', function($scope, $controller, PackageSrv) {
    $scope.packages = [];

    // Retrieve packages of the user.
    PackageSrv.retrievePackages($scope.packages);

    // Methods to save, edit and delete a package.
    $scope.savePackage = function(pack) {
      PackageSrv.save($scope.packages, pack);
    };

    $scope.deletePackage = function(pack) {
      PackageSrv.delete($scope.packages, pack);
    };

    $scope.updatePackage = function(pack, updatedPack) {
      PackageSrv.update(pack, updatedPack);
    }

    // Inheritance the track controller.
    $controller('TrackCtrl', {$scope: $scope});
});
