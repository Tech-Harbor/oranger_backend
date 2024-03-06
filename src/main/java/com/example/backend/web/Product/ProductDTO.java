package com.example.backend.web.Product;

import com.example.backend.web.File.ImageEntity;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProductDTO (Long id,
                          String name,
                          String description_product,
                          String characteristic_product,
                          double price,
                          LocalDateTime createDate,
                          List<ImageEntity> image,
                          Long categoryId) { }