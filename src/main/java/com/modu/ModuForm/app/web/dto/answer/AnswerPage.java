package com.modu.ModuForm.app.web.dto.answer;

import com.modu.ModuForm.app.domain.surbay.answer.Answer;
import com.modu.ModuForm.app.web.dto.AnswerPreview;
import com.modu.ModuForm.app.web.dto.PageContents;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AnswerPage extends PageContents {
    private List<AnswerPreview> answerPreviews = new ArrayList<>();

    public AnswerPage(Page<Answer> answerPageList, Integer currentPage) {
        super(answerPageList.getTotalPages(), currentPage);
        this.answerPreviews = answerPageList.getContent().stream()
                .map(AnswerPreview::new)
                .collect(Collectors.toList());
    }
}
