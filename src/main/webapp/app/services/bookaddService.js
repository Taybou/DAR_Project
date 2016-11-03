/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 23/10/2016.
 */
//TODO:Move this with a user service, this is specific to a user
angular.module('booxchangeApp')
    .factory('bookaddService', ['$http', function ($http) {

        this.addBook = function (userName, query, onSuccess, onError) {
            console.log('serviceX : ' + userName + ' , ' + query);

            $http({
                method: 'GET',
                url: '/api/books?query=' + query + '&userName=' + userName + '&action=add',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then(
                function success(response) {
                    onSuccess(response,'added');
                },
                function error(response) {
                    onError(response);
                }
            );
        };

        return this;
    }]);
