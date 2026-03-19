package com.estudos.java.spring.Fridge.service;

import com.estudos.java.spring.Fridge.dto.FoodCreateDTO;
import com.estudos.java.spring.Fridge.dto.FoodUpdateDTO;
import com.estudos.java.spring.Fridge.exceptions.NotFoundException;
import com.estudos.java.spring.Fridge.model.FoodModel;
import com.estudos.java.spring.Fridge.repository.FoodRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        foodRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Alimento não foi encontrado na geladeira."));
            
        foodRepository.deleteById(id);
    }

    // PATCH
    @Transactional() // Garante que tudo acontece com sucesso, ou nada acontece
    @SuppressWarnings("null") // Isso diz à IDE: "Eu já validei a nulidade com o orElseThrow, ignore o aviso"
    public FoodModel patch(Long id, FoodUpdateDTO dto) {
        
        FoodModel existingFood = foodRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Alimento não encontrado"));

        if (dto.name() != null) {
            existingFood.setName(dto.name());
        }
        if (dto.expirationDate() != null) {
            existingFood.setExpirationDate(dto.expirationDate());
        }
        if (dto.quantity() != null) {
            existingFood.setQuantity(dto.quantity());
        }

        return foodRepository.save(existingFood);
    }

    // PUT
    @Transactional()
    @SuppressWarnings("null")
    public FoodModel update(Long id, FoodCreateDTO dto) {
        FoodModel existingFood = foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alimento não encontrado"));

        existingFood.setName(dto.name());
        existingFood.setExpirationDate(dto.expirationDate());
        existingFood.setQuantity(dto.quantity());

        return foodRepository.save(existingFood);
    }

    
    @Transactional()
    @SuppressWarnings("null")
    public FoodModel getById(Long id) {
        return foodRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Alimento não encontrado"));
    }


}
