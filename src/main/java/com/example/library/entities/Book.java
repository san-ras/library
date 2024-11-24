package com.example.library.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String author;
    private String category;
    private BigDecimal price;
    private String cover;
    private boolean reserved;
    private LocalDate createdDateTime;
    private LocalDate modifiedDateTime;

    @PrePersist
    public void onCreate() {
        createdDateTime = LocalDate.now();
        modifiedDateTime = createdDateTime;
    }

    @PreUpdate
    public void onUpdate() {
        modifiedDateTime = LocalDate.now();
    }

}
