/**
 * Created by Al on 02-Nov-16.
 */

angular.module('booxchangeApp')
    .controller('BookController', [
        '$routeParams',
        'bookService',
        'userService',
        function ($routeParams, bookService, userService) {

            var vm = this;
            vm.ISBN = $routeParams.bookISBN;
            vm.book = null;
            vm.users = {};
            vm.loading = true;
            vm.loadingUsers = true;

            bookService.getBook(vm.ISBN,
                function (response) {
                    vm.loading = false;
                    vm.book = response.data;
                },
                function (response) {
                    vm.loading = false;
                    vm.error = response.data;
                });

            userService.getProfilesByIsbn(vm.ISBN,
                function (response) {
                    vm.loadingUsers = false;
                    vm.users = response.data;


                },
                function (response) {
                    vm.loadingUsers = false;
                    vm.error = response.data;
                });

            return vm;
        }
    ]);