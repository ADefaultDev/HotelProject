<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Список стран</title>
</head>
<body>
<h1>Список стран</h1>

<g:link class="btn" action="create">Добавить страну</g:link>

<g:if test="${flash.message}">
    <div class="alert alert-success" role="alert">
        ${flash.message}
    </div>
</g:if>

<g:if test="${flash.error}">
    <div class="alert alert-danger" role="alert">
        ${flash.error}
    </div>
</g:if>


<table>
    <thead>
    <tr>
        <th>Название страны</th>
        <th>Столица страны</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${countries}" var="country">
        <tr>
            <td>${country.name}</td>
            <td>${country.capital}</td>
            <td>
                <g:link action="edit" id="${country.id}">Редактировать</g:link> |
                <g:link action="delete" id="${country.id}" onclick="return confirm('Удалить страну?');">Удалить</g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
