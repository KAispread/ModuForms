<%@ page import="com.modu.ModuForm.practice.domain.member.Member" %>
<%@ page import="com.modu.ModuForm.practice.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    request.setCharacterEncoding("utf-8");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

    response.setContentType("text/html;charset=utf-8");
%>
<html>
<head>
    <title>save</title>
</head>
<body>
    성공
    <ul>
        <li>id=<%=member.getId()%></li>
        <li>username=<%=member.getUsername()%></li>
        <li>age=<%=member.getAge()%></li>
    </ul>
    <a href="index.html">메인</a>
</body>
</html>