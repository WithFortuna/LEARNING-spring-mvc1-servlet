<%--jsp를 사용하면 서블릿 없이도 url맵핑이 되면서 사용가능하다--%>
<%--해당 jsp파일의 url은 "/jsp/members/new-form.jsp"  이다.--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>
