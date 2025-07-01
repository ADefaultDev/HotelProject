<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Редактирование страны</title>
</head>
<body>
<h1>Редактирование страны</h1>

<g:form action="update">
    <g:hiddenField name="id" value="${country?.id}"/>

    <label for="name">Название страны:</label>
    <g:textField name="name" value="${country?.name}" required=""/><br/>

    <label for="capital">Столица:</label>
    <g:textField name="capital" value="${country?.capital}" required=""/><br/>

    <g:submitButton name="update" value="Сохранить"/>
</g:form>

<g:link action="index">Назад к списку</g:link>

<g:if test="${country?.errors}">
    <ul>
        <g:eachError bean="${country}" var="error">
            <li><g:message error="${error}"/></li>
        </g:eachError>
    </ul>
</g:if>
</body>
</html>
