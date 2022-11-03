package com.modu.ModuForm.app.web.dto;

import lombok.Getter;

@Getter
public class PageContents {
    protected final int PAGE_ROW_COUNT = 5;
    private final int currentPage;
    private final Integer totalPages;
    private final int startPage;
    private final int endPage;

    public PageContents(Integer totalPages, Integer page) {
        this.totalPages = totalPages;
        this.currentPage = page;
        this.startPage = setStartPage(page);
        this.endPage = setEndPage(page);
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
