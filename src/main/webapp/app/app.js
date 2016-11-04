/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */

angular.module('booxchangeApp', [
        'ngRoute', "ngResource"
    ])
    .config([
        '$routeProvider',
        function ($routeProvider) {

            $routeProvider
                .when('/home', {
                    templateUrl: 'views/search.html',
                    controller: 'BookSearchController',
                    controllerAs: 'bookSearchCtrl'
                })
                .when('/book/:bookISBN', {
                    templateUrl: 'views/book.html',
                    controller: 'BookController',
                    controllerAs: 'bookCtrl'
                })
                .when('/messages/:username', {
                    templateUrl: 'views/messages.html',
                    controller: 'messageController',
                    controllerAs: 'MsgCtrl'
                })
                .otherwise('/home');
        }
    ]);
