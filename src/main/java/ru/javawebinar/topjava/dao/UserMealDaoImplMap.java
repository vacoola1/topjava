package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by r.vakulenko on 10.03.2016.
 */
public class UserMealDaoImplMap implements UserMealDao {

    private static final Map<Integer, UserMeal> userMeals = new HashMap<>();

    static {

        UserMeal newMeal;
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 25, 10, 0), "Завтрак", 500);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 25, 13, 0), "Обед", 1000);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 25, 20, 0), "Ужин", 500);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 26, 10, 0), "Завтрак", 1000);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 26, 13, 0), "Обед", 500);
        userMeals.put(newMeal.getId(), newMeal);
        newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 26, 20, 0), "Ужин", 510);
        userMeals.put(newMeal.getId(), newMeal);
    }

    @Override
    public void addUserMeal(UserMeal meal) {
        if (meal == null) {
            return;
        }
        synchronized (userMeals) {
            userMeals.put(meal.getId(), meal);
        }

    }

    @Override
    public boolean updateUserMeal(UserMeal meal) {
        if (meal == null) {
            return false;
        }
        return userMeals.containsKey(meal.getId());
    }

    @Override
    public void deleteUserMeal(UserMeal meal) {
        if (meal == null) {
            return;
        }
        synchronized (userMeals) {
            userMeals.remove(meal.getId());
        }
    }

    @Override
    public List<UserMeal> getAllUserMeal() {
        return userMeals.values().stream().collect(Collectors.toList());
    }

    @Deprecated
    private Map<Integer, UserMeal> getUserMealsMap() {

        synchronized (userMeals) {

            UserMeal newMeal;
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 25, 10, 0), "Завтрак", 500);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 25, 13, 0), "Обед", 1000);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 25, 20, 0), "Ужин", 500);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 26, 10, 0), "Завтрак", 1000);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 26, 13, 0), "Обед", 500);
            userMeals.put(newMeal.getId(), newMeal);
            newMeal = new UserMeal(LocalDateTime.of(2015, Month.MAY, 26, 20, 0), "Ужин", 510);
            userMeals.put(newMeal.getId(), newMeal);
            return userMeals;
        }
    }

    @Override
    public UserMeal getUserMealById(int id) {
        return userMeals.getOrDefault(id, null);
    }

}
