package com.sph.book.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "counters")
@Data
public class SagaDatabaseSequence {

    @Id
    private String id;   
    private long seq;
}
