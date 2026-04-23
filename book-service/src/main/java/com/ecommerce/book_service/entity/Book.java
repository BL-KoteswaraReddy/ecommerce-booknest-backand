package com.ecommerce.book_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    private String title;
    private String author;

    @Column(unique = true)
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
