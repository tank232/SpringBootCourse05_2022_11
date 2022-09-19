package com.av.domain;

import lombok.Data;


@Data
public class Comment {

    private String userName;
    private String text;



    @Override
    public String toString() {
        return "Comment{" +
                ", userName='" + userName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
