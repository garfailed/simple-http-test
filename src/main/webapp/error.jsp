<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error page</title>
</head>
<body>
<p>${requestScope.get('jakarta.servlet.error.message')}<p>
<p><c:out value="Hello from a JSTL tag."/></p>
</body>
</html>
