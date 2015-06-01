'use strict';

angular.module('monitoradorPacotes', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize', 'ui.router'])
  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .state('package_manager', {
        url: '/gerenciador-de-pacotes',
        templateUrl: 'app/package_manager/gerenciador_de_pacotes.html',
        controller: 'gerenciadorPacotes'
      });

    $urlRouterProvider.otherwise('/');
  })
;
