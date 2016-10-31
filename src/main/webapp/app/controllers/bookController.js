/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 21/10/2016.
 */

angular.module('booxchangeApp')
    .controller('BookController', ['booksearchService', 'bookaddService', 'dataFactory', 'ModalService' ,
        function (booksearchService, bookaddService, bookDetailsFactory,ModalService) {

            var vm = this;
            vm.books = null;
            vm.status = null;
            vm.bookdetails = null;

            var onSuccess = function (response, action) {
                if (action === 'added') {
                    vm.status = response.data;
                } else if (action === 'found') {
                    vm.books = response.data;
                } else if (action === 'details') {
                    vm.bookdetails = response.data;
                    //console.log('dd ' + vm.bookdetails);

                }
            };

            var onError = function (error) {
                vm.status = 'Unable to load customer data: ' + error.message;
            };

            vm.find = function () {
                booksearchService.getBooks(vm.query, onSuccess, onError);
            };

            vm.add = function (userName, query) {
                console.log('ctrl : ' + userName + ' ' + query);
                bookaddService.addBook(userName, query, onSuccess, onError);
            };

            // vm.details = function (query) {
            //     ModalService.showModal({
            //         templateUrl: "views/template.html",
            //         controller: ""
            //     }).then(function(modal) {
            //
            //         //it's a bootstrap element, use 'modal' to show it
            //         modal.element.modal();
            //
            //
            //         modal.close.then(function(result) {
            //             console.log(result);
            //         });
            //     });
            //     bookDetailsFactory.getDetails(query, onSuccess, onError);
            // };

            return vm;
        }])

    .filter('getIsbn13', function () {
        return function (isbns) {
            var filtered = [];
            for (var i = 0; i < isbns.length; i++) {
                var isbn = isbns[i];
                if (isbn.type == 'ISBN_13') {
                    filtered.push(isbn);
                }
            }
            return filtered;
        }
    });
