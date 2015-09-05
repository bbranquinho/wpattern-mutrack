'use strict';

angular.module('mutrack')
  .service('UserSrv', function($http, ngNotify, REST_URL) {
    var url = REST_URL.PRIVATE_PATH + '/user';
    var userFactory = {};

    userFactory.findUsers = function(scope) {
      $http.get(url)
        .success(function(data) {
          scope.users = data;
        })
        .error(function() {
        });
    };

    return userFactory;
  });
