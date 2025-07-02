<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Редактировать отель</title>
    <meta name="layout" content="main"/>
</head>
<body>
<h1>Редактировать отель</h1>

<g:form controller="hotel" action="update" method="POST">
    <g:hiddenField name="id" value="${hotel?.id}"/>
    <g:hiddenField name="version" value="${hotel?.version}"/>

    <div>
        <label for="name">Название:</label>
        <g:textField name="name" value="${hotel?.name}" required=""/>
    </div>

    <div>
        <label for="country.id">Страна:</label>
        <g:select name="country.id" from="${countries}" optionKey="id" optionValue="name"
                  value="${hotel?.country?.id}" required=""/>
    </div>

    <div>
        <label for="stars">Количество звезд:</label>
        <g:select name="stars" from="${[1,2,3,4,5]}" value="${hotel?.stars}" required=""/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: hotel, field: 'website', 'error')}">
        <label for="website">Сайт отеля</label>
        <g:textField name="website" value="${hotel?.website}"
                     class="${hasErrors(bean: hotel, field: 'website', 'error-field')}"/>

        <g:hasErrors bean="${hotel}" field="website">
            <div class="error-message">
                <g:eachError bean="${hotel}" field="website">
                    <p class="text-danger"><g:message error="${it}"/></p>
                </g:eachError>
            </div>
        </g:hasErrors>
    </div>



    <g:submitButton name="update" value="Сохранить"/>
</g:form>

<g:link action="list">Назад к списку</g:link>
</body>
</html>
