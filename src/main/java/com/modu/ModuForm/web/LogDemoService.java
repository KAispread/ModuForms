package com.modu.ModuForm.web;

import com.modu.ModuForm.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogDemoService {
    private final MyLogger myLogger;
    public void logic(String Id) {
        myLogger.log("service id = " + Id);
    }
}
