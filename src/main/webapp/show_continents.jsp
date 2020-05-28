<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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



<form action="/show_cities" method="post">

</form>


</body>
</html>
