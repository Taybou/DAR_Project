/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 04/11/2016.
 */

angular.module('booxchangeApp')
    .factory('exchangeService', ['$http', function ($http) {

        this.addExchange = function (user1, user2, isbnBookUser1, isbnBookUser2, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/books',
                params: {
                    action: 'add',
                    user1: user1,
                    user2: user2,
                    isbnBookUser1: isbnBookUser1,
                    isbnBookUser2: isbnBookUser2
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

        this.acceptExchange = function (id, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/books',
                params: {
                    action: 'accept',
                    id: id
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

        this.getSubimetted = function (user1, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/books',
                params: {
                    action: 'viewSubmitted',
                    user1: user1
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

        this.getNotified = function (user2, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/books',
                params: {
                    action: 'viewNotified',
                    user2: user2
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
