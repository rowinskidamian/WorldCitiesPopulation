<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p><b>Wybierz państwo z listy:</b>
<form action="/show_continents" method="post">
    <select name="countryOption" onchange="this.form.submit()">
        <c:forEach var="country" items="${countriesSet}">
            <option value="${country}">${country}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Wyślij">
</form>
</p>