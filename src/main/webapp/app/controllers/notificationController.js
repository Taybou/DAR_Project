/**
 * Created by kadao on 06/11/2016.
 */

angular.module('booxchangeApp')
    .controller('NotificationController', [
        '$location',
        'notificationService',
        'bookService',
        function ($location, notificationService, bookService) {
            var vm = this;
            vm.msgNotifsNum = 0;

            vm.alertNotifications = [];
            vm.alertBooks = [];

            notificationService.autoUpdate(
                function onSuccessMsg(responseData) {
                    vm.msgNotifsNum = responseData;
                }
            );

            vm.getAlertNotifications = function () {
                vm.loadingAlerts = true;
                notificationService.getAlertNotifications(
                    function (notifications) {
                        vm.alertNotifications = notifications;
                        vm.loadingAlerts = false;
                        vm.loadAlertBooks();
                    });
            };

            vm.loadAlertBooks = function () {
                vm.loadingBooks = true && vm.alertNotifications.length > 0;
                vm.alertBooks = [];
                vm.alertNotifications.forEach(function (notification) {
                    bookService.getBook(notification.bookISBN, function (response) {
                        var book = response.data;
                        book.bookISBN = notification.bookISBN;
                        vm.alertBooks.push(response.data);
                        if (vm.alertBooks.length === vm.alertNotifications.length) {
                            vm.loadingBooks = false;
                        }
                    },
                    function (response) {
                        vm.error = response.data;
                    });
                });
            };

            vm.seeAlertNotification = function (book) {
                var foundNotif = vm.alertNotifications.find(function (notification) {
                    return (notification.bookISBN === book.bookISBN);
                });
                notificationService.deleteNotification(foundNotif,
                    function () {
                        $location.path('/book/' + book.bookISBN);
                        vm.getAlertNotifications();
                    }
                );
            };

            setInterval(vm.getAlertNotifications, 45000);

            vm.getAlertNotifications();
        }
    ]);