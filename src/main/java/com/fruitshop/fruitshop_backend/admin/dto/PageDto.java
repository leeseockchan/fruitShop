package com.fruitshop.fruitshop_backend.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class PageDto {
    private int page;   // 현재 페이지
    private int size;   // 페이지당 조회 개수
    private int totalPages; //전체 페이지
    private int totalElements;  //전체 항목 개수
    private int pageGroupSize = 10; // 페이지별 보이는 항목 개수
    private int start;  // 시작 페이지 번호
    private int end;    // 끝 페이지 번호
    private boolean prev;
    private boolean next;
    private List<ItemDto> items;

    public PageDto(int page, int size, int totalElements, List<ItemDto> items){
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;

        // 전체 페이지 계산
        this.totalPages = (int)Math.ceil((double)totalElements/size);

        // 시작 페이지 계산 ex)현재 페이지=5 -> 1, 현재 페이지=15 -> 11
        this.start = ((page-1) /pageGroupSize) * pageGroupSize+1;

        // 끝 패이지 계산
        this.end = Math.min(start + pageGroupSize - 1, totalPages);

        this.prev = start > 1;
        this.next = end <totalPages;

        this.items = items;

    }
}
