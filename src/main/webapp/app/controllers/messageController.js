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
            "$resource",
            '$http',
            '$routeParams',
            function ( $resource, $http, $routeParams) {
                var vm = this;
                vm.username = "";
                vm.myUsername = $routeParams.username;
                vm.contacts = {};
                vm.getContacts = function () {
                    var Users = $resource('/api/users?relatedTo=:sender', {'get': {method: 'GET'}});
                    vm.contacts = Users.get({sender: vm.myUsername}, function (response) {
                        var result = [];

                        console.log(response);
                        for (var user in response) {
                            if (response.hasOwnProperty(user) && response[user].userName != undefined  ) {
                                console.log(user);
                                response[user].firstName = response[user].firstName.toUpperCase()[0] + response[user].firstName.substring(1);
                                response[user].lastName = response[user].lastName.toUpperCase()[0] + response[user].lastName.substring(1);
                                result.push(response[user]);
                            }
                        }

                        console.log(result);
                        console.log(result[0]);
                        vm.fetchMsg(result[0]);
                        return result;
                    });
                };

                vm.fetchMsg = function (username) {
                    vm.username = username || vm.username;
                    username = vm.username;
                    var localMyUsername = vm.myUsername;
                    var Message = $resource('/api/messages?sender=:sender&receiver=:receiver', {
                        'query': {
                            method: 'GET',
                            isArray: true
                        }
                    });
                    vm.messages = Message.query({sender: localMyUsername, receiver: username}, function (response) {
                        console.log(response);
                        for (var index in response) {
                            var value = response[index];
                            if (value.timeStamp !== undefined) {
                                value.time = (new Date(value.timeStamp)).toDateString();
                                value.moment = moment(value.timeStamp).fromNow();
                                if (value.from.userName === localMyUsername) {
                                    value.pullDirection = "right";
                                    value.inverseDirection = "left";
                                    value.color = "55C1E7";
                                } else {
                                    value.pullDirection = "left";
                                    value.inverseDirection = "right";
                                    value.color = "FA6F57";
                                }
                                value.initials = value.from.firstName.toUpperCase()[0] + value.from.lastName.toUpperCase()[0];
                            }
                            console.log("Index = " + index + " value = " + value);
                        }

                        for (var user in vm.contacts) {
                            console.log(vm.contacts[user].userName)
                            if (vm.contacts[user].userName === username) {
                                vm.contacts[user].active = "true";
                            } else {
                                vm.contacts[user].active = undefined;
                            }
                        }
                        console.log(username);
                        console.log(vm.contacts);
                        // for(var msg in response) {
                        //     console.log(msg);
                        //     msg.time = (new Date(msg.timeStamp)).toTimeString();
                        //     msg.moment = moment(msg.time);
                        // }
                        return response;
                    });

                    // $http({
                    //     method:'GET',
                    //     url: '/api/messages',
                    //     headers: {
                    //         'Content-Type': 'application/json'
                    //     },
                    //     data: {
                    //         sender : vm.myUsername,
                    //         receiver : vm.username
                    //     }
                    // }).then(
                    //     function success(response) {
                    //         console.log(response)
                    //     },
                    //     function error(response) {
                    //         console.log(response);
                    //     }
                    // );

                };
                vm.sendMsg = function () {
                    // var Message = $resource('/api/messages?from=:myUserName&to=:userName&content=:msg', {});
                    // Message.post(
                    //     {
                    //         myUsername : vm.myUserName,
                    //         userName : vm.userName,
                    //         msg : vm.message
                    //     }, function () {
                    //         vm.fetchMsg();
                    //         vm.message = "";
                    //     });
                    if (vm.username != "")
                        $http({
                            method: 'POST',
                            url: '/api/messages',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: {
                                sender: vm.myUsername,
                                receiver: vm.username,
                                msg: vm.message
                            }
                        }).then(
                            function success(response) {
                                console.log(response);
                                vm.fetchMsg();
                                vm.message = "";
                            },
                            function error(response) {
                                console.log(response);
                            }
                        );

                };

                /**
                 * temporary
                 */
                //vm.myUsername = "ramo";
                vm.getContacts();


            }
        ]
    );