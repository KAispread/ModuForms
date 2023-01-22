package com.modu.ModuForm.app.web.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class PageContents {
    protected final int PAGE_ROW_COUNT = 5;
    private final int currentPage;
    private final Integer totalPages;
    private final int startPage;
    private final int endPage;

    public PageContents(Page page) {
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber() + 1;
        this.startPage = setStartPage(page.getNumber() + 1);
        this.endPage = setEndPage(page.getNumber() + 1);
    }

    private int setStartPage(Integer currentPage) {
        if (currentPage % PAGE_ROW_COUNT == 0) {
            return currentPage - (PAGE_ROW_COUNT - 1);
        }

        return (currentPage / PAGE_ROW_COUNT) * PAGE_ROW_COUNT + 1;
    }

    private int setEndPage(Integer currentPage) {
        int end;
        if (currentPage % PAGE_ROW_COUNT == 0) {
            return currentPage;
        }
        end = ((currentPage / PAGE_ROW_COUNT) + 1) * PAGE_ROW_COUNT;
        if (totalPages <= end) {
            return totalPages;
        }
        return end;
    }
}
