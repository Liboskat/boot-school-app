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
    <a class="navbar-brand" href="/">Личный кабинет учителя</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/teacher/timetable">Расписание</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/teacher/lesson">Работа на уроке <span class="sr-only">(current)</span></a>
            </li>
        </ul>
    </div>

    <span class="nav-item">
        <a class="nav-link" href="/logout">Выйти</a>
    </span>
</nav>
<div class="container">
    <div class="row mt-3 mb-3">
        <div class="col">
            <form method="get" class="form-inline row">
                    <label for="date" class="col-form-label col-2">Выберите дату</label>
                    <input type="date" class="form-control col-6" name="date" id="date" value="${date}">
                    <button type="submit" class="btn btn-default ml-3 col-2">Выбрать</button>
            </form>
        </div>
    </div>
    <div class="row mb-3">
    <#if date??>
        <div class="col">
            <form method="get" class="form-inline row">
                <input type="hidden" name="date" value="${date}">
                <label for="lessonId" class="col-form-label col-2">Выберите урок</label>
                <select name="lessonId" id="lessonId" class="form-control col-6" required>
                    <#if lessons?has_content>
                        <#list lessons as lesson>
                            <#if lessonId?? && lesson.id?? && lessonId == lesson.id>
                                <option value="${lesson.id}" name="${lesson.id}" selected>${lesson.subject}, ${lesson.startTime}, ${lesson.studentClass}</option>
                            <#elseif lesson.id??>
                                <option value="${lesson.id}" name="${lesson.id}">${lesson.subject}, ${lesson.startTime}, ${lesson.studentClass}</option>
                            </#if>
                        </#list>
                    <#else>
                        <option disabled="disabled" selected>В этот день нет занятий</option>
                    </#if>
                </select>
                <button type="submit" class="btn btn-default ml-3 col-2">Выбрать</button>
            </form>
        </div>
    </#if>
    </div>
    <div class="row mt-4">
        <#if lessonId??>
        <div class="col-4 mr-2 border border-info">
            <form method="post" name="mark_form" class="p-2">
                <div class="form-group row mb-1">
                    <label for="studentId"  class="col-form-label col-5">Ученик</label>
                    <select name="studentId" id="studentId" required class="form-control col-6">
                        <#list students as student>
                            <option name="${student.id}" value="${student.id}">${student}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group row">
                    <label for="value" class="col-form-label col-5">Оценка</label>
                    <select name="value" required id="value" class="form-control col-6">
                        <option selected disabled></option>
                        <option name="5" value="5">5</option>
                        <option name="4" value="4">4</option>
                        <option name="3" value="3">3</option>
                        <option name="2" value="2">2</option>
                        <option name="1" value="1">1</option>
                        <option name="н" value="н">н</option>
                        <option name="б" value="б">б</option>
                    </select>
                </div>

                <div class="row">
                    <button type="submit" class="btn btn-default col-3 mx-auto">Выбрать</button>
                </div>
            </form>
        </div>
        <div class="col-7 border border-info">
            <form method="post" name="homework_form" class="p-2">
                <div class="form-group row">
                    <label for="homeworkText" class="col-form-label col-3">Домашнее задание:</label>
                    <textarea class="form-control col-9" rows="3" id="homeworkText" required name="homeworkText"><#if homework??>${homework.text}</#if></textarea>
                </div>
                <div class="row">
                    <button type="submit" class="btn btn-default col-3 mx-auto">Задать</button>
                </div>
            </form>
        </div>
    </#if>
    </div>
    <#if lessonId??>
    <div class="row mt-4">
        <h4>Оценки учеников за этот урок</h4>
        <table class="table table-hover">
            <thead class="thead-light">
            <tr>
                <th scope="col" class="w-75">Ученик</th>
                <th scope="col" class="w-25">Оценка</th>
            </tr>
            </thead>
            <tbody>
        <#list marks as mark>
        <tr>
            <td scope="row">${mark.student}</td>
            <td>${mark.value}</td>
        </tr>
        </#list>
            </tbody>
        </table>
    </div>
    </#if>
</div>

</body>
</html>