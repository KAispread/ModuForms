package com.modu.ModuForm.practice.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modu.ModuForm.practice.basic.JSONconvert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        JSONconvert jsoNconvert = new JSONconvert();
        jsoNconvert.setAge(20);
        jsoNconvert.setUsername("kim");

        String s = objectMapper.writeValueAsString(jsoNconvert);
        response.getWriter().write(s);
    }
}
