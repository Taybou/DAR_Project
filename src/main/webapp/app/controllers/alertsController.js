/**
 * Created by Al on 07-Nov-16.
 */

angular.module('booxchangeApp')
    .controller('AlertsController',[
        'alertsService',
        function (alertsService) {

            var vm = this;

            vm.alerts = [];

            vm.loadAlerts = function () {
                alertsService.getAlerts(
                    function (alerts) {
                        vm.alerts = alerts;
                    },
                    function (error) {
                        vm.error = error;
                    }
                );
            };

            vm.createAlert = function (bookISBN) {
                alertsService.createAlert(bookISBN,
                    function (alert) {
                        vm.alerts.push(alert);
                    },
                    function (error) {
                        vm.error = error;
                    });
            };

            vm.deleteAlert = function (bookISBN) {
                alertsService.deleteAlert(bookISBN, function () {
                    var foundAlert = vm.alerts.findIndex(function (alert) {
                        return (alert.bookISBN === bookISBN);
                    });
                    vm.alerts.splice(foundAlert, 1);
                }, function (error) {
                    vm.error = error;
                })
            };

            vm.hasAlert = function (bookISBN) {
                // return bookISBN in alerts
                return vm.alerts.find(function (alert) {
                    return (alert.bookISBN === bookISBN);
                });
            };

            vm.loadAlerts();

        }
    ]);