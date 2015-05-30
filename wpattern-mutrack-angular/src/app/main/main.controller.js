'use strict';

angular.module('mutrack')
  .service('oi', function($scope, $http) {
    return "hello";
});

angular.module('mutrack')
  .controller('MainCtrl', function ($scope, oi) {
    $scope.packages = [];

    alert(oi());
});
