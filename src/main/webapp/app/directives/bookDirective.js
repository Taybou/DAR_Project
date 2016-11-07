/**
 * Created by Al on 01-Nov-16.
 */

angular.module('booxchangeApp')
    .directive('book', function () {
        return {
            restrict: 'E',
            scope: {
                book: '=book',
                createAlert: '&',
                hasAlert: '&',
                deleteAlert: '&'
            },
            templateUrl: 'app/directives/templates/book.html'
        }
    });