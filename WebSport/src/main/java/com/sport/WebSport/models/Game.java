package com.sport.WebSport.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "Будь ласка, введіть дату")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime date;

    @NotEmpty(message = "Введіть учасників гри!")
    String players1;

    @NotEmpty(message = "Введіть учасників гри!")
    String players2;

    public Game(LocalDateTime date, String players1, String players2) {
        this.date = date;
        this.players1 = players1;
        this.players2 = players2;
    }
}
