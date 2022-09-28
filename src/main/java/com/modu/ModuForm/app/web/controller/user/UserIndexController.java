package com.modu.ModuForm.app.web.controller.user;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserIndexController {
    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/basic/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data","Hello <b>Spring!</b>");
        return "basic/text-basic";
    }

    @GetMapping("/basic/variable")
    public String variable(Model model) {
        Member memberA = new Member("memberA", 10);
        Member memberB = new Member("memberB", 20);

        List<Member> list = new ArrayList<>();
        list.add(memberA);
        list.add(memberB);

        Map<String, Member> map = new HashMap<>();
        map.put("memberA", memberA);
        map.put("memberB", memberB);

        model.addAttribute("member", memberA);
        model.addAttribute("members", list);
        model.addAttribute("memberMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic/basic-objects")
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "/basic/basic-object";
    }

    @Data
    static class Member{
        private String username;
        private int age;

        public Member(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/basic/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }
}
