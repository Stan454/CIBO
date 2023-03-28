package com.Stan.CIBO.Controllers;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Dish;
import com.Stan.CIBO.Services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Dish dish) {
        try {
            dishService.saveDish(dish);
            return ResponseEntity.status(HttpStatus.CREATED).body("New Dish added");
        } catch (IllegalArgumentException | SaveException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Dish>> getAllDishes(){
        List<Dish> dishes = dishService.getAllDishes();
        if(dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(dishes, HttpStatus.OK);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable int id){
     Dish dish = dishService.findById(id);
        if (dish != null){
            return ResponseEntity.ok(dish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
