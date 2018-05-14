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
    <a class="navbar-brand" href="/">Teacher</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="/teacher/timetable">Timetable <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/teacher/lesson">Lesson</a>
        </li>
    </ul>

    <span class="nav-item">
        <a class="nav-link" href="/logout">Logout</a>
    </span>
</nav>


<table class="table table-hover">
    <thead class="thead-light">
        <tr>
            <th scope="col" class="w-25">Weekday</th>
            <th scope="col" class="w-25">Time</th>
            <th scope="col">Lesson</th>
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
        </tr>
        </#list>
    </tbody>
</table>
</body>
</html>