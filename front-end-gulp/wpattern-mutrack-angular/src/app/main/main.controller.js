'use strict';

angular.module('mutrack')
  .controller('MainCtrl', function ($scope, $controller, localStorageService, userDetails, ngNotify, TrackSrv) {
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

      // Methods to save, edit and delete a package.
      // A better/right way to pass 'packages' is use a promisse.
      $scope.savePackage = function(scope, pack) {
        pack.code = pack.code.toUpperCase();
        scope.packages.push(pack);
        ngNotify.set('Pacote \'' + pack.code + '\' salvo, buscando o último status!', 'success');
        TrackSrv.trackLastEvent(pack);
      };

      // A better/right way to pass 'packages' is use a promisse.
      $scope.deletePackage = function(scope, pack) {
        var indexOf = $scope.packages.indexOf(pack);
        scope.packages.splice(indexOf, 1);
      };

      // Inheritance the track controller.
      $controller('TrackCtrl', {$scope: $scope});
    }
});
