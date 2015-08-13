'use strict';

angular.module('mutrack')
  .controller('MainCtrl', function ($scope, $controller, localStorageService) {
    // Inheritance the track controller.
    $controller('TrackCtrl', {$scope: $scope}); //This works

    // Local storage of public packages.
    var packagesInStore = localStorageService.get('packages');

    $scope.packages = packagesInStore || [];

    $scope.$watch('packages', function() {
      localStorageService.set('packages', $scope.packages);
    }, true);

    $scope.message = ' (PACOTES SALVOS LOCALMENTE)'
});
