'use strict';

angular.module('mutrack')
  .service('UserSrv', function($http, ngNotify, REST_URL) {
    var url = REST_URL.PRIVATE_PATH + '/user';
    var userFactory = {};

    userFactory.findUsers = function(scope) {
      /*var requestParams = {
        method: 'POST',
        url: url,
        headers: {
          'X-Auth-Token': 'branquinho@gmail.com:1439760149056:fbc45b9aac17c7c7a23a17f4d900fb72',
          'Content-Type': 'application/json'
        },
        data: {
          'name': 'Teste Branquinho',
          'email': 'testeangular@gmail.com',
          'password': '123456'
        }
      };*/
      /*var requestParams = {
        method: 'GET',
        url: url,
        headers: {
          'X-Auth-Token': 'branquinho@gmail.com:1439760149056:fbc45b9aac17c7c7a23a17f4d900fb72',
          'Content-Type': 'application/json'
        }
      };

      $http(requestParams)
        .success(function(data) {
          scope.users = data;//console.log(data);
        })
        .error(function(erro) {
          console.log(erro);
        });*/

      $http.get(url)
        .success(function(data) {
          scope.users = data;//console.log(data);
        })
        .error(function(erro) {
          console.log(erro);
        });
    };

    return userFactory;
  });
