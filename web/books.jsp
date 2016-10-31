<!DOCTYPE html>
<html ng-app="booxchangeApp">
<head>
    <meta http-equiv='Content-Type' content='text/html' charset="UTF-8">

    <!-- Loading External CSS files -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">
    <!--***************************-->

    <!-- Loading Internal CSS files -->
    <link rel="stylesheet" href="css/style.css">

    <!-- Loading AngularJS Modules -->
    <script src="bower_components/angular/angular.min.js"></script>
    <script src="bower_components/angular-modal-service/dst/angular-modal-service.min.js"></script>

    <!-- Loading Internal Angular Scripts and Modules -->
    <!-- App module -->
    <script src="app/app.js"></script>
    <!-- Services -->
    <script src="app/services/booksearchService.js"></script>
    <script src="app/services/bookaddService.js"></script>
    <script src="app/services/bookDetailsFactory.js"></script>

    <!-- Directives -->
    <!-- Controllers-->
    <script src="app/controllers/bookController.js"></script>

    <title>BooXchange</title>
</head>

<body>

<div ng-include="'views/booksView.jsp'"></div>


</body>
</html>