'use strict';

angular.module('mutrack')
  .controller('TrackCtrl', function ($scope, $modal, $log, $timeout, TrackSrv, SchedulerTrackSrv) {

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

    // Switch to activate/desactivate auto update.
    $scope.switchEnabled = true; // active

    $scope.onSwitchChange = function(status) {
      if (status) {
        SchedulerTrackSrv.start();
      } else {
        SchedulerTrackSrv.stop();
      }
    };

    // Start scheduler to track packages.
    $scope.updateTime = [];

    SchedulerTrackSrv.configScheduler($scope.packages, $scope.updateTime);
    SchedulerTrackSrv.start();

});
