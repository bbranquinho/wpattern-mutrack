'use strict';

angular.module('mutrack')
  .controller('MainCtrl', function ($scope, PackageSrv, localStorageService, ngNotify) {
    var packagesInStore = localStorageService.get('packages');

    $scope.packages = packagesInStore || [];

    $scope.$watch('packages', function() {
      localStorageService.set('packages', $scope.packages);
    }, true);

    $scope.savePackage = function() {
      $scope.package.code = $scope.package.code.toUpperCase();
      $scope.packages.push($scope.package);
      ngNotify.set('Pacote salvo, buscando último status do pacote ' + $scope.package.code + '!', 'success');
      PackageSrv.trackLastEvent($scope.package);
      $scope.package = {};
    };

    $scope.deletePackage = function(pack) {
      var indexOf = $scope.packages.indexOf(pack);

      $scope.packages.splice(indexOf, 1);
    };
});
