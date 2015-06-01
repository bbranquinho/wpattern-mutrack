'use strict';

angular.module('mutrack')
  .factory('PackageSrv', function($http, ngNotify, REST_URL) {
    var url = REST_URL.ROOT + '/package';
    var dataFactory = {};

    dataFactory.getPackages = function() {
      return $http.get(url);
    };

    dataFactory.trackLastEvent = function(pack) {
      $http.get(url + '/tracker/lastevent/' + pack.code)
        .success(function(data) {
          pack.lastStatus = data.events[0].description;
          pack.date = new Date(parseInt(data.events[0].date));
        })
        .error(function(erro) {
          ngNotify.set('Erro ao buscar o status do pacote: ' + erro, 'error');
        });
    };

    return dataFactory;
});
