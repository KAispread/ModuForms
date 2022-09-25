package com.modu.ModuForm.practice.web.servlet;

import com.modu.ModuForm.practice.domain.member.Member;
import com.modu.ModuForm.practice.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                    "성공\n" +
                    "<ul>\n" +
                    " <li>id="+member.getId()+"</li>\n" +
                    " <li>username="+member.getUsername()+"</li>\n" + " <li>age="+member.getAge()+"</li>\n" +
                    "</ul>\n" +
                    "<a href=\"/index.html\">메인</a>\n" +
                    "<a href='/servlet/members'>목록</a>" +
                "</body>\n" +
                "</html>"
        );
    }
}
