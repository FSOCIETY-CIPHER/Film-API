package com.cipher.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "films")
@Data
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Date releaseDate;
    @Column(nullable = false)
    private Long commentCount;
    @OneToMany(mappedBy = "film",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> comments;

}
