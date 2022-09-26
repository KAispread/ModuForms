package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.AnswerServiceImpl;
import com.modu.ModuForm.app.web.dto.AnswerRequestDto;
import com.modu.ModuForm.app.web.dto.SurveyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SurveyUserApiController {
    private final AnswerServiceImpl answerService;

    // 설문 응답 제출
    @PostMapping("/app/survey/{id}/ans")
    public Long save(@RequestBody AnswerRequestDto answerRequestDto, Long userId) {
        return answerService.save(answerRequestDto, userId);
    }

    // 선택한 설문 보기
    @GetMapping("/app/survey/{id}")
    public SurveyResponseDto findById(@PathVariable Long id){
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
}
