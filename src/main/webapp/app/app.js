/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */

angular.module('booxchangeApp', [
        'ngRoute'
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
                .otherwise('/home');
        }
    ]);
