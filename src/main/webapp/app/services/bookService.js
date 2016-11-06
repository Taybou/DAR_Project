/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 21/10/2016.
 */

angular.module('booxchangeApp')
    .factory('bookService', ['$http', function ($http) {

        //Find books by query
        this.getBooks = function (query, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/books',
                params: {
                    query: query,
                    action: 'search'
                }
            }).then(
                function success(response) {
                    onSuccess(response);
                },
                function error(response) {
                    onError(response);
                }
            );
        };

        // Get one book by isbn
        this.getBook = function (isbn, onSuccess, onError) {

            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/books',
                params: {
                    query: isbn,
                    action: 'details'
                }
            }).then(
                function success(response) {
                    onSuccess(response);
                },
                function error(response) {
                    onError(response);
                }
            );
        };

        this.getOwnedBooks = function (query, onSuccess, onError) {
            $http({
                method: 'GET',
                url: '/api/books',
                headers: {
                    'Content-Type': 'application/json'
                },
                params: {
                    query: query,
                    action: 'searchOwned'
                }
            }).then(
                function success(response) {
                    onSuccess(response);
                },
                function error(response) {
                    onError(response);
                }
            );
        };

        return this;
    }]);
