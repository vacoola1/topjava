package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by r.vakulenko on 10.03.2016.
 */
public interface UserMealDao {
    void addUserMeal(UserMeal meal);

    boolean updateUserMeal(UserMeal meal);

    void deleteUserMeal(UserMeal meal);

    List<UserMeal> getAllUserMeal();
}
