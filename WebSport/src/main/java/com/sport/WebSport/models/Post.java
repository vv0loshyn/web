package com.sport.WebSport.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;


    @NotEmpty(message = "Введіть назву змагань")
    String title;

    @NotEmpty(message = "Введіть місце проведення змагань")
    String location;
    @NotNull(message = "Будь ласка, введіть дату")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime date;

    @Min(value = 0, message = "Число не може бути менше 0!")
    Integer res1;

    @Min(value = 0, message = "Число не може бути менше 0!")
    Integer res2;

    @NotEmpty(message = "Введіть назву першої команди")
    String team1;

    @NotEmpty(message = "Введіть назву другої команди")
    String team2;

    public Post(String title, String location, LocalDateTime date, String team1, String team2, Integer res1, Integer res2) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.res1 = res1;
        this.res2 = res2;
    }
}