/**
 * Created by islam on 2016-10-19.
 */

angular.module('booxchangeApp')
    .factory('dataFactory', ['$http', function($http) {

        var urlBase = '/api/books/*';
        var dataFactory = {};

        dataFactory.getDetails = function () {
            return $http.get(urlBase);
        };


        return dataFactory;
    }]);
