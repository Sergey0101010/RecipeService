package com.sergey.recipesservice.recipes.databaselayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "category")
    private String category;

    private LocalDateTime dateOfCreationOrUpdate;


    @NotBlank
    @Column(name = "description")
    private String description;


    @NotEmpty
    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "INGREDIENTS", joinColumns = @JoinColumn(name = "id", nullable = false))
    @Column(name = "ingredients")
    private List<String> ingredients;


    @NotEmpty
    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "DIRECTIONS", joinColumns = @JoinColumn(name = "id", nullable = false))
    @Column(name = "directions")
    private List<String> directions;

    @PrePersist
    protected void onCreate() {
        dateOfCreationOrUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateOfCreationOrUpdate = LocalDateTime.now();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User recipeCreator;
}
