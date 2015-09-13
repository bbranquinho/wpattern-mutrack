'use strict';

var BASE_URL = 'http://localhost:8080/service';

angular.module('mutrack', ['ngAnimate', 'ngCookies', 'ngTouch', 'ngSanitize',
 'ngResource', 'ui.router', 'ui.bootstrap', 'LocalStorageModule', 'ngNotify',
 'uiSwitch'])
  .constant('REST_URL', {
    'ROOT':BASE_URL,
    'PUBLIC_PATH':BASE_URL + '/public',
    'PRIVATE_PATH':BASE_URL + '/private'
  })
  // Define the service of local storage.
  .config(['localStorageServiceProvider', function(localStorageServiceProvider){
    localStorageServiceProvider.setPrefix('ls');
  }])
  // Define routes.
  .config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
      .state('config', {
        url: '/config',
        templateUrl: 'app/config/config.html',
        controller: 'ConfigCtrl'
      })
      .state('home', {
        url: '/',
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
      })
      .state('login', {
        url: '/login',
        templateUrl: 'app/login/login.html',
        controller: 'LoginCtrl'
      })
      .state('logout', {
        url: '/logout',
        controller: 'LogoutCtrl'
      })
      .state('package', {
        url: '/package',
        templateUrl: 'app/package/package.html',
        controller: 'PackageCtrl'
      })
      .state('user', {
        url: '/user',
        templateUrl: 'app/user/user.html',
        controller: 'UserCtrl'
      });

    $urlRouterProvider.otherwise('/');
  })
  // Define values of the user.
  .value('userDetails', {
    authorities: ['user', 'admin'],
    token: 'admin@admin.com:1442150572080:6d74879abb07400a4998c668cd112ed0'
  });
