package com.fiveletters.brush.domain.feedback;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "PAINTING_FEEDBACK")
public class Feedback {

    @Id
    @Column(name = "SEQ")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "INSERT_DT")
    private String insertDateTime;

    public void update(String name, String content, String email) {
        this.name = name;
        this.content = content;
        this.email = email;
    }
}


