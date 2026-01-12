package com.sph.book.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Bookingcounters")
@Data
public class BookingDatabaseSequence {

    @Id
    private String id;   
    private long seq;
}
