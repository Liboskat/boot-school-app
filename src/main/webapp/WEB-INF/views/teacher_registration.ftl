<#ftl encoding="utf-8">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>School app</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">School app</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</span></a>
            </li>
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Sign up
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/signUp/student">as student</a>
                    <a class="dropdown-item" href="/signUp/teacher">as teacher</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<#if error??>
<div class="alert alert-danger">
    <strong>Error!</strong> ${error}.
</div>
</#if>

<div class="container mt-3">
    <h4 class="form-signin-heading">Sign up as teacher</h4>
<form method="post">
    <div class="form-group row">
        <label for="invite" class="col-form-label col-2">Invite*:</label>
        <input type="text" class="form-control col-10" id="invite" name="invite" required maxlength="20">
    </div>
    <div class="form-group row">
        <label for="login" class="col-form-label col-2">Login*:</label>
        <input type="text" class="form-control col-10" id="login" name="login" required maxlength="20">
    </div>
    <div class="form-group row">
        <label for="password" class="col-form-label col-2">Password*:</label>
        <input type="password" class="form-control col-10" id="password" name="password" required maxlength="20">
    </div>
    <div class="form-group row">
        <label for="passwordRepeat" class="col-form-label col-2">Password repeat*:</label>
        <input type="password" class="form-control col-10" id="passwordRepeat" name="passwordRepeat" required maxlength="20">
    </div>
    <div class="form-group row">
        <label for="name" class="col-form-label col-2">Name*:</label>
        <input type="text" class="form-control col-10" id="name" name="name" required maxlength="20">
    </div>
    <div class="form-group row">
        <label for="surname" class="col-form-label col-2">Surname*:</label>
        <input type="text" class="form-control col-10" id="surname" name="surname" required maxlength="30">
    </div>
    <div class="form-group row">
        <label for="patronymic" class="col-form-label col-2">Patronymic:</label>
        <input type="text" class="form-control col-10" id="patronymic" name="patronymic" maxlength="20">
    </div>
    <div class="form-group row">
        <label for="email" class="col-form-label col-2">Email:</label>
        <input type="email" class="form-control col-10" id="email" name="email" maxlength="40">
    </div>
    <div class="form-group row">
        <label for="phoneNumber" class="col-form-label col-2">Phone number*:</label>
        <input type="tel" class="form-control col-10" required id="phoneNumber" name="phoneNumber" pattern="^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>
</body>
</html>