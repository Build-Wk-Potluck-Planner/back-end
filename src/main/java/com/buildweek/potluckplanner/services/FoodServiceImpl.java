package com.buildweek.potluckplanner.services;

import com.buildweek.potluckplanner.models.Food;
import com.buildweek.potluckplanner.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "foodService")
public class FoodServiceImpl implements FoodService
{
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> findAll()
    {
        List<Food> list = new ArrayList<>();
        foodRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Food findFoodById(long foodid)
    {
        return foodRepository.findById(foodid)
            .orElseThrow(() -> new EntityNotFoundException("Food id " + foodid + " not found!"));
    }

    @Transactional
    @Override
    public Food save(Food food)
    {
        Food newFood = new Food();

        if (food.getFoodid() != 0)
        {
            foodRepository.findById(food.getFoodid())
                .orElseThrow(() -> new EntityNotFoundException("Food id " + food.getFoodid() + " not found!"));
            newFood.setFoodid(food.getFoodid());
        }

        newFood.setFoodname(food.getFoodname());

        return foodRepository.save(newFood);
    }

    @Transactional
    @Override
    public void delete(long foodid)
    {
        foodRepository.findById(foodid)
            .orElseThrow(() -> new EntityNotFoundException("Food id " + foodid + " not found!"));
        foodRepository.deleteById(foodid);
    }
}
