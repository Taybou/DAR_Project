/**
 * Created by kadao on 05/11/2016.
 */


angular.module('booxchangeApp')
    .factory('notificationService', ['$http', "$rootScope", function ($http, $rootScope) {

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

        this.updateNotifications = function () {
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
                    $rootScope.msgNotifsNum = response.data;
                },
                function error(response) {
                    $rootScope.msgNotifsNum = 0;
                    console.log(" error " + response);
                }
            );
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
                    $rootScope.exchangeNotifsNum = response.data;
                },
                function error(response) {
                    $rootScope.exchangeNotifsNum = 0;
                    console.log(" error " + response);
                }
            );
        }

        this.removeMessageNotificaiton = function () {
            $rootScope.msgNotifsNum = 0;
        }
        this.removeExchangeNotificaiton = function () {
            $rootScope.exchangeNotifsNum = 0;
        }

        this.autoUpdate = function () {
            //console.log("auto update activated");
            this.stopAutoUpdate();
            this.updateNotifications();
            this.interval = setInterval(this.updateNotifications, 1000);
        };

        this.stopAutoUpdate = function () {
            if (this.interval !== undefined) {
                clearInterval(this.interval);
                this.interval = undefined;
            }
        }
        return this;
    }]);

