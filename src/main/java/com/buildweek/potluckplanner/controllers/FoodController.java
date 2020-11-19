package com.buildweek.potluckplanner.controllers;

import com.buildweek.potluckplanner.models.Food;
import com.buildweek.potluckplanner.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController
{
    @Autowired
    private FoodService foodService;

    // http://localhost:2019/foods/foods
    @GetMapping(value = "/foods",
        produces = "application/json")
    public ResponseEntity<?> listAllFoods()
    {
        List<Food> foods = foodService.findAll();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    // http://localhost:2019/foods/food/1
    @GetMapping(value = "/food/{foodid}",
        produces = "application/json")
    public ResponseEntity<?> listFoodById(@PathVariable long foodid)
    {
        Food f = foodService.findFoodById(foodid);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    // http://localhost:2019/foods/food
    @PostMapping(value = "/food",
        consumes = "application/json")
    public ResponseEntity<?> addNewFood(@Valid @RequestBody Food food)
    {
        food.setFoodid(0);
        food = foodService.save(food);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{foodid}")
            .buildAndExpand(food.getFoodid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    // http://localhost:2019/foods/food/1
    @PutMapping(value = "/food/{foodid}",
        consumes = "application/json")
    public ResponseEntity<?> updateFood(@Valid @RequestBody Food updateFood,
                                        @PathVariable long foodid)
    {
        updateFood.setFoodid(foodid);
        foodService.save(updateFood);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // http://localhost:2019/foods/food/1
    @DeleteMapping(value = "/food/{foodid}")
    public ResponseEntity<?> deleteFood(@PathVariable long foodid)
    {
        foodService.delete(foodid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
