/**
    * BooXchange Project
    * Created by Al on 15-Oct-16.
    */

angular.module('authApp')
    .factory('authenticationService', [ '$http', function ($http) {

        this.createUser = function (userData, onSuccess, onError) {
            //Send Http request;
            $http({
                method:'POST',
                url: '/api/signup',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: userData
            }).then(
                function success(response) {
                    onSuccess(response.data);
                },
                function error(response) {
                    onError(response.data);
                }
            );
        };

        this.authenticateUser = function (userData, onSuccess, onError) {

            $http({
                method:'POST',
                url: '/api/signin',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: userData
            }).then(
                function success(response) {
                    onSuccess(response.data);
                },
                function error(response) {
                    onError(response.data);
                }
            );
        };

        this.signout = function (onSuccess, onError) {
            $http({
                method:'DELETE',
                url: '/api/signin',
                headers: {
                    'Content-Type': 'application/json'
                },
                params: {
                    action: 'signout'
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

        return this;
    }]);