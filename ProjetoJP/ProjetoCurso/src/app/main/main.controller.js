'use strict';

angular.module('monitoradorPacotes')
  .service('servValidaLogin', function() {

    var vl = function(login, senha) {
      return login == "Joao" && senha == "123";
    };

    return {
      vl: vl
    }
  });

angular.module('monitoradorPacotes')
  .controller('MainCtrl', function($scope, servValidaLogin, $location) {
    $scope.validate = function(login, senha) {

      if (!servValidaLogin.vl(login, senha)) {
        alert("Login ou Senha inválidos");
      } else {
        $location.path("/gerenciador-de-pacotes");
      }
    };

  });


//http://websro.correios.com.br/sro_bin/txect01$.QueryList?P_TIPO=001&P_COD_UNI=DM532508124BR&P_LINGUA=001
