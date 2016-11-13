<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr" ng-app="booxchangeApp">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>BooXchange</title>

    <!-- Loading External CSS files -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/bower_components/angular-google-places-autocomplete/src/autocomplete.css">
    <!--***************************-->

    <!-- Loading Internal CSS files -->
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/book-directive.css">
    <link rel="stylesheet" href="css/profile.css">
    <!--<link rel="stylesheet" href="css/messages.css">
    <link rel="stylesheet" href="css/chat.css">-->
    <link rel="stylesheet" href="css/messages.css">
    <link rel="stylesheet" href="css/chat.css">
    <!--****************************-->

    <!-- Loading AngularJS Modules -->
    <script src="bower_components/angular/angular.min.js"></script>
    <script src="bower_components/angular-route/angular-route.min.js"></script>
    <script src="bower_components/angular-animate/angular-animate.min.js"></script>
    <script src="bower_components/angular-resource/angular-resource.min.js"></script>
    <script src="bower_components/angular-cookies/angular-cookies.min.js"></script>
    <script src="bower_components/angular-touch/angular-touch.min.js"></script>
    <script src="bower_components/moment/moment.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyBgKQMp-a7NqPJkXH7-m4UuSMbxwEzleRU"></script>
    <script src="/bower_components/angular-google-places-autocomplete/src/autocomplete.js"></script>
    <!-- ***************************************************** -->

    <!-- Loading AngularJS External Modules -->
    <script src="bower_components/angular-bootstrap/ui-bootstrap.min.js"></script>
    <script src="bower_components/angular-bootstrap/ui-bootstrap-tpls.min.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
    <!--******************************************************-->

    <!-- Loading Internal Angular Scripts and Modules -->
    <!-- App module -->
    <script src="app/app.js"></script>
    <script src="app/authenticationApp.js"></script>
    <!-- Services -->
    <script src="app/services/bookService.js"></script>
    <script src="app/services/userService.js"></script>
    <script src="app/services/notificationService.js"></script>
    <script src="app/services/messageService.js"></script>
    <script src="app/services/authenticationServices.js"></script>
    <script src="app/services/alertsService.js"></script>
    <script src="app/services/endroitService.js"></script>
    <!-- Directives -->
    <script src="app/directives/bookDirective.js"></script>
    <!-- Controllers-->
    <script src="app/controllers/bookSearchController.js"></script>
    <script src="app/controllers/bookController.js"></script>
    <script src="app/controllers/messageController.js"></script>
    <script src="app/controllers/userProfileController.js"></script>
    <script src="app/controllers/notificationController.js"></script>
    <script src="app/controllers/authenticationControllers.js"></script>
    <script src="app/controllers/alertsController.js"></script>
    <!--****************************************************** -->

</head>
<body ng-controller="AlertsController as alertCtrl">

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand" href="#">BooXChange</a>
        </div>

        <div class="collapse navbar-collapse" id="navbar-collapse-1"
             ng-controller="NotificationController as NotifCtrl">
            <ul class="nav navbar-nav navbar-right">

                <li uib-dropdown>
                    <a href id="simple-dropdown" uib-dropdown-toggle>
                        <i class="fa fa-2x fa-bell-o" aria-hidden="true"
                                   ng-class="{ 'alerted': NotifCtrl.alertNotifications.length>0 }"></i>
                        <span ng-show="NotifCtrl.alertNotifications.length>0"
                              style="color:#d35400">{{NotifCtrl.alertNotifications.length}}</span>
                    </a>
                    <ul class="dropdown-menu" uib-dropdown-menu aria-labelledby="simple-dropdown">
                        <div class="alert-loading" ng-show="NotifCtrl.loadingAlerts || NotifCtrl.loadingBooks">
                            <i class='fa fa-1x fa-circle-o-notch fa-spin' ></i>
                        </div>
                        <li class="alert-notification" ng-repeat="book in NotifCtrl.alertBooks" ng-click="NotifCtrl.seeAlertNotification(book)">
                            <span class="alert-book-image">
                                <img  ng-src="{{book.volumeInfo.imageLinks.smallThumbnail}}" alt="book image">
                            </span>
                            <span class="alert-book-text">Le livre <span class="alert-book-title">{{book.volumeInfo.title}}</span> est disponible</span>
                        </li>
                        <li class="alert-notification"
                            ng-show="NotifCtrl.alertBooks.length == 0 && !NotifCtrl.loadingBooks"><p> Aucune nouvelle
                            alerte </p></li>
                    </ul>
                </li>
                <li>
                    <a href="#/messages">
                        <i class="fa fa-2x fa-envelope-o" aria-hidden="true"
                           ng-class="{ 'alerted': NotifCtrl.msgNotifsNum>0 }"
                        ></i>
                        <span ng-show="NotifCtrl.msgNotifsNum>0" style="color:#d35400">{{NotifCtrl.msgNotifsNum}}</span>
                    </a>
                </li>
                <li>
                    <a href="#/profile"><i class="fa fa-2x fa-user-circle-o" aria-hidden="true"></i> Profile</a>
                </li>
                <li ng-controller="SignoutController as SignoutCtrl" ng-click="SignoutCtrl.signout()">
                    <a class="signout">
                        <i ng-show="SignoutCtrl.loading" class="fa fa-1x fa-circle-o-notch fa-spin"></i>
                        <i ng-hide="SignoutCtrl.loading" class="fa fa-2x fa-sign-out"></i>
                        Déconnexion
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- This is the main view. it changes depending on the route-->
<div ng-view class="main-view"></div>



</div>

<div class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <p class="navbar-text pull-left">BooXChange © 2016 - Créé par Allaoua, Islam, Omar et Tayeb
        </p>

        <a href="https://github.com/Taybou/DAR_Project" target="_blank" class="navbar-btn btn-info btn pull-right">
            <span class="fa fa-github"></span>  Voir Sur GitHub</a>
    </div>
</div>
</body>
</html>