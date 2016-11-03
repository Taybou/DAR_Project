/**
 * Created by kadao on 16/10/2016.
 */

var myapp = angular.module('MsgApp', [
    "ngResource"
]);

myapp.config(['$resourceProvider', function($resourceProvider) {
    // Don't strip trailing slashes from calculated URLs
    $resourceProvider.defaults.stripTrailingSlashes = false;
}]);

myapp.controller("MsgCtrl",  ["$scope","$resource",'$http', function($scope, $resource, $http) {
    $scope.username='';
    $scope.contacts = {};
    $scope.getContacts = function () {
        var Users = $resource('/api/users?relatedTo=:sender', {'get':  {method:'GET'}});
        $scope.contacts = Users.get({sender : $scope.myUsername}, function(response) {
            var result = [];


            for (var user in response) {
                if (response.hasOwnProperty(user)) {
                    response[user].firstName = response[user].firstName.toUpperCase()[0] + response[user].firstName.substring(1);
                    response[user].lastName = response[user].lastName.toUpperCase()[0] + response[user].lastName.substring(1);
                    result.push(response[user]);
                }
            }

            console.log(result);
            console.log(result[0]);
            $scope.fetchMsg (result[0]);
            return result;
        });
    };

    $scope.fetchMsg = function (username) {
        $scope.username = username || $scope.username;
        username =$scope.username;
        var localMyUsername = $scope.myUsername;
        var Message = $resource('/api/messages?sender=:sender&receiver=:receiver', {'query':  {method:'GET', isArray:true}});
        $scope.messages = Message.query({sender : localMyUsername, receiver:username}, function(response) {
            console.log(response);
            for( var index in response){
                var value = response[index];
                if(value.timeStamp !== undefined) {
                    value.time = (new Date(value.timeStamp)).toDateString();
                    value.moment = moment(value.timeStamp).fromNow();
                    if(value.from.userName === localMyUsername) {
                        value.pullDirection = "right";
                        value.inverseDirection = "left";
                        value.color = "55C1E7";
                    } else {
                        value.pullDirection = "left";
                        value.inverseDirection = "right";
                        value.color = "FA6F57";
                    }
                    value.initials = value.from.firstName.toUpperCase()[0]+value.from.lastName.toUpperCase()[0];
                }
                console.log("Index = " + index + " value = " + value);
            }

            for(var user in $scope.contacts) {
                console.log( $scope.contacts[user].userName)
                if($scope.contacts[user].userName === username) {
                    $scope.contacts[user].active="true";
                } else {
                    $scope.contacts[user].active = undefined;
                }
            }
            console.log(username);
            console.log($scope.contacts);
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
        //         sender : $scope.myUsername,
        //         receiver : $scope.username
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
    $scope.sendMsg = function() {
        // var Message = $resource('/api/messages?from=:myUserName&to=:userName&content=:msg', {});
        // Message.post(
        //     {
        //         myUsername : $scope.myUserName,
        //         userName : $scope.userName,
        //         msg : $scope.message
        //     }, function () {
        //         $scope.fetchMsg();
        //         $scope.message = "";
        //     });
        if($scope.username != "")
        $http({
            method:'POST',
            url: '/api/messages',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                sender : $scope.myUsername,
                receiver : $scope.username,
                msg : $scope.message
            }
        }).then(
            function success(response) {
                console.log(response);
                $scope.fetchMsg();
                $scope.message = "";
            },
            function error(response) {
                console.log(response);
            }
        );

    };

    /**
     * temporary
     */
    $scope.myUsername = "ramo";
    $scope.getContacts();


}]);