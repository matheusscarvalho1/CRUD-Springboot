package com.estudos.java.spring.Fridge.controller;

import com.estudos.java.spring.Fridge.dto.FoodRequestDTO;
import com.estudos.java.spring.Fridge.dto.FoodUpdateDTO;
import com.estudos.java.spring.Fridge.groups.OnCreate;
import com.estudos.java.spring.Fridge.model.FoodModel;
import com.estudos.java.spring.Fridge.service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {

    // Injeção de dependência
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }
    /* ou consigo fazer a injeção dessa forma também automática
        @Autowired
        private final FoodService foodService;
    */

    @GetMapping
    public ResponseEntity<List<FoodModel>> GetAll() {
        List<FoodModel> foods = foodService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(foods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodModel> getById(@PathVariable Long id) {
        FoodModel food = foodService.listById(id);
        return ResponseEntity.status(HttpStatus.OK).body(food);
    }

    @PostMapping
    // @Validated(OnCreate.class) ativa as regras que você marcou com esse grupo no DTO
    public ResponseEntity<FoodModel> create(@Validated(OnCreate.class) @RequestBody FoodRequestDTO foodDto) {
        FoodModel createdFood = foodService.save(foodDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FoodModel> updatePartial(@PathVariable Long id, @Valid @RequestBody FoodUpdateDTO foodDto) {
        FoodModel updatedFood = foodService.patch(id, foodDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedFood);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodModel> update(@PathVariable Long id, @Validated(OnCreate.class) @RequestBody FoodRequestDTO foodDto) {
        FoodModel updatedFood = foodService.update(id, foodDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedFood);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        foodService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
