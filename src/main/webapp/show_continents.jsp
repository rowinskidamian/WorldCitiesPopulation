<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of continents</title>
</head>
<body>

<h3>Możesz wybrać miasto z poniższych kontynentów:</h3>
<div>
    <c:forEach var="continent" items="${continentsSet}">
        ${continent}<br>
    </c:forEach>
</div>


<p><b>Wybierz kontynent z listy:</b>
<form action="/show_continents" method="post">
    <select name="continentOption">
        <c:forEach var="continent" items="${continentsSet}">
            <option value="${continent}">${continent}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Wyślij">
</form>
</p>

<p>
    <c:if test="${not empty countriesSet}">
        <%@ include file="show_countries.jsp" %>
    </c:if>
</p>

<p>
    <c:if test="${not empty citiesMap}">
        <%@ include file="show_cities.jsp" %>
    </c:if>
</p>


</body>
</html>
