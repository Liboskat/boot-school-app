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
<a class="navbar-brand" href="/">Student diary week ${week}</a>
<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="/student/timetable">Timetable</a>
        </li>
        <li class="nav-item active">
            <a class="nav-link" href="/student/diary">Diary <span class="sr-only">(current)</span></a>
        </li>
    </ul>

    <span class="nav-item">
        <a class="nav-link" href="/logout">Logout</a>
    </span>
</div>
</nav>
<ul class="pagination w-50 mt-3 mx-auto">
    <li class="page-item mx-auto w-50"><a class="page-link" href="/student/diary/${week - 1}">Previous week</a></li>
    <li class="page-item mx-auto w-50"><a class="page-link" href="/student/diary/">Current week</a></li>
    <li class="page-item mx-auto w-50"><a class="page-link" href="/student/diary/${week + 1}">Next week</a></li>
</ul>

<table class="table table-hover">
    <thead class="thead-light">
    <tr>
        <th scope="col">Weekday</th>
        <th scope="col">Time</th>
        <th scope="col">Lesson</th>
        <th scope="col">Homework</th>
        <th scope="col">Mark</th>
    </tr>
    </thead>
    <tbody>
        <#list diary as diaryUnit>
        <tr>
            <td scope="row">${diaryUnit.lessonDto.weekday}</td>
            <td>${diaryUnit.lessonDto.startTime} - ${diaryUnit.lessonDto.endTime}</td>
            <td title="${diaryUnit.lessonDto}">
                ${diaryUnit.lessonDto.subject}
            </td>
            <td>
            <#if diaryUnit.homework??>
                ${diaryUnit.homework}
            </#if>
            </td>
            <td>
            <#if diaryUnit.mark??>
                ${diaryUnit.mark}
            </#if>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</body>
</html>