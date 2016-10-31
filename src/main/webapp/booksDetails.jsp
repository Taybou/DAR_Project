<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 2016-10-19
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html  ng-app="booxchangeApp">
<head>
    <title>Book Details</title>
    <meta http-equiv='Content-Type' content='text/html' charset="UTF-8">

    <!-- Loading External CSS files -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">


    <!--***************************-->

    <!-- Loading Internal CSS files -->
    <link rel="stylesheet" href="css/bookStyle.css">

    <!-- Loading AngularJS Modules -->
    <script src="bower_components/angular/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular-sanitize.min.js"></script>



    <!-- App module -->
    <script src="app/app.js"></script>
    <!-- Factory -->
    <script src="app/services/bookDetailsFactory.js"></script>
    <!-- Services
    <script src="app/services/bookDetailsServices.js"></script>-->

    <!-- Controllers-->
    <script src="app/controllers/bookDetailsControllers.js"></script>

    <!--****************************************************** -->

</head>
<body>
<div ng-include="'views/bookDetails.html'"></div>

</body>
</html>
