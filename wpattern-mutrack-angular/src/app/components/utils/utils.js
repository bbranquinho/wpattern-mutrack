'use strict';

// DIRECTIVES and FILTERS

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
  })
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
