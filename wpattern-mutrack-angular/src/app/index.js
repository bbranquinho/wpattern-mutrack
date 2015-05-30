'use strict';

angular.module('mutrack', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize',
 'ngResource', 'ui.router', 'ui.bootstrap', 'datatables'])
  .constant('REST_URL', {
    'ROOT':'http://localhost:8080/service/public'
  })
  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .state('package', {
        url: '/package',
        templateUrl: 'app/package/package.html',
        controller: 'PackageCtrl'
      });

    $urlRouterProvider.otherwise('/');
  });
