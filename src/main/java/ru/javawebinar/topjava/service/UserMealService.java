package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMeal save(Integer userId, UserMeal userMeal);

    void delete(Integer userId, int id) throws NotFoundException;

    UserMeal get(Integer userId, int id) throws NotFoundException;

    List<UserMeal> getAll(Integer userId);

    List<UserMeal> getFiltered(Integer userId, LocalDate startDate, LocalDate endDate);

    void update(Integer userId, UserMeal userMeal);
}
