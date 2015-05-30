'use strict';

angular.module('mutrack')
  .factory('PackageSrv', function($http, REST_URL) {
    var url = REST_URL.ROOT + '/package';
    var dataFactory = {};

    dataFactory.getPackages = function() {
      return $http.get(url);
    };

    return dataFactory;
});
