/**
 * Created by islam on 2016-10-19.
 */

angular.module('booxchangeApp')
    .factory('dataFactory', ['$http', function ($http) {

        this.getDetails = function (query, onSuccess, onError) {
            $http({
                method: 'GET',
                url: '/api/books?query=' + query + '&action=details',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function success(response) {
                    onSuccess(response, 'details');
                },
                function error(response) {
                    onError(response);
                }
            );
        };

        return this;
    }]);
