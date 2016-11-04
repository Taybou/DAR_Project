/**
 * BooXchange Project
 * Created by Mohamed Tayeb on 04/11/2016.
 */
angular.module('booxchangeApp')
    .controller('ExchangeController', [
        'exchangeService',
        function (exchangeService) {

            var vm = this;
            vm.isbnBookUser1 = null;
            vm.isbnBookUser2 = null;
            vm.user1 = null;
            vm.user2 = null;

            vm.id = null;
            vm.exchanges = null;
            vm.error = null;
            vm.message = null;

            var onSuccess = function (response) {
                vm.exchanges = response.data;
                vm.message = response.data;
            };

            var onError = function (error) {
                vm.error = error;
            };

            vm.add = function () {
                exchangeService.addExchange(vm.user1, vm.user2, vm.isbnBookUser1, vm.isbnBookUser2, onSuccess, onError);
            };

            vm.accept = function () {
                exchangeService.acceptExchange(vm.id, onSuccess, onError)
            };

            vm.getSubimetted = function () {
                exchangeService.getSubimetted(vm.user1, onSuccess, onError)
            };

            vm.getNotified = function () {
                exchangeService.getNotified(vm.user2, onSuccess, onError)
            };

            return vm;
        }]);
