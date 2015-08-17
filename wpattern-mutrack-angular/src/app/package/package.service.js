'use strict';

angular.module('mutrack')
  .service('PackageSrv', function($http, REST_URL) {
    var url = REST_URL.PRIVATE_PATH + '/package';
    var packageFactory = {};

    packageFactory.retrievePackages = function(scope) {
      $http.get(url)
        .success(function(data) {
          scope.packages = data;
        })
        .error(function(error) {
          console.log(error);
        });
    };

    return packageFactory;
  });
