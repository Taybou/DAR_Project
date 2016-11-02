/**
    * BooXchange Project
    * Created by Al on 14-Oct-16.
    */

angular.module('authApp')
            .controller('SignupController', [
                'authenticationService',
                function (authenticationService) {

            var vm = this;
            vm.createdUser = null;
            vm.error = null;

            var onSuccess = function (userName) {
                vm.loading = false;
                vm.createdUser = userName;
            };

            var onError = function (error) {
                vm.loading = false;
                vm.error = error;
            };

            vm.submit = function () {
                vm.loading = true;
                vm.error = null;
                authenticationService.createUser(vm.user, onSuccess, onError);
            };

            return vm;
    }])
    .controller('SigninController', [
        'authenticationService',
        '$window',
        function (authenticationService, $window) {

            var vm = this;

            var onError = function (error) {
                vm.error = error;
                vm.loading = false;
            };

            var onSuccess = function () {
                $window.location.href = '/main/web';
            };

            vm.submit = function () {
                vm.loading = true;
                vm.error = null;
                authenticationService.authenticateUser(vm.userData, onSuccess, onError);
            };

            return vm;
        }
    ]);