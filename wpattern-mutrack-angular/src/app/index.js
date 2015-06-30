'use strict';

var BASE_URL = 'http://localhost:8080/service';

angular.module('mutrack', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize',
 'ngResource', 'ui.router', 'ui.bootstrap', 'LocalStorageModule', 'ngNotify'])
  .constant('REST_URL', {
    'ROOT':BASE_URL,
    'PUBLIC_PATH':BASE_URL + '/public',
    'PRIVATE_PATH':BASE_URL + '/private'
  })
  .config(['localStorageServiceProvider', function(localStorageServiceProvider){
    localStorageServiceProvider.setPrefix('ls');
  }])
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
      })
      .state('login', {
        url: '/login',
        templateUrl: 'app/login/login.html',
        controller: 'LoginCtrl'
      })
      .state('config', {
        url: '/config',
        templateUrl: 'app/config/config.html',
        controller: 'ConfigCtrl'
      });

    $urlRouterProvider.otherwise('/');
  });
