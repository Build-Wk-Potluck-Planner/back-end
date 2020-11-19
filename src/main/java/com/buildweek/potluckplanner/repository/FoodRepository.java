package com.buildweek.potluckplanner.repository;

import com.buildweek.potluckplanner.models.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long>
{
}
