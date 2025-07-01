<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Поиск отелей</title>
    <style>
    .pagination {
        margin: 20px 0;
        text-align: center;
    }
    .pagination a, .pagination span {
        padding: 6px 12px;
        margin: 0 3px;
        border: 1px solid #ddd;
        display: inline-block;
    }
    </style>
</head>
<body>
<h1>Поиск отелей</h1>
<div style="margin-bottom: 20px;">
    <g:link controller="hotel" action="create" class="btn btn-success">
        Добавить отель
    </g:link>
</div>

<g:form action="search" method="GET">
    <div class="form-group">
        <label>Название отеля:</label>
        <g:textField name="query" value="${query}" class="form-control"/>
    </div>

    <div class="form-group">
        <label>Страна:</label>
        <g:select name="countryId" from="${countries}"
                  value="${country?.id}" optionKey="id"
                  noSelection="['':'Любая']" class="form-control"/>
    </div>

    <g:submitButton name="search" value="Найти" class="btn btn-primary"/>
</g:form>

<g:if test="${hotels}">
    <h2>Найдено отелей: ${hotelCount}</h2>

    <g:if test="${hotelCount > params.max}">
        <div class="pagination">
            <g:paginate total="${hotelCount}" params="${params}"/>
        </div>
    </g:if>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Звездность</th>
            <th>Название</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${hotels}" var="hotel">
            <tr>
                <td>${'★' * hotel.stars}</td>
                <td>
                    ${hotel.name}
                    <g:if test="${hotel.website}">
                        <br/>
                        <a href="${hotel.website}" target="_blank">${hotel.website}</a>
                    </g:if>
                </td>
            </tr>
        </g:each>

        </tbody>
    </table>

    <g:if test="${hotelCount > params.max}">
        <div class="pagination">
            <g:paginate total="${hotelCount}" params="${params}"/>
        </div>
    </g:if>
</g:if>
<g:elseif test="${params.query || params.countryId}">
    <div class="alert alert-info">По вашему запросу ничего не найдено</div>
</g:elseif>
<g:else>
    <div class="alert alert-info">Введите параметры поиска или оставьте пустыми для вывода всех отелей</div>
</g:else>
</body>
</html>