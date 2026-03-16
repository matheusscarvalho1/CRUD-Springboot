package com.estudos.java.spring.Fridge.dto;

import com.estudos.java.spring.Fridge.groups.OnCreate;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record FoodCreateDTO(
        @NotBlank(groups = OnCreate.class, message = "O nome é obrigatório")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres")
        String name,

        @NotNull(groups = OnCreate.class, message = "A data é obrigatória")
        @FutureOrPresent(message = "A data não pode ser no passado")
        LocalDate expirationDate,

        @NotNull(groups = OnCreate.class, message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade mínima é 1")
        Integer quantity
) {}

