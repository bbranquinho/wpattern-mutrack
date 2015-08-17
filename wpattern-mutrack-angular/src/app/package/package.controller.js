'use strict';

angular.module('mutrack')
  .controller('PackageCtrl', function($scope, $controller, PackageSrv) {
    $scope.packages = [];

    // Retrieve packages of the user.
    PackageSrv.retrievePackages($scope);

    // Methods to save, edit and delete a package.
    $scope.savePackage = function(pack) {
      PackageSrv.save($scope, pack);
    };

    $scope.deletePackage = function(pack) {
      PackageSrv.delete($scope, pack);
    };

    // Inheritance the track controller.
    $controller('TrackCtrl', {$scope: $scope});
});
