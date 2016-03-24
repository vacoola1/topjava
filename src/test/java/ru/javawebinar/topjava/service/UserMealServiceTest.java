package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by r.vakulenko on 23.03.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {

    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal userMeal = service.get(MEAL_ID, USER_ID);
        MATCHER.assertEquals(MEAL, userMeal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFoundNoUser() throws Exception {
        service.get(MEAL_ID, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID, USER_ID);
        MATCHER.assertCollectionEquals(MEAL_LIST_MINUS_FIRST, service.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDeleteNoUser() throws Exception {
        service.delete(MEAL_ID, ADMIN_ID);
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(MEAL_LIST_BETWEEN_DATE, service.getBetweenDates(START_DATE, END_DATE, USER_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(MEAL_LIST_BETWEEN_DATETIME, service.getBetweenDateTimes(START_DATETIME, END_DATETIME, USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEAL_LIST, service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        MEAL.setDescription("UpdatedDescription");
        MEAL.setCalories(111);
        service.update(MEAL, USER_ID);
        MATCHER.assertEquals(MEAL, service.get(MEAL_ID, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        UserMeal userMeal = new UserMeal(1, LocalDateTime.of(2020, Month.MAY, 1, 1, 0), "ам", 11);
        service.update(userMeal, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdateNoUser() throws Exception {
        UserMeal userMeal = new UserMeal(MEAL_ID, LocalDateTime.of(2020, Month.MAY, 1, 1, 0), "ам", 11);
        service.update(userMeal, ADMIN_ID);
    }

    @Test
    public void testSave() throws Exception {
        UserMeal userMeal = new UserMeal(LocalDateTime.of(2020, Month.MAY, 15, 10, 0), "Пирожек", 410);
        service.save(userMeal, USER_ID);
        List<UserMeal> mealList = MEAL_LIST.stream().collect(Collectors.toList());
        mealList.add(userMeal);
        MATCHER.assertCollectionEquals(mealList, service.getAll(USER_ID));
    }

}
