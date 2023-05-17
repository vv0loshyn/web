package com.sport.WebSport.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Min(value = 0, message = "Рахунок не може бути менше 0!")
    Integer res1;

    @Min(value = 0, message = "Рахунок не може бути менше 0!")
    Integer res2;

    public Result(Integer res1, Integer res2) {
        this.res1 = res1;
        this.res2 = res2;
    }
}