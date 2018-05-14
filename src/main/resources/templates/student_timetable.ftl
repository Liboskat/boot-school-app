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
    <a class="navbar-brand" href="/">Личный кабинет ученика</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/student/timetable">Расписание <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/student/diary">Дневник</a>
            </li>
        </ul>
    </div>

    <span class="nav-item">
        <a class="nav-link" href="/logout">Выйти</a>
    </span>
</nav>

<table class="table table-hover">
    <thead class="thead-light">
    <tr>
        <th scope="col" class="w-25">День недели</th>
        <th scope="col" class="w-10">Время</th>
        <th scope="col" class="w-10">Урок</th>
        <th scope="col" class="w-10">Кабинет</th>
        <th scope="col">Преподаватель</th>
    </tr>
    </thead>
    <tbody>
        <#list lessons as lesson>
        <tr>
            <td scope="row">${lesson.weekday}</td>
            <td>${lesson.startTime} - ${lesson.endTime}</td>
            <td title="${lesson}">
                ${lesson.subject}
            </td>
            <td>${lesson.room}</td>
            <td>${lesson.teacher}</td>
        </tr>
        </#list>
    </tbody>
</table>
</body>
</html>