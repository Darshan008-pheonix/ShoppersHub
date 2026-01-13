package com.sph.book.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingSaga {

    @Id
    private String sagaId;

    private String bookingId;
    private SagaStep currentStep;
    private String failureReason;

    private Instant startedAt;
    private Instant updatedAt;
}