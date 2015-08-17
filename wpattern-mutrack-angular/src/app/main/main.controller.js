'use strict';

angular.module('mutrack')
  .controller('MainCtrl', function ($scope, $controller, localStorageService, userDetails) {
    // Check if the user is authenticated.
    $scope.isAutheticated = function() {
      if (userDetails.token === '') {
        return false;
      }

      return true;
    };

    if (!$scope.isAutheticated()) {
      // Local storage of public packages.
      var packagesInStore = localStorageService.get('packages');

      $scope.packages = packagesInStore || [];

      $scope.$watch('packages', function() {
        localStorageService.set('packages', $scope.packages);
      }, true);

      // Inheritance the track controller.
      $controller('TrackCtrl', {$scope: $scope});
    }
});
