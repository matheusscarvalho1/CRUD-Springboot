package com.estudos.java.spring.Fridge.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record FoodUpdateDTO(
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        String name,

        @FutureOrPresent(message = "A data não pode ser no passado")
        LocalDate expirationDate,

        @Min(value = 1, message = "A quantidade mínima é 1")
        Integer quantity
) {}

