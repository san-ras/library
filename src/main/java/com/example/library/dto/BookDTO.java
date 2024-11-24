package com.example.library.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

        private Integer id;
    private String title;
    private String author;
    private String category;
    private BigDecimal price;
    private String cover;
    private boolean reserved;


}