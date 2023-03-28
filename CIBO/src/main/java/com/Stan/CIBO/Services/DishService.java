package com.Stan.CIBO.Services;

import com.Stan.CIBO.Exceptions.SaveException;
import com.Stan.CIBO.Models.Dish;

import java.util.List;

public interface DishService {
    public Dish saveDish(Dish dish) throws SaveException;
    public List<Dish> getAllDishes();
    public Dish findById(int id);
}
