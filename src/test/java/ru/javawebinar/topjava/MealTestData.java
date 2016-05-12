package ru.javawebinar.topjava;

import ru.javawebinar.topjava.TestUtil.ToStringModelMatcher;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.swing.*;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final ModelMatcher<UserMeal, String> MATCHER = new ToStringModelMatcher<>(UserMeal.class);
    public static final ModelMatcher<UserMealWithExceed, String> MATCHER_WITH_EXCEEDS = new ToStringModelMatcher<>(UserMealWithExceed.class);

    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 8;

    public static final UserMeal MEAL1 = new UserMeal(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final UserMeal MEAL2 = new UserMeal(MEAL1_ID + 1, of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final UserMeal MEAL3 = new UserMeal(MEAL1_ID + 2, of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final UserMeal MEAL4 = new UserMeal(MEAL1_ID + 3, of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final UserMeal MEAL5 = new UserMeal(MEAL1_ID + 4, of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final UserMeal MEAL6 = new UserMeal(MEAL1_ID + 5, of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);
    public static final UserMeal ADMIN_MEAL = new UserMeal(ADMIN_MEAL_ID, of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static final UserMeal ADMIN_MEAL2 = new UserMeal(ADMIN_MEAL_ID + 1, of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    public static final List<UserMeal> USER_MEALS = Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static UserMeal getCreated() {
        return new UserMeal(null, of(2015, Month.JUNE, 1, 18, 0), "Созданный ужин", 300);
    }

    public static UserMeal getUpdated() {
        return new UserMeal(MEAL1_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }

    public static final List<UserMealWithExceed> USER_MEAL_WITH_EXCEEDS = UserMealsUtil.getWithExceeded(USER_MEALS, LoggedUser.getCaloriesPerDay());

    public static final UserMealWithExceed MEAL1_WITH_EXCEED = USER_MEAL_WITH_EXCEEDS.stream()
            .filter(userMealWithExceed -> userMealWithExceed.getId().equals(MEAL1_ID))
            .findFirst().get();

    public static final List<UserMealWithExceed> MEAL_WITH_EXCEEDS_BETWEEN = UserMealsUtil.getWithExceeded(Arrays.asList(MEAL3, MEAL2, MEAL1) , LoggedUser.getCaloriesPerDay());


}
