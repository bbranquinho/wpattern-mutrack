'use strict';

var intervalTrack = 10 * 60 *1000;

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

// SERVICES

angular.module('mutrack')
  .service('AutoTrackSrv', function($interval) {
    return {
      track : function(packages) {
        $interval(function() {
          for (var pack in packages) {
            var asss = pack;
          }
        }, intervalTrack);
      }
    };
  });
