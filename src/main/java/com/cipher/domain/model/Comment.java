package com.cipher.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private LocalDateTime commentDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "film_id",referencedColumnName = "id")
    @ToString.Exclude
    private Film film;
}
