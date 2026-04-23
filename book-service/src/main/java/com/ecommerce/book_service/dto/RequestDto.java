package com.ecommerce.book_service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RequestDto {

    private String title;
    private String author;

    private String isbn;

    private String genre;
    private String publisher;
    private BigDecimal price;
    private Integer stock;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String coverImageUrl;

    private LocalDate publishedDate;
    private Boolean featured;

}
