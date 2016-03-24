package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {

    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    public static final int MEAL_ID = START_SEQ + 2;

    public static final UserMeal MEAL = new UserMeal(MEAL_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);

    public static final List<UserMeal> MEAL_LIST = Arrays.asList(
            new UserMeal(START_SEQ + 2, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new UserMeal(START_SEQ + 3, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new UserMeal(START_SEQ + 4, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new UserMeal(START_SEQ + 5, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500),
            new UserMeal(START_SEQ + 6, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000),
            new UserMeal(START_SEQ + 7, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));


    public static final List<UserMeal> MEAL_LIST_MINUS_FIRST = MEAL_LIST.subList(1, MEAL_LIST.size());

    public static final LocalDateTime START_DATETIME = LocalDateTime.of(2015, Month.MAY, 31, 11, 0);
    public static final LocalDateTime END_DATETIME = LocalDateTime.of(2015, Month.MAY, 31, 13, 0);

    public static final LocalDate START_DATE = START_DATETIME.toLocalDate();
    public static final LocalDate END_DATE = END_DATETIME.toLocalDate();

    public static final List<UserMeal> MEAL_LIST_BETWEEN_DATETIME = MEAL_LIST.subList(4, 5);
    public static final List<UserMeal> MEAL_LIST_BETWEEN_DATE = MEAL_LIST.subList(3, 6);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
