'use strict';

var intervalTrack = 15 * 60 * 1000; // 15 minutes

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
  .service('SchedulerTrackSrv', function($interval, PackageSrv) {
    return {
      track : function(packages) {
        $interval(function() {
          var packagesToVerify = PackageSrv.selectPackagesToTrack(packages);

          PackageSrv.trackMultipleLastEvent(packagesToVerify);
        }, intervalTrack);
      }
    };
  });
