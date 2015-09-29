'use strict';

angular.module('mutrack')
  .controller('indexCtrl', function($cookies, userDetails) {
    var xAuthToken = $cookies.getObject('x-auth-token');

    if (xAuthToken !== undefined) {
      userDetails.authorities = xAuthToken.authorities;
      userDetails.token = xAuthToken.token;
    }
  });
