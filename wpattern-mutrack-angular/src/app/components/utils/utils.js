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
  .filter('formatToTime', function() {
    return function(seconds) {
      if (seconds > 60) {
        var time = Math.floor(seconds / 60);

        return time + ' minuto' + (time == 1 ? '' : 's');
      } else if (seconds > 0) {
        return seconds + ' segundo' + (seconds == 1 ? '' : 's');
      } else {
        return 'Atualizando!';
      }
    };
  });
