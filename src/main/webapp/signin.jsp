<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr" ng-app="authApp">
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

    <!-- Loading Internal Angular Scripts and Modules -->
    <!-- App module -->
    <script src="app/authenticationApp.js"></script>
    <!-- Services -->
    <script src="app/services/authenticationServices.js"></script>
    <!-- Directives -->
    <!-- Controllers-->
    <script src="app/controllers/authenticationControllers.js"></script>
    <!--****************************************************** -->

    <title>BooXChange</title>
</head>
<body ng-controller="SigninController as signinCtrl">

<div class="container">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="panel-heading">
                <div class="panel-title text-center">
                    <h1 class="title">Connexion à BooXChange</h1>
                    <hr />
                </div>
            </div>

            <form novalidate class="form-horizontal" name="userForm">

                <div class="form-group" ng-class="{ 'has-error' : userForm.userName.$invalid && userForm.userName.$dirty }">
                    <label for="username" class="cols-sm-2 control-label">Nom d'utilisateur</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input type="text" name="userName"
                                   required class="form-control"
                                   ng-model="signinCtrl.userData.userName"
                                   id="username"  placeholder="Entrer un Nom d'Utilisateur"/>
                        </div>
                    </div>
                </div>

                <div class="form-group" ng-class="{ 'has-error' : userForm.password.$invalid && userForm.password.$dirty }">
                    <label for="password" class="cols-sm-2 control-label" >Mot de passe</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" name="password"
                                   required class="form-control"
                                   ng-model="signinCtrl.userData.password"
                                   id="password"  placeholder="Entrer un Mot de passe"/>
                        </div>
                    </div>
                </div>

                <div class="alert alert-danger"
                     role="alert"
                     ng-show="signinCtrl.error">
                    {{signinCtrl.error.message}}
                </div>

                <div class="form-group ">

                    <button class="btn btn-primary btn-lg btn-block login-button"
                            ng-disabled="userForm.$invalid || signinCtrl.loading"
                            ng-click="signinCtrl.submit()">
                        <i class='fa fa-circle-o-notch fa-spin' ng-show="signinCtrl.loading"></i> Connexion
                    </button>
                </div>

                <div class="login-register">
                    <a href="/signup">S'inscrire</a>
                </div>
            </form>

        </div>
        <div class="col-sm-3"></div>

    </div>
</div>

</body>
</html>