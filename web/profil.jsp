<%--
  Created by IntelliJ IDEA.
  User: islam
  Date: 2016-10-31
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html  ng-app="booxchangeApp">
<head>
    <!-- Loading External CSS files -->
    <link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="bower_components/font-awesome/css/font-awesome.min.css">

    <!-- Loading Internal CSS files -->
    <link rel="stylesheet" href="css/profile.css">

    <script src="bower_components/angular/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.23/angular-sanitize.min.js"></script>

    <!-- App module -->
    <script src="app/app.js"></script>
    <!-- Factory -->
    <script src="app/services/userServices.js"></script>

    <!-- Controllers-->
    <script src="app/controllers/userControllers.js"></script>

    <title>Profile</title>
</head>
<body ng-controller="userControllers">
<div class="container">
    <div class="row">
        <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
            <A href="edit.html" >Edit Profile</A>


        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >


            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">{{profile.userName}}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="https://s-media-cache-ak0.pinimg.com/originals/b1/bb/ec/b1bbec499a0d66e5403480e8cda1bcbe.png" width="300px" height="300px" class="img-circle img-responsive"> </div>

                        <!--<div class="col-xs-10 col-sm-10 hidden-md hidden-lg"> <br>
                          <dl>
                            <dt>DEPARTMENT:</dt>
                            <dd>Administrator</dd>
                            <dt>HIRE DATE</dt>
                            <dd>11/12/2013</dd>
                            <dt>DATE OF BIRTH</dt>
                               <dd>11/12/2013</dd>
                            <dt>GENDER</dt>
                            <dd>Male</dd>
                          </dl>
                        </div>-->
                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td>Prénom:</td>
                                    <td>{{profile.lastName}}</td>
                                </tr>
                                <tr>
                                    <td>Nom</td>
                                    <td>{{profile.firstName}}</td>
                                </tr>
                                <tr>
                                    <td>Sexe</td>
                                    <td>{{profile.gender}}</td>
                                </tr>

                                <tr>
                                <tr>
                                    <td>Adresse</td>
                                    <td>{{profile.address}}</td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td>{{profile.email}}</td>
                                </tr>


                                </tr>

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                <div class="panel-footer">

                    <span class="pull-right">
                            <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>

                    </span>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
