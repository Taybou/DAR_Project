/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 23/10/2016.
 */
angular.module('booxchangeApp')
    .factory('userService', ['$http', function ($http) {

        this.addBook = function (userName, isbn, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    isbn: isbn,
                    username: userName,
                    action: 'add'
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

        this.deleteBook = function (userName, isbn, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    isbn: isbn,
                    username: userName,
                    action: 'delete'
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

        this.getBooks = function (userName, isbn, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    isbn: isbn,
                    username: userName,
                    action: 'getbooks'
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
