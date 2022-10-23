package com.modu.ModuForm.app.web.dto.answer;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AnswerStringClass {
    public String answer;

    public AnswerStringClass(String answer) {
        this.answer = answer;
    }
}
