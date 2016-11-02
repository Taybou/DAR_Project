/**
 * Created by Al on 02-Nov-16.
 */

angular.module('booxchangeApp')
    .controller('BookController', [
        '$routeParams',
        'bookService',
        function ($routeParams, bookService) {

            var vm = this;
            vm.ISBN = $routeParams.bookISBN;
            vm.book = null;
            vm.loading = true;

            bookService.getBook(vm.ISBN,
                function (response) {
                    vm.loading = false;
                    vm.book = response.data;
                },
                function (response) {
                    vm.loading = false;
                    vm.error = response.data;
                });

            return vm;
        }
    ]);