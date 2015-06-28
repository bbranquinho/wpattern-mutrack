'use strict';

angular.module('mutrack').factory('TrackModalSrv', function(REST_URL, ngNotify) {
  var factory = {};

  factory.refreshEvents = function($scope, $http, code) {
    $scope.events.eventChecking = true;

    $http.get(REST_URL.PUBLIC_PATH + '/track/fullevent/' + code)
      .success(function(data) {
        $scope.events = data.events.sort(function(e1, e2) { return e2.date - e1.date; });
        $scope.events.eventChecking = false;
      })
      .error(function(erro) {
        $scope.events.eventChecking = false;
        ngNotify.set('Erro ao buscar os status do pacote: ' + erro, 'error');
      });
  };

  return factory;
});
