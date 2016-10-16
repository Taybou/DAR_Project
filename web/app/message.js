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
    $scope.username='Cptn Jack Sparrow';
    $scope.fetchMsg = function () {

        var Message = $resource('/api/messages?sender=:sender&receiver=:receiver', {'query':  {method:'GET', isArray:true}});
        $scope.messages = Message.query({sender : $scope.myUsername, receiver:$scope.username}, function(response) {
            console.log(response);
            for( var index in response){
                var value = response[index];
                if(value.timeStamp !== undefined) {
                    value.time = (new Date(value.timeStamp)).toDateString();
                    value.moment = moment(value.timeStamp).fromNow();
                }
                console.log("Index = " + index + " value = " + value);
            }
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
}]);