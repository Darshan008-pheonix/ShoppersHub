package com.sph.product.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "counters")
@Data
public class DatabaseSequence {

    @Id
    private String id;   
    private long seq;
}
