<!doctype html>
<html lang="en" class="no-js">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <title>
    <g:layoutTitle default="Grails"/>
    </title>

    <asset:stylesheet href="application.css"/>

    <g:layoutHead/>
</head>

<body>

<g:layoutBody/>


<ul class="nav">
    <li><g:link controller="hotel" action="search">Новый поиск</g:link></li>
    <li><g:link controller="country" action="index">Страны</g:link></li>
    <li><g:link controller="hotel" action="list">Отели</g:link></li>
</ul>

</body>
</html>
