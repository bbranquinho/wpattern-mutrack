'use strict';

angular.module('mutrack')
  .controller('PackageCtrl', function($scope, PackageSrv) {
    $scope.packages = [];

    function getPackages() {
      PackageSrv.getPackages()
        .success(function(packages) {
          $scope.packages = packages;
        })
        .error(function (error) {
          $scope.status = 'Unable to load customer data: ' + error.message;
        });
    }

    getPackages();
  });

//$http.get('http://localhost:8080/service/public/package').success;
