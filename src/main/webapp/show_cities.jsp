<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<p><b>Miasta w Twoim państwie to:</b>

    <table border="1">
    <th>
        Miasto
    </th>
    <th>
        Liczebność
    </th>
    <c:forEach var="city" items="${citiesMap}">
        <tr>
            <td>${city.key}</td>
            <td>${city.value}</td>
        </tr>
    </c:forEach>
</table>

</p>