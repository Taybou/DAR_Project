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
<body ng-controller="SignupController as signupCtrl">

<div class="container">
    <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="panel-heading">
                <div class="panel-title text-center">
                    <h1 class="title">Inscription à BooXChange</h1>
                    <hr />
                </div>
            </div>

            <form novalidate class="form-horizontal" name="userForm">

                <div class="form-group" ng-class="{ 'has-error' : (userForm.firstName.$invalid && userForm.firstName.$dirty) || signupCtrl.error.firstName }">
                    <label for="firstname" class="cols-sm-2 control-label">Prénom</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" name="firstName"
                                   required class="form-control"
                                   ng-model="signupCtrl.user.firstName"
                                   ng-model-options="{ updateOn: 'blur' }"
                                   notificationId="firstname"  placeholder="Entrer votre Nom"/>
                        </div>
                    </div>
                </div>

                <div class="form-group" ng-class="{ 'has-error' : (userForm.lastName.$invalid && userForm.lastName.$dirty) || signupCtrl.error.lastName }">
                    <label for="lastname" class="cols-sm-2 control-label">Nom</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" name="lastName"
                                   required class="form-control"
                                   ng-model="signupCtrl.user.lastName"
                                   ng-model-options="{ updateOn: 'blur' }"
                                   notificationId="lastname"
                                   placeholder="Enter votre Prénom"/>
                        </div>
                    </div>
                </div>

                <div class="form-group" ng-class="{ 'has-error' : (userForm.email.$invalid && userForm.email.$dirty) || signupCtrl.error.email }">
                    <label for="email" class="cols-sm-2 control-label">Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input type="email" name="email"
                                   required class="form-control"
                                   ng-model="signupCtrl.user.email"
                                   ng-model-options="{ updateOn: 'blur' }"
                                   notificationId="email"
                                   placeholder="Entrer votre Email"/>
                        </div>
                    </div>
                </div>

                <div class="alert alert-danger" role="alert" ng-show="userForm.email.$invalid && userForm.email.$dirty"> Veuillez entrer une adresse email valide </div>

                <div class="form-group" ng-class="{ 'has-error' : (userForm.userName.$invalid && userForm.userName.$dirty) || signupCtrl.error.userName }">
                    <label for="username" class="cols-sm-2 control-label">Nom d'utilisateur</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                            <input type="text" name="userName"
                                   required class="form-control"
                                   ng-model="signupCtrl.user.userName"
                                   ng-model-options="{ updateOn: 'blur' }"
                                   notificationId="username"  placeholder="Entrer un Nom d'Utilisateur"/>
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
                                   ng-model="signupCtrl.user.password"
                                   ng-model-options="{ updateOn: 'blur' }"
                                   notificationId="password"  placeholder="Entrer un Mot de passe"/>
                        </div>
                    </div>
                </div>

                <div class="form-group"
                     ng-class="{ 'has-error' : (signupCtrl.user.password !== signupCtrl.user.confirmedPassword || userForm.confirmedPassword.$invalid) && userForm.confirmedPassword.$dirty}">
                    <label for="confirm" class="cols-sm-2 control-label">Confirmer le mot de passe</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" name="confirmedPassword"
                                   required class="form-control"
                                   ng-model="signupCtrl.user.confirmedPassword"
                                   notificationId="confirm"  placeholder="Confirmer le Mot de passe"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button class="btn btn-primary btn-lg btn-block login-button"
                            ng-disabled="userForm.$invalid || signupCtrl.loading || signupCtrl.createdUser"
                            ng-click="signupCtrl.submit()">
                        <i class='fa fa-circle-o-notch fa-spin' ng-show="signupCtrl.loading"></i> S'inscrire
                    </button>
                </div>

                <div class="alert alert-danger"
                     role="alert"
                     ng-show="signupCtrl.error">
                    <span ng-show="signupCtrl.error.message">{{signupCtrl.error.message}}</span>
                    <ul ng-hide="signupCtrl.error.message">
                        <li ng-repeat="error in signupCtrl.error">
                            {{error.message}}
                        </li>
                    </ul>
                </div>

                <div class="alert alert-success"
                     role="alert"
                     ng-show="signupCtrl.createdUser">
                    Votre compte a bien été crée, vous pouvez désormais vous <a href="/signin">connecter</a>
                    <a href="/"></a>
                </div>

                <div class="login-register">
                    <a href="/signin">Connexion</a>
                </div>
            </form>
        </div>
        <div class="col-sm-3"></div>
        
    </div>
</div>

</body>
</html>