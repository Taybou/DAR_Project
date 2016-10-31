/**
 * Created by islam on 2016-10-31.
 */
angular.module('booxchangeApp')
    .controller('userControllers', ['$scope', 'userFactory',
        function ($scope, userServices) {

            $scope.status;
            $scope.profile;


            getProfile();

            function getProfile() {
                userServices.getProfile()
                    .then(function (response) {
                        $scope.profile = response.data;
                        console.log($scope.profile.userName);
                    }, function (error) {
                        $scope.status = 'Unable to load customer data: ' + error.message;
                    });
            }

        }]);