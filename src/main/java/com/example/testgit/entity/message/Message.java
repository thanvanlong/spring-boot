package com.example.testgit.entity.message;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {
    private String from;
    private String to;
    private String message;
    private String time;

}
