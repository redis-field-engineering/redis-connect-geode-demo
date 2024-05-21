package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    String type;
    Trade[] data;
}
