package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO0 return filtered list with correctly exceeded field

        List<UserMealWithExceed> mealWithExceedList = new ArrayList<>();
        Map<LocalDate, Integer> dateMap = new HashMap<>();

        for (UserMeal userMeal : mealList) {
            int caloris = (dateMap.containsKey(userMeal.getDateTime().toLocalDate()) ? dateMap.get(userMeal.getDateTime().toLocalDate()) : 0) + userMeal.getCalories();
            dateMap.put(userMeal.getDateTime().toLocalDate(), caloris);
        }

        for (UserMeal userMeal : mealList) {
            if (startTime.compareTo(userMeal.getDateTime().toLocalTime()) <= 0 && endTime.compareTo(userMeal.getDateTime().toLocalTime()) >= 0) {
                mealWithExceedList.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), dateMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }

        mealWithExceedList.forEach(System.out::println);

        return mealWithExceedList;
    }
}
