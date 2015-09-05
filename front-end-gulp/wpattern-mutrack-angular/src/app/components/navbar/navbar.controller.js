'use strict';

angular.module('mutrack')
  .controller('NavbarCtrl', function (userDetails, $scope) {

    // Verify if an user has some permission.
    $scope.hasAnyPermission = function(authorities) {
      var hasPermission = false;

      userDetails.authorities.forEach(function(userAuth) {
        authorities.forEach(function(auth) {
          if (userAuth === auth) {
            hasPermission = true;
          }
        });
      });

      return hasPermission;
    };
});
