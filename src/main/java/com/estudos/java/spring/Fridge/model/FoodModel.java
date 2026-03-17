package com.estudos.java.spring.Fridge.model;

import com.estudos.java.spring.Fridge.dto.FoodCreateDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity //É uma entidade no meu banco de dados e irá criar uma tabela
@Table(name = "food_table") // Nome da tabela
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // Para quando você tiver o ID e quiser criar o objeto completo
public class FoodModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @jakarta.persistence.Column(nullable = false)
    private String name;

    @jakarta.persistence.Column(nullable = false)
    private LocalDate expirationDate;

    @jakarta.persistence.Column(nullable = false)
    private Integer quantity;


     public FoodModel(FoodCreateDTO dto) {
         this.name = name;
         this.expirationDate = expirationDate;
         this.quantity = quantity;
     }
    // GETTERS AND SETTERS @Getters, @Setters
        // public Long getId() {
        //     return id;
        // }

        // public void setId(Long id) {
        //     this.id = id;
        // }

        // public String getName() {
        //     return name;
        // }

        // public void setName(String name) {
        //     this.name = name;
        // }

        // public LocalDate getExpirationDate() {
        //     return expirationDate;
        // }

        // public void setExpirationDate(LocalDate expirationDate) {
        //     this.expirationDate = expirationDate;
        // }

        // public Integer getQuantity() {
        //     return quantity;
        // }

        // public void setQuantity(Integer quantity) {
        //     this.quantity = quantity;
        // }
}



