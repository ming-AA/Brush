package com.fiveletters.brush.web.dto;

import com.fiveletters.brush.domain.feedback.Feedback;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FeedbackDto {

    private long id;
    private String name;
    private String content;
    private String email;
    private String insertDateTime;
    private String searchKey="Name";
    private String searchValue="";

    public FeedbackDto(Feedback entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.email = entity.getEmail();
        this.insertDateTime = entity.getInsertDateTime();
    }

    public FeedbackDto(String searchKey, String searchValue) {
        this.searchKey = searchKey;
        this.searchValue = searchValue;
    }
}
