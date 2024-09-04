<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="groupName.servlet.domain.member.MemberRepository" %>
<%@ page import="groupName.servlet.domain.member.Member" %>


<%
    //request,response는 jsp에서 지원하기에 그냥 사용가능
    MemberRepository memberRepository = MemberRepository.getInstance();
    String username = request.getParameter("username"); //유저가 입력한 username, age를 HttpServletRequest.getParameter()
    int age = Integer.parseInt(request.getParameter("age"));
    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
