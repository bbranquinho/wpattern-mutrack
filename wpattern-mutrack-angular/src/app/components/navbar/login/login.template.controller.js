'use strict';

angular.module('mutrack')
  .controller('LoginTemplateCtrl', function(userDetails, $scope) {
    $scope.isAuthenticated = function() {
      return !(!userDetails.token || 0 === userDetails.token.length);
    };
  });
