package com.estudos.java.spring.Fridge.service;

import com.estudos.java.spring.Fridge.dto.FoodCreateDTO;
import com.estudos.java.spring.Fridge.dto.FoodUpdateDTO;
import com.estudos.java.spring.Fridge.exceptions.BadRequestException;
import com.estudos.java.spring.Fridge.exceptions.NotFoundException;
import com.estudos.java.spring.Fridge.model.FoodModel;
import com.estudos.java.spring.Fridge.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    // GET
    public List<FoodModel> getAll() {
        List<FoodModel> foods = foodRepository.findAll();
        if (foods.isEmpty()) {
            throw new NotFoundException("Nenhum alimento cadastrado na geladeira.");
        }
        return foods;
    }

    // POST
    public FoodModel save(FoodCreateDTO dto) {
        FoodModel food = new FoodModel();
        food.setName(dto.name());
        food.setExpirationDate(dto.expirationDate());
        food.setQuantity(dto.quantity());

        return foodRepository.save(food);
    }

    // DELETE
    public void delete(Long id) {
        if (!foodRepository.existsById(id)) {
            throw new NotFoundException("Alimento não foi encontrado na geladeira.");
        }
        foodRepository.deleteById(id);
    }

    // PATCH
    public FoodModel patch(Long id, FoodUpdateDTO dto) {
        // 1. Valida se o DTO está totalmente vazio
        if (dto.name() == null && dto.expirationDate() == null && dto.quantity() == null) {
            throw new BadRequestException("Pelo menos um campo deve ser enviado para atualização.");
        }

        // 2. Busca o registro atual
        FoodModel foodExistente = listById(id);

        // 3. Atualiza apenas o que não for nulo no DTO
        if (dto.name() != null) {
            foodExistente.setName(dto.name());
        }
        if (dto.expirationDate() != null) {
            foodExistente.setExpirationDate(dto.expirationDate());
        }
        if (dto.quantity() != null) {
            foodExistente.setQuantity(dto.quantity());
        }

        // 4. Salva as alterações parciais
        return foodRepository.save(foodExistente);
    }

    // PUT
    public FoodModel update(Long id, FoodCreateDTO dto) {
        // 1. Busca o registro existente (reutilizando seu método listById)
        FoodModel foodExistente = listById(id);

        // 2. Atualiza os dados da Model com o que veio no DTO
        foodExistente.setName(dto.name());
        foodExistente.setExpirationDate(dto.expirationDate());
        foodExistente.setQuantity(dto.quantity());

        // 3. Salva a atualização
        return foodRepository.save(foodExistente);
    }

    // GET BY ID
    public FoodModel listById(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Alimento não foi encontrado na geladeira."));
    }
}
