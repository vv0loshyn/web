package com.sport.WebSport.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty(message = "Введіть назву змагань!")
    String title;

    @NotEmpty(message = "Введіть місце проведення змагань!")
    String location;

    @Min(value = 0, message = "Рахунок не може бути менше 0!")
    Integer res1;

    @Min(value = 0, message = "Рахунок не може бути менше 0!")
    Integer res2;

    @NotEmpty(message = "Введіть назву першої команди!")
    String team1;

    @NotEmpty(message = "Введіть назву другої команди!")
    String team2;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    Result result;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    Game game;

    public Post(String title, String location, String team1, String team2, Result result, Game game) {
        this.title = title;
        this.location = location;
        this.game = game;
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
    }
}