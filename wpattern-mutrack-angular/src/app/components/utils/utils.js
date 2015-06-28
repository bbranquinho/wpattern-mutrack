'use strict';

// DIRECTIVES

angular.module('mutrack')
  .directive('ngShow', function($compile, $animate) {
      return  {
        priority: 1000,
        link: function(scope, element) {
          if (element.hasClass('fa-spinner')) {
                //element.attr('no-animate', true);
              $animate.enabled(false, element);
              scope.$watch(function () {
                $animate.enabled(false, element);
              });
              //$compile(element)(scope);
          }
        }
      };
  });
