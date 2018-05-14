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
    <a class="navbar-brand" href="/">Личный кабинет администратора</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/admin/invite">Пригласить пользователя <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>

    <span class="nav-item">
        <a class="nav-link" href="/logout">Выйти</a>
    </span>
</nav>

<form method="post" class="container-fluid form-inline mx-5 mt-3 mb-2">
    <div class="form-group">
        <label for="role">Выберите роль:</label>
        <select name="role" id="role" class="form-control mx-3" required>
            <option value="STUDENT" name="STUDENT">Ученик</option>
            <option value="TEACHER" name="TEACHER">Учитель</option>
        </select>
    </div>
    <button type="submit" class="btn btn-default ml-2">Сгенерировать</button>
</form>
<#if invite_code??>
    <div class="alert alert-success">
        <strong>Дело сделано!</strong> Пригласительный код: <mark>${invite_code}</mark>
    </div>
</#if>
</body>
</html>