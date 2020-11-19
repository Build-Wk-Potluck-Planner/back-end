package com.buildweek.potluckplanner.services;

import com.buildweek.potluckplanner.models.Food;

import java.util.List;

public interface FoodService
{
    List<Food> findAll();

    Food findFoodById(long foodid);

    Food save(Food food);

    void delete(long foodid);
}
