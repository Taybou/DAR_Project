/**
 * Created by islam on 2016-11-09.
 */


angular.module('booxchangeApp')
    .factory('endroitService', ['$http', function ($http) {
        this.getPlaces = function (username, onSuccess, onError) {
            $http({
                method: 'GET',
                url: '/api/endroit',
                headers: {
                    'Content-Type': 'application/json'
                },
                params: {
                    query: username

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