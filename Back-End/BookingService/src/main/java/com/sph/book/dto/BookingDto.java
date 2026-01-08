package com.sph.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {


    private String userId;
    private String email;
    private String phoneNumber;
    private String productId;
    private String ownerId;
}
