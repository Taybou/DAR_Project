/**
 * Created by kadao on 16/10/2016.
 */

/*var myapp = angular.module('MsgApp', [
 "ngResource"
 ]);*/

angular.module('booxchangeApp').config(['$resourceProvider', function ($resourceProvider) {
    // Don't strip trailing slashes from calculated URLs
    $resourceProvider.defaults.stripTrailingSlashes = false;
}]);

angular.module('booxchangeApp')
    .controller('messageController', [
            '$routeParams',
        'notificationService',
        'messageService',
        'endroitService',
        '$uibModal',
        '$scope',
        function ($routeParams, notificationService, messageService , endroitService , $uibModal, $scope) {
            var vm = this;

            vm.username = "";
            vm.contacts = {};
            vm.contactsWithNew = {};
            vm.places = {};

            // Modal scope
            vm.placesMap = {};

            vm.fetchMsg = function (username) {

                // vm.msgNotificationExist = false;
                // vm.msgNotificationNumber = notifsNum;

                    vm.username = username || vm.username;
                    username = vm.username;

                vm.messages = messageService.getMessages(vm.username, function () {

                        for (var user in vm.contacts) {
                            if (vm.contacts[user].userName === username) {
                                vm.contacts[user].active = "true";
                            } else {
                                vm.contacts[user].active = undefined;
                            }
                        }
                    vm.updateContactsNotification();
                    vm.getPlaces(username);
                    });
                };

            vm.sendMsg = function () {
                    if (vm.username != "") {
                        messageService.sendMessage(
                            {username: vm.username, message: vm.message},
                            function success(response) {
                                vm.fetchMsg();
                                vm.message = "";
                            },
                            function error(response) {
                                console.log("message not sent");
                            }
                        );
                    }
                };

            vm.updateContactsNotification = function () {
                return messageService.updateContactsWithNew(function onSuccess(response) {

                    for (var user in vm.contacts) {

                        if (response[vm.contacts[user].userName] != undefined) {
                            vm.contacts[user].newMsg = response[vm.contacts[user].userName];
                        } else {
                            vm.contacts[user].newMsg = undefined;
                        }
                    }
                    return vm.contacts;
                });

            };

            // calling the getContacts to initialize the view
            // notificationService.stopAutoUpdate();
            // notificationService.removeMessageNotificaiton();
            vm.contacts = messageService.getContacts(function () {
                vm.updateContactsNotification();
                setInterval(vm.updateContactsNotification, 1000);
            });

           vm.getPlaces = function () {
               endroitService.getPlaces(vm.username,
                   function success(response) {
                       vm.places = response.data.slice(0, 5);
                       console.log(vm.places);
                   },
                   function error(response) {
                       console.log("user not exist");
                   }
               );
           }

            vm.open = function () {
                vm.placesMap = angular.copy(vm.places);
                vm.modal = $uibModal.open({
                    templateUrl: 'places.html',
                    scope: $scope
                });
            };

            vm.close = function () {
                vm.placesMap.errors = null;
                vm.modal.dismiss();
            };



            }
        ]
    );