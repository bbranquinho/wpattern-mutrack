'use strict';

angular.module('monitoradorPacotes')
	.controller('gerenciadorPacotes', function($scope, $http) {

		$scope.searchCod = function(P_COD_UNI) {

			$http.get("http://localhost:8882/track?code=" + P_COD_UNI)
				.success(function(response) {
					$scope.etapas = response;
				}).error(function(error) {
					alert("error");
				});
		};
	});


angular.module('monitoradorPacotes')
	.controller('adicionarPacotes', function($scope) {
		$scope.pacotes = [];

		$scope.addPacote = function(novoPacote) {
			$scope.pacotes.push({
				nome: novoPacote,
				editando: false
			});
			$scope.novoPacote = "";
		};

		$scope.deletePacote = function() {

		};
	});
