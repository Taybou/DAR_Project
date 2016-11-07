/**
 * Created by Al on 04-Nov-16.
 */

angular.module('booxchangeApp')
    .controller('UserProfileController', [
        '$scope',
        '$uibModal',
        'userService',
        'bookService',
        function ($scope, $uibModal, userService, bookService) {
            var vm = this;

            vm.error = null;
            vm.user = {
                userName: 'Homer09',
                lastName: 'Simpson',
                firstName: 'Homer',
                address: null,
                description: 'I love donuts !',
                gender: 'Male',
                profilePictureUrl: null
            };

            vm.books = {};

            vm.gender = {
                Male: "Homme",
                Female: "Femme"
            };

            // Modal scope
            vm.updateUser = {};

            vm.loadUser = function () {
                userService.getUser(
                    function (user) {
                        vm.user = user;
                        vm.loadBooks();
                    },
                    function (error) {
                        vm.error = error;
                    }
                );
            };

            vm.loadBooks = function () {
                vm.loadingBooks = true;
                userService.getBooks(vm.user.userName,
                    function (books) {
                        vm.books = books;
                        vm.loadingBooks = false;
                    },
                    function (error) {
                        vm.error = error;
                    }
                );
            };

            vm.deleteBook = function (book) {
                vm.loadingBooks = true;
                userService.deleteBook(book.volumeInfo.industryIdentifiers[0].identifier,
                    function (user) {
                        vm.user = user;
                        var index = vm.books.indexOf(book);
                        vm.books.splice(index, 1);
                        vm.loadingBooks = false;
                    },
                    function (error) {
                        vm.error = error;
                    }
                )
            };

            vm.findBook = function () {
                vm.loadingSearch = true;
                bookService.getBooks(vm.query, function (response) {
                    vm.foundBooks = response.data.slice(0, 5);
                    vm.loadingSearch = false;
                }, function (error) {
                    vm.error = error;
                });
            };

            vm.addBook = function (book) {
                vm.loadingSearch = true;
                userService.addBook(book.volumeInfo.industryIdentifiers[0].identifier,
                    function (user) {
                        vm.user = user;
                        vm.foundBooks = null;
                        vm.books.push(book);
                        vm.loadingSearch = false;
                    },
                    function (error) {
                        vm.error = error;
                    }
                )
            };

            vm.open = function () {
                vm.updatedUser = angular.copy(vm.user);
                vm.modal = $uibModal.open({
                    templateUrl: 'myModalContent.html',
                    scope: $scope
                });
            };

            vm.close = function () {
                vm.updateUser.errors = null;
                vm.modal.dismiss();
            };

            vm.save = function () {
                vm.updateUser.loading = true;
                userService.updateUser(vm.updatedUser,
                    function (user) {
                        vm.user = user;
                        vm.updateUser.loading = false;
                        vm.modal.dismiss();
                    },
                    function (errors) {
                        vm.updateUser.loading = false;
                        vm.updateUser.errors = errors;
                    }
                );
            };

            vm.loadUser();

            return vm;
        }
    ])
    .controller('ViewUserProfileController', [
        '$scope',
        '$routeParams',
        '$uibModal',
        'userService',
        'messageService',
        function ($scope, $routeParams, $uibModal, userService, messageService) {

            var vm = this;
            vm.error = null;
            vm.loading = false;
            vm.loadingBooks = true;
            vm.userName = $routeParams.userName;
            vm.user = {};
            vm.books = {};

            vm.gender = {
                Male: "Homme",
                Female: "Femme"
            };


            vm.loading = true;
            userService.viewUserProfile(vm.userName,
                function (user) {
                    vm.user = user;
                    vm.loading = false;
                },
                function (error) {
                    vm.error = error;
                    vm.loading = false;
                }
            );

            userService.getBooks(vm.userName,
                function (books) {
                    vm.books = books;
                    vm.loadingBooks = false;
                },
                function (error) {
                    vm.error = error;
                }
            );

            vm.openMsg = function () {
                vm.modalMsg = $uibModal.open({
                    templateUrl: 'SendMessageModal.html',
                    scope: $scope
                });
            };

            vm.closeMsg = function () {
                vm.message = undefined;
                vm.modalMsg.dismiss();
            };

            vm.saveMsg = function () {
                vm.sendingMessageLoading = true;
                messageService.sendMessage(
                    {
                        username: vm.user.userName,
                        message: vm.message
                    },
                    function onSuccess() {
                        vm.sendingMessageLoading = undefined;
                        vm.closeMsg();
                    },
                    function onError() {

                    }
                )
            };

            return vm;
        }
    ]);