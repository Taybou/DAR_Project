/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 21/10/2016.
 */

angular.module('booxchangeApp')
    .factory('booksearchService', ['$http', function ($http) {

        this.getBooks = function (query, onSuccess, onError) {
            $http({
                method: 'GET',
                url: '/api/books?query=' + query + '&action=search',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function success(response) {
                    onSuccess(response,'found');
                },
                function error(response) {
                    onError(response);
                }
            );
        };

        return this;
    }]);
