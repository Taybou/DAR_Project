/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 23/10/2016.
 */
angular.module('booxchangeApp')
    .factory('userService', ['$http', '$resource', function ($http, $resource) {

        var userService = {};

        userService.user = null;

        userService.getUser = function (onSuccess, onError) {
            if (userService.user !== null) {
                onSuccess(userService.user);
            }
            else {
                $http({
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    url: '/api/users',
                    params: {
                        action: 'getProfile'
                    }
                }).then(
                    function success(response) {
                        userService.user = response.data;
                        onSuccess(response.data);
                    },
                    function error(response) {
                        onError(response.data);
                    }
                );
            }
        };

        userService.updateUser = function (user, onSuccess, onError) {
            $http({
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    action: 'updateProfile'
                },
                data: user
            }).then(
                function success(response) {
                    userService.user = response.data;
                    onSuccess(response.data);
                },
                function error(response) {
                    onError(response.data);
                }
            );
        };

        userService.addBook = function (isbn, onSuccess, onError) {
            $http({
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    isbn: isbn,
                    action: 'addBook'
                },
                data: {}
            }).then(
                function success(response) {
                    userService.user = response.data;
                    onSuccess(response.data);
                },
                function error(response) {
                    onError(response.data);
                }
            );
        };

        userService.deleteBook = function (isbn, onSuccess, onError) {
            $http({
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    isbn: isbn,
                    action: 'deleteBook'
                },
                data: {}
            }).then(
                function success(response) {
                    userService.user = response.data;
                    onSuccess(response.data);
                },
                function error(response) {
                    onError(response.data);
                }
            );
        };

        userService.getBooks = function (userName, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    action: 'getBooks',
                    userName: userName
                }
            }).then(
                function success(response) {
                    onSuccess(response.data);
                },
                function error(response) {
                    onError(response.data);
                }
            );
        };

        userService.viewUserProfile = function (userName, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/users',
                params: {
                    action: 'viewProfile',
                    userName: userName
                }
            }).then(
                function success(response) {
                    onSuccess(response.data);
                },
                function error(response) {
                    onError(response.data);
                }
            );
        };

        userService.getProfilesByIsbn = function (isbn, onSuccess, onError) {
            var Users = $resource('/api/users?action=getProfilesByISBN&isbn=:isbn', {
                'query': {
                    method: 'GET',
                    isArray: true
                }
            });
            return Users.query({isbn: isbn}, function (response) {
                    response.data = response.data || response;
                    onSuccess(response);
                    return response;
                }
                , onError);
        };

        return userService;
    }]);
