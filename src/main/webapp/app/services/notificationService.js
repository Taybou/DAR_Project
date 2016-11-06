/**
 * Created by kadao on 05/11/2016.
 */


angular.module('booxchangeApp')
    .factory('notificationService', ['$http', function ($http) {

        this.getMessagesNotification = function (query, onSuccess, onError) {
            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/notifications',
                params: {
                    type: 'message'
                }
            }).then(
                function success(response) {
                    onSuccess(response);
                },
                function error(response) {
                    onError(response);
                }
            );
        };

        this.updateMessagesNotifications = function (onSuccess, onError) {
            //console.log(" updating .... ");

            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/notifications',
                params: {
                    type: 'message'
                }
            }).then(
                function success(response) {
                    //vm.msgNotifsNum = response.data;
                    onSuccess(response.data);
                },
                function error(response) {
                    //vm.msgNotifsNum = 0;
                    if (onError != undefined) onError(response);
                    console.log(" error " + response);
                }
            );
        };
        this.updateExchangesNotifications = function (onSuccess, onError) {

            $http({
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                },
                url: '/api/notifications',
                params: {
                    type: 'exchange'
                }
            }).then(
                function success(response) {
                    //vm.exchangeNotifsNum = response.data;
                    onSuccess(response.data);
                },
                function error(response) {
                    //vm.exchangeNotifsNum = 0;
                    if (onError != undefined) onError(response);
                    console.log(" error " + response);
                }
            );
        };

        this.autoUpdate = function (onSuccessMsg, onSuccessExchange) {
            //console.log("auto update activated");
            this.stopAutoUpdate();
            var updtMSG = this.updateMessagesNotifications;
            var updtEXC = this.updateExchangesNotifications;
            this.updateMessagesNotifications(onSuccessMsg);
            this.updateExchangesNotifications(onSuccessExchange);
            this.interval = setInterval(function () {
                updtMSG(onSuccessMsg);
                updtEXC(onSuccessExchange);
            }, 1000);
        };

        this.stopAutoUpdate = function () {
            if (this.interval !== undefined) {
                clearInterval(this.interval);
                this.interval = undefined;
            }
        };
        return this;
    }]);

