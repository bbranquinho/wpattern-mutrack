'use strict';

angular.module('mutrack')
  .controller('LogoutCtrl', function($location, userDetails) {
    // Execute the logout and redirect to homepage.
    userDetails.authorities = [];
    userDetails.token = '';

    $location.path('/home');
  });
