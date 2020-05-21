package com.fiveletters.brush.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto {

    private int currentPageNo = 1;//현재 페이지 번호
    private int pageSize = 10;//페이지 리스트에 게시되는 페이지 건 수 (설정)
    private int recordCountPerPage = 15;//한 페이지당 게시되는 게시물 건 수 (설정)
    private int totalRecordCount = 0;//전체 게시물 건 수
    private int totalPageCount = 0;//전체 페이지 개수
    private int firstRecordIndex = 0;//첫 페이지 인덱스 번호(글 번호)
    private int lastRecordIndex = 0;//마지막 페이지 인덱스 번호(글 번호)
    private int firstPageNoOnPageList = 1;//페이지 리스트의 첫 페이지 번호
    private int lastPageNoOnPageList = 1;//페이지 리스트의 마지막 페이지 번호

    public int getTotalPageCount() { //전체 페이지 개수 = ((전체 데이터 건수-1)/페이지당 게시될 데이터 건수)+1
        totalPageCount = ((getTotalRecordCount()-1)/getRecordCountPerPage()) + 1;
        return totalPageCount;
    }

    public int getFirstPageNoOnPageList() { //페이지 리스트 첫 페이지 번호 = ((현재 페이지 번호-1)/페이지 건수)*페이지 건수 +1
        firstPageNoOnPageList = ((getCurrentPageNo()-1)/getPageSize())*getPageSize() + 1;
        return firstPageNoOnPageList;
    }

    public int getLastPageNoOnPageList() { //페이지 리스트 끝 페이지 번호 = 첫 페이지 번호+페이지 건 수
        lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;
        if(lastPageNoOnPageList > getTotalPageCount()){
            lastPageNoOnPageList = getTotalPageCount();
        }
        return lastPageNoOnPageList;
    }

    public int getFirstRecordIndex() {
        firstRecordIndex = (getCurrentPageNo()-1) * getRecordCountPerPage();
        return firstRecordIndex;
    }

    public int getLastRecordIndex() {
        lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
        return lastRecordIndex;
    }
}
