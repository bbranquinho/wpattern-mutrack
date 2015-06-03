'use strict';

angular.module('mutrack')
  .service('ValidateLoginSrv', function() {

    var vl = function(login, password) {
      return login == "Joao" && password == "123";
    };

    return {
      vl: vl
    }
  });

angular.module('mutrack')
  .controller('LoginCtrl', function($scope, $location, ValidateLoginSrv) {
    $scope.validate = function(login, password) {

      if (!servValidaLogin.vl(login, password)) {
        alert("Login ou Senha inválidos");
      } else {
        $location.path("/packges");
      }
    };

  });
