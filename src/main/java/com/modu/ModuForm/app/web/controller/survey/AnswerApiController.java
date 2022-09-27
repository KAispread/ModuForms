package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.AnswerServiceImpl;
import com.modu.ModuForm.app.web.dto.survey.AnswerRequestDto;
import com.modu.ModuForm.app.web.dto.survey.AnswerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {
    private final AnswerServiceImpl answerService;

    // 설문 응답 제출
    @PostMapping("/app/survey/{surveyId}/ans")
    public Long save(@RequestBody AnswerRequestDto answerRequestDto, @PathVariable Long surveyId) {
        return answerService.save(answerRequestDto, surveyId);
    }

    // 선택한 설문 보기
    @GetMapping("/app/survey/{id}")
    public AnswerResponseDto findById(@PathVariable Long id){
        return answerService.findById();
    }

    // 선택한 설문 수정
    @PutMapping("app/survey/{id}")
    public Long update(@PathVariable Long id) {
        return answerService.update();
    }

    // 선택한 설문 삭제
    @DeleteMapping
    public Long delete(@PathVariable Long id) {
        return answerService.delete(id);
    }

    @GetMapping("/app/response")
    public AnswerRequestDto response() {
        List<String> answer = new ArrayList<>();
        answer.add("Lee");
        answer.add("123");
        answer.add("Hi");
        return AnswerRequestDto.builder()
                .userId(1L)
                .answerQuestion(answer)
                .build();
    }
}
