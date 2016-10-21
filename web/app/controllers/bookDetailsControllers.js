/**
 * Created by islam on 2016-10-19.
 */
angular.module('booxchangeApp')
    .controller('bookDetailsController', ['$scope', 'dataFactory',
        function ($scope, bookDetailsFactory) {

            $scope.status;
            $scope.details;


            getDetails();

            function getDetails() {
                bookDetailsFactory.getDetails()
                    .then(function (response) {
                        $scope.details = response.data;
                        console.log($scope.details);
                    }, function (error) {
                        $scope.status = 'Unable to load customer data: ' + error.message;
                    });
            }

        }]);