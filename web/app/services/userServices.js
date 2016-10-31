/**
 * Created by islam on 2016-10-31.
 */

angular.module('booxchangeApp')
    .factory('userFactory', ['$http', function($http) {

        var urlBase = '/api/user';
        var userFactory = {};

        userFactory.getProfile = function () {
            return $http.get(urlBase);
        };


        return userFactory;
    }]);
