/**
 * Created by Al on 02-Nov-16.
 */

angular.module('booxchangeApp')
    .controller('UserController', [
        '$routeParams',
        '$uibModal',
        'userService',

        function ($routeParams, $uibModal, userService) {

            var vm = this;
            vm.ISBN = $routeParams.bookISBN;
            vm.ID = $routeParams.userID;
            vm.resultMessage = null;
            vm.loading = true;
            vm.books = null;

            var onSuccess = function (response) {
                vm.loading = false;
                vm.resultMessage = response.data;
                vm.books = response.data;
            };

            var onError = function (error) {
                vm.loading = false;
                vm.resultMessage = error.data;
            };

            vm.add = function () {
                userService.addBook(vm.ID, vm.ISBN, onSuccess, onError);
            };

            vm.delete = function () {
                userService.deleteBook(vm.ID, vm.ISBN, onSuccess, onError);
            };

            vm.getBooks = function () {
                userService.getBooks(vm.ID, vm.ISBN, onSuccess, onError);
            }


            return vm;
        }
    ]);