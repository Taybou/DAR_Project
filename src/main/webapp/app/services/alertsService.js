/**
 * Created by Al on 07-Nov-16.
 */

angular.module('booxchangeApp')
    .factory('alertsService', [
        '$http',
        function ($http) {

            var alertsService = {};

            alertsService.getAlerts = function (onSuccess, onError) {
                $http({
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    url: '/api/alerts'
                }).then(
                    function success(response) {
                        onSuccess(response.data);
                    },
                    function error(response) {
                        onError(response.data);
                    }
                );
            };

            alertsService.createAlert = function(bookISBN, onSuccess, onError) {
                $http({
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    url: '/api/alerts',
                    params: {
                        action: 'createAlert',
                        bookISBN: bookISBN
                    },
                    data: {}
                }).then(
                    function success(response) {
                        onSuccess(response.data);
                    },
                    function error(response) {
                        onError(response.data);
                    }
                );
            };

            alertsService.deleteAlert = function(bookISBN, onSuccess, onError) {
                $http({
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    url: '/api/alerts',
                    params: {
                        bookISBN: bookISBN
                    },
                    data: {}
                }).then(
                    function success(response) {
                        onSuccess(response.data);
                    },
                    function error(response) {
                        onError(response.data);
                    }
                );
            };

            return alertsService;
        }
    ]);