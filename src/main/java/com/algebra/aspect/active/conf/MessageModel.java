package com.algebra.aspect.active.conf;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageModel implements Serializable {

    private String title;
    private String message;
}
