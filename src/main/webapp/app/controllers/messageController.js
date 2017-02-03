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
                vm.loadingMessages = true;
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
                    vm.loadingMessages = false;
                    vm.updateContactsNotification();

                });
            };

            vm.sendMsg = function () {

                    console.log(vm.message);
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
            vm.loadingContacts = true;
            vm.contacts = messageService.getContacts(function () {
                vm.loadingContacts = false;
                vm.updateContactsNotification();
                setInterval(vm.updateContactsNotification, 45000);
            });

            vm.getPlaces = function (username, onSucess) {
               endroitService.getPlaces(vm.username,
                   function success(response) {
                       vm.places = response.data.slice(0, 5);
                       //console.log(vm.places);
                       onSucess();
                   },
                   function error(response) {
                       console.log("user does not exist");
                   }
               );
           }

            vm.open = function () {
                vm.loadingPlaces = true;
                vm.getPlaces(vm.username, function () {
                    vm.loadingPlaces = false;
                    vm.placesMap = angular.copy(vm.places);
                });
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