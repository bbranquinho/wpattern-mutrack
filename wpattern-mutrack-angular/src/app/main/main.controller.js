'use strict';

angular.module('mutrack')
  .controller('MainCtrl', function ($scope, $modal, $log, PackageSrv, SchedulerTrackSrv, localStorageService, ngNotify) {
    // Local storage of public packages.
    var packagesInStore = localStorageService.get('packages');

    $scope.packages = packagesInStore || [];

    $scope.$watch('packages', function() {
      localStorageService.set('packages', $scope.packages);
    }, true);

    $scope.savePackage = function(pack) {
      pack.code = pack.code.toUpperCase();
      $scope.packages.push(pack);
      ngNotify.set('Pacote \'' + pack.code + '\' salvo, buscando o último status!', 'success');
      PackageSrv.trackLastEvent(pack);
    };

    $scope.deletePackage = function(pack) {
      var indexOf = $scope.packages.indexOf(pack);
      $scope.packages.splice(indexOf, 1);
    };

    // Open Modal (Add a New Package)
    $scope.openAddPackage = function () {
      var modalInstance = $modal.open({
        animation: true,
        templateUrl: 'packageModalContent.html',
        controller: 'MainModalCtrl',
        size: 'lg',
        resolve: {
          pack: function () {
          }
        }
      });

      modalInstance.result.then(function (pack) {
        $scope.savePackage(pack);
      });
    };

    // Track the last event of all packages.
    PackageSrv.trackMultipleLastEvent(PackageSrv.selectPackagesToTrack($scope.packages));

    // Start a scheduler to track packages.
    SchedulerTrackSrv.track($scope.packages);
});

// More about modal: https://angular-ui.github.io/bootstrap/
angular.module('mutrack').controller('MainModalCtrl', function ($scope, $modalInstance, pack) {

  $scope.package = { };

  $scope.save = function () {
    $modalInstance.close($scope.package);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});
