package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    UserMeal save(UserMeal userMeal);

    boolean delete(Integer userId, int id);

    UserMeal get(Integer userId, int id);

    List<UserMeal> getAll(Integer userId);

    List<UserMeal> getFiltered(Integer userId, LocalDate startDate, LocalDate endDate);
}
