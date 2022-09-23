package com.modu.ModuForm.practice.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); //메시지 바디의 내용을 byte코드로 받음
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//Byte 를 문자로 바꿀땐 항상 인코딩 정보 필수

        System.out.println("message body = " + messagebody);

        response.getWriter().write("ok");
    }
}
