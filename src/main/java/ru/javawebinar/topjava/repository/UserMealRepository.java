package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    // null if updated meal do not belong to userId
    UserMeal save(UserMeal userMeal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    UserMeal get(int id, int userId);

    // ORDERED dateTime
    Collection<UserMeal> getAll(int userId);

    // ORDERED dateTime
    List getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
