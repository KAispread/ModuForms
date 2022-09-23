package com.modu.ModuForm.practice.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modu.ModuForm.practice.basic.JSONconvert;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messagebody = " + messagebody);

        JSONconvert readValue = objectMapper.readValue(messagebody, JSONconvert.class);

        System.out.println("readValue.getUsername() = " + readValue.getUsername());
        System.out.println("readValue.getAge() = " + readValue.getAge());

        response.getWriter().write("ok");
    }
}
