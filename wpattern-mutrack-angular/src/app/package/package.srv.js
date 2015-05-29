'use strict';

angular.module('mutrack')
  .factory('PackageSrv', function($http) {
    var REST_URL = 'http://localhost:8080/service/public/package';
    var dataFactory = {};

    dataFactory.getPackages = function() {
      return $http.get(REST_URL);
    };

    return dataFactory;
});
