'use strict';
// More about modal: https://angular-ui.github.io/bootstrap/

angular.module('mutrack').controller('PackageModalCtrl', function ($scope, $modalInstance) {

  $scope.package = { };

  $scope.save = function () {
    $modalInstance.close($scope.package);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
});

angular.module('mutrack').factory('TrackPackageModalSrv', function(REST_URL, ngNotify) {
  var factory = {};

  factory.refreshEvents = function($scope, $http, code) {
    $scope.events.eventChecking = true;

    $http.get(REST_URL.PACKAGE + '/tracker/fullevent/' + code)
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

angular.module('mutrack').controller('TrackPackageModalCtrl', function ($scope, $http, $modalInstance, code, TrackPackageModalSrv) {

  $scope.events = [];
  $scope.code = code;

  $scope.close = function () {
    $modalInstance.close();
  };

  TrackPackageModalSrv.refreshEvents($scope, $http, code);
});
