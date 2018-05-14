<#ftl encoding="utf-8">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Школьный портал</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Школьный портал</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/login">Вход</span></a>
            </li>
            <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Регистрация
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/signUp/student">Как ученик</a>
                    <a class="dropdown-item" href="/signUp/teacher">Как учитель</a>
                </div>
            </li>
        </ul>
    </div>
</nav>

<#if error??>
<div class="alert alert-danger">
    <strong>Ошибка!</strong> ${error}.
</div>
</#if>

<div class="container mt-3">
    <h4 class="form-signin-heading">Зарегистрироваться как ученик</h4>
    <form method="post" class="mt-2">
        <div class="form-group row">
            <label for="invite" class="col-form-label col-2">Пригласительный код*:</label>
            <input type="text" class="form-control col-10" id="invite" name="invite" required maxlength="20">
        </div>
        <div class="form-group row">
            <label for="login" class="col-form-label col-2">Логин*:</label>
            <input type="text" class="form-control col-10" id="login" name="login" required  maxlength="20">
        </div>
        <div class="form-group row">
            <label for="password" class="col-form-label col-2">Пароль*:</label>
            <input type="password" class="form-control col-10" id="password" name="password" required  maxlength="20">
        </div>
        <div class="form-group row">
            <label for="passwordRepeat" class="col-form-label col-2">Повторите пароль*:</label>
            <input type="password" class="form-control col-10" id="passwordRepeat" name="passwordRepeat" required maxlength="20">
        </div>
        <div class="form-group row">
            <label for="name" class="col-form-label col-2">Имя*:</label>
            <input type="text" class="form-control col-10" id="name" name="name" required maxlength="20">
        </div>
        <div class="form-group row">
            <label for="surname" class="col-form-label col-2">Фамилия*:</label>
            <input type="text" class="form-control col-10" id="surname" name="surname" required maxlength="30">
        </div>
        <div class="form-group row">
            <label for="patronymic" class="col-form-label col-2">Отчество:</label>
            <input type="text" class="form-control col-10" id="patronymic" name="patronymic" maxlength="20">
        </div>
        <div class="form-group row">
            <label for="email" class="col-form-label col-2">Email:</label>
            <input type="email" class="form-control col-10" id="email" name="email" maxlength="40">
        </div>
        <div class="form-group row">
            <label for="phoneNumber" class="col-form-label col-2">Номер телефона*:</label>
            <input type="tel" class="form-control col-10" id="phoneNumber" required name="phoneNumber" pattern="^((8|\+7)[\- ]?)?(\(?\d{3}\)?[\- ]?)?[\d\- ]{7,10}$">
        </div>

        <div class="form-group row">
            <label for="classId" class="col-form-label col-2">Выберите класс*:</label>
            <select class="form-control col-10" id="classId" name="classId" required>
            <#list classes as elem>
                <option name="${elem.id}" value="${elem.id}">${elem}</option>
            </#list>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
    </form>
</div>
</body>
</html>