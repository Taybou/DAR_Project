/**
 * Created by kadao on 06/11/2016.
 */

angular.module('booxchangeApp')
    .controller('NotificationController', [
        'notificationService',
        function (notificationService) {
            var vm = this;
            vm.msgNotifsNum = 0;
            vm.exchangeNotifsNum = 0;
            notificationService.autoUpdate(
                function onSuccessMsg(responseData) {
                    vm.msgNotifsNum = responseData;
                },
                function onSuccessExchange(responseData) {
                    vm.exchangeNotifsNum = responseData;
                }
            );
        }
    ]);