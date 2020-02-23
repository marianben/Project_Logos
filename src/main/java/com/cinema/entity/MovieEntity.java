package com.cinema.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String releaseYear;

    @Column
    private Integer rating;

    @Column
    private String image;

    @Column(nullable = false)
    private String duration;

    @Column(name = "age_rating", nullable = false)
    private String ageRation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
