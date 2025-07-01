<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>Список отелей</title>
</head>
<body>
<h1>Список отелей</h1>

<g:link class="btn" action="create">Добавить отель</g:link>

<table>
  <thead>
  <tr>
    <th>Название отеля</th>
    <th>Страна</th>
    <th>Звезды</th>
    <th>Действия</th>
  </tr>
  </thead>
  <tbody>
  <g:each in="${hotels}" var="hotel">
    <tr>
      <td>${hotel.name}</td>
      <td>${hotel.country?.name}</td>
      <td>${hotel.stars}</td>
      <td>
        <g:link action="edit" id="${hotel.id}">Редактировать</g:link> |
        <g:link action="delete" id="${hotel.id}" onclick="return confirm('Удалить отель?');">Удалить</g:link>
      </td>
    </tr>
  </g:each>

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

  </tbody>
</table>
