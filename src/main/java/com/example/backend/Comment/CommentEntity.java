package com.example.backend.Comment;

import com.example.backend.Product.ProductEntity;
import com.example.backend.User.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private String text;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
}