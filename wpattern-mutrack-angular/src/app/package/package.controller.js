'use strict';

angular.module('mutrack')
  .controller('PackageCtrl', function($scope, $controller, PackageSrv) {
    $scope.packages = [];

    // Retrieve packages of the user.
    PackageSrv.retrievePackages($scope);

    // Inheritance the track controller.
    $controller('TrackCtrl', {$scope: $scope});
});
