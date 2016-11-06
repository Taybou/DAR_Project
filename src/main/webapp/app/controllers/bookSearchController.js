/**
 * Created by Al on 01-Nov-16.
 */

angular.module('booxchangeApp')
    .controller('BookSearchController', [
        'bookService',
        'notificationService',
        function (bookService, notificationService) {
            var vm = this;
            vm.books = null;

            notificationService.autoUpdate();


            var onSuccess = function (response, action) {
                vm.loading = false;
                vm.books = response.data;
            };

            var onError = function (error) {
                vm.error = error;
            };

            vm.find = function () {
                vm.loading = true;
                bookService.getBooks(vm.query, onSuccess, onError);
            };

            return vm;
        }]);

    // .filter('getIsbn13', function () {
    //
    //     return function (isbns) {
    //
    //         var filtered = [];
    //         for (var i = 0; i < isbns.length; i++) {
    //             var isbn = isbns[i];
    //             if (isbn.type == 'ISBN_13') {
    //                 filtered.push(isbn);
    //             }
    //         }
    //         return filtered;
    //     }
    // });