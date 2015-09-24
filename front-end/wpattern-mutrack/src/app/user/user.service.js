'use strict';

function setPermissions(users) {
  users.forEach(function(user, userIndex) {
    users[userIndex].permission = { admin:false, user:false };

    user.permissions.forEach(function(userPermission) {
      if (userPermission.permission === 'ADMIN') {
        users[userIndex].permission.admin = true;
      }

      if (userPermission.permission === 'USER') {
        users[userIndex].permission.user = true;
      }
    });
  });
}

angular.module('mutrack')
  .service('UserSrv', function($http, ngNotify, REST_URL) {
    var url = REST_URL.PRIVATE_PATH + '/user';
    var userFactory = {};

    userFactory.retrieveUsers = function(scope) {
      $http.get(url)
        .success(function(users) {
          setPermissions(users);
          scope.users = users;
        })
        .error(function() {
        });
    };

    userFactory.addUser = function(users, user, callback) {
      delete user.permission;

      var requestParams = {
        method: 'POST',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: user
      };

      $http(requestParams)
        .success(function(newUser) {
          users.push(newUser);
          setPermissions(users);
          callback();

          ngNotify.set('Usuário ' + user.name + ' criado com sucesso!', 'success');
        })
        .error(function() {
          ngNotify.set('Erro criar o usuário ' + user.name + '.', 'error');
        });
    };

    userFactory.updateUser = function(users, user, callback) {
      delete user.permission;

      var requestParams = {
        method: 'PUT',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: user
      };

      $http(requestParams)
        .success(function() {
          for (var i = 0; i < users.length; i++) {
            if (users[i].id === user.id) {
              users[i] = user;
            }
          }

          setPermissions(users);
          callback();

          ngNotify.set('Usuário ' + user.name + ' modificado com sucesso!', 'success');
        })
        .error(function() {
          ngNotify.set('Erro modificar o usuário ' + user.name + '.', 'error');
        });
    };

    userFactory.deleteUser = function(users, user) {
      var requestParams = {
        method: 'DELETE',
        url: url,
        headers: { 'Content-Type': 'application/json' },
        data: { 'id': user.id }
      };

      $http(requestParams)
        .success(function() {
          users.splice(users.indexOf(user), 1);

          ngNotify.set('Usuário ' + user.name + ' deletado com sucesso!', 'success');
        })
        .error(function() {
          ngNotify.set('Erro ao deletar o usuário ' + user.code + '.', 'error');
        });
    };

    return userFactory;
  });
