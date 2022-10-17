package com.modu.ModuForm.app.web.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "Swagger API")
@Controller
public class SwaggerController {
    @GetMapping("/api/docs")
    public String apiDocs() {
        return "redirect:/swagger-ui/index.html";
    }
}
