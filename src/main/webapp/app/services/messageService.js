/**
 * Created by kadao on 05/11/2016.
 */



angular.module('booxchangeApp')
    .factory('messageService', [
        '$http',
        "$resource",
        function ($http, $resource) {

            this.getMessages = function (username, onSucess) {

                var Message = $resource('/api/messages?type=messages&receiver=:receiver', {
                    'query': {
                        method: 'GET',
                        isArray: true
                    }
                });
                return Message.query({receiver: username}, function (response) {

                    for (var index in response) {
                        var value = response[index];
                        if (value.timeStamp !== undefined) {
                            value.time = (new Date(value.timeStamp)).toDateString();
                            value.moment = moment(value.timeStamp).fromNow();
                            if (value.from.userName === username) {
                                value.pullDirection = "left";
                                value.inverseDirection = "right";
                                value.color = "FA6F57";
                            } else {
                                value.pullDirection = "right";
                                value.inverseDirection = "left";
                                value.color = "55C1E7";
                            }
                            value.initials = value.from.firstName.toUpperCase()[0] + value.from.lastName.toUpperCase()[0];
                        }
                    }
                    onSucess();
                    return response;
                });

            }

            this.sendMessage = function (options, onSucess, onError) {
                $http({
                    method: 'POST',
                    url: '/api/messages',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: {
                        receiver: options.username,
                        msg: options.message
                    }
                }).then(
                    function success(response) {
                        onSucess(response);
                    },
                    function error(response) {
                        onError(response);
                    }
                );
            }

            this.getContacts = function (onSuccess) {
                var Users = $resource('/api/messages?type=contacts', {
                    'query': {
                        method: 'GET',
                        isArray: true
                    }
                });
                return Users.query({}, function (response) {
                    var result = [];
                    for (var user in response) {
                        if (response.hasOwnProperty(user) && response[user].userName != undefined) {
                            response[user].firstName = response[user].firstName.toUpperCase()[0] + response[user].firstName.substring(1);
                            response[user].lastName = response[user].lastName.toUpperCase()[0] + response[user].lastName.substring(1);
                            result.push(response[user]);
                        }
                    }

                    if (onSuccess != undefined) onSuccess();
                    return result;
                });
            };

            this.updateContactsWithNew = function (onSuccess) {
                var Users = $resource('/api/messages?type=contactsWithNew', {'get': {method: 'GET'}});
                return Users.get({}, function (response) {

                    if (onSuccess != undefined) return onSuccess(response);
                    return result;
                });
            };

            return this;
        }]);

