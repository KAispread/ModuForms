package com.modu.ModuForm.app.web.controller.survey;

import com.modu.ModuForm.app.service.survey.SurveyService;
import com.modu.ModuForm.app.web.dto.survey.SurveySaveDto;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Survey DATA handling API")
@RequestMapping("/app/surveys")
@RequiredArgsConstructor
@RestController
public class SurveyApiController {
    private final SurveyService surveyService;

    @Operation(summary = "설문 저장 API", description = "설문 저장 요청을 처리합니다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{nickName}")
    public Long save(@Validated @RequestBody final SurveySaveDto surveySaveDto, @PathVariable final String nickName) {
        return surveyService.save(surveySaveDto, nickName);
    }

    @Operation(summary = "설문 수정 API", description = "설문 수정 요청을 처리합니다.")
    @PutMapping("/{id}")
    public Long update(@Validated @RequestBody final SurveySaveDto surveySaveDto, @PathVariable final Long id) {
        return surveyService.update(id, surveySaveDto);
    }

    @Operation(summary = "설문 삭제 API", description = "설문을 삭제하는 요청을 처리합니다.")
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable final Long id) {
        return surveyService.delete(id);
    }
}
