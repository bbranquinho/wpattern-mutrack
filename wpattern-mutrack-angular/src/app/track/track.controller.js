'use strict';

angular.module('mutrack')
  .controller('TrackCtrl', function ($scope, $modal, $log, $timeout, TrackSrv, SchedulerTrackSrv, ngNotify) {
    $scope.savePackage = function(pack) {
      pack.code = pack.code.toUpperCase();
      $scope.packages.push(pack);
      ngNotify.set('Pacote \'' + pack.code + '\' salvo, buscando o último status!', 'success');
      TrackSrv.trackLastEvent(pack);
    };

    $scope.deletePackage = function(pack) {
      var indexOf = $scope.packages.indexOf(pack);
      $scope.packages.splice(indexOf, 1);
    };

    // Open Modal (Add a New Package)
    $scope.openAddPackage = function () {
      var modalInstance = $modal.open({
        animation: true,
        templateUrl: 'trackModalContent.html',
        controller: 'TrackModalCtrl',
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

    // Open Modal (Edit a Package)
    $scope.openEditPackage = function (pack) {
      var modalInstance = $modal.open({
        animation: true,
        templateUrl: 'trackModalContent.html',
        controller: 'TrackModalCtrl',
        size: 'lg',
        resolve: {
          pack: function () {
            return pack;
          }
        }
      });

      modalInstance.result.then(function (packEdited) {
        pack.name = packEdited.name;
        pack.description = packEdited.description;
      });
    };

    // Open Modal (Full Track)
    $scope.openTrackPackage = function (code) {
      var modalInstance = $modal.open({
        animation: true,
        templateUrl: 'fullTrackModalContent.html',
        controller: 'FullTrackModalCtrl',
        size: 'lg',
        resolve: {
          code: function () {
            return code;
          }
        }
      });

      modalInstance.result.then(function () { });
    };

    // Run and show the update packate status timeout.
    $scope.updateTime = 0;

    function countdown() {
      if ($scope.updateTime !== undefined) {
        $scope.updateTime--;
      }

      $scope.timeout = $timeout(countdown, 1000);
    }

    $scope.resetTimeout = function() {
      $scope.updateTime = TrackSrv.intervalTrack / 1000;
    };

    $scope.startTimeout = function() {
      $scope.resetTimeout();
      countdown();
    };

    $scope.stopTimeout = function() {
      $timeout.cancel($scope.timeout);
    };

    $scope.disableTimeout = function() {
      $scope.updateTime = undefined;
      $scope.stopTimeout();
    };

    // Switch to activate/desactivate auto update.
    $scope.switchEnabled = true; // active

    $scope.onSwitchChange = function(status) {
      if (status) {
        $scope.startTimeout();
        SchedulerTrackSrv.start($scope.packages, $scope.resetTimeout);
      } else {
        $scope.disableTimeout();
        SchedulerTrackSrv.stop();
      }
    };

    // Track the last event of all packages.
    TrackSrv.trackMultipleLastEvent(TrackSrv.selectPackagesToTrack($scope.packages), $scope.resetTimeout);

    // Start a scheduler to track packages.
    $scope.startTimeout();
    SchedulerTrackSrv.start($scope.packages, $scope.resetTimeout);
});
