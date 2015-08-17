'use strict';

// DIRECTIVES
angular.module('mutrack')
  .directive('ngShow', function($compile, $animate) {
      return  {
        priority: 1000,
        link: function(scope, element) {
          if (element.hasClass('fa-spinner')) {
              $animate.enabled(false, element);
              scope.$watch(function () {
                $animate.enabled(false, element);
              });
          }
        }
      };
  });

angular.module('mutrack')
  .directive('login', function() {
  return {
    restrict: 'E',
    templateUrl: '/app/components/navbar/login/login.template.html'
  };
});

// FILTERS
angular.module('mutrack')
  .filter('formatToScheduleTime', function() {
    return function(seconds) {
      if (seconds === undefined) {
        return '';
      }

      if (seconds > 60) {
        var time = Math.floor(seconds / 60);

        return 'Atualizar em ' + time + ' minuto' + (time === 1 ? '' : 's');
      } else if (seconds > 0) {
        return 'Atualizar em ' + seconds + ' segundo' + (seconds === 1 ? '' : 's');
      } else {
        return 'Atualizando!';
      }
    };
  });

// HTTP INTERCEPTORS
angular.module('mutrack')
  .config(function($httpProvider) {
      $httpProvider.interceptors.push('sessionInjector');
  })
  .factory('sessionInjector', function(userDetails) {
    return {
        request: function(config) {
            if (userDetails.token !== '') {
              config.headers['X-Auth-Token'] = userDetails.token;
            }

            return config;
        }
    };
  });
