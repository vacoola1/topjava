package ru.javawebinar.topjava.service.datajpa;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceTest;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

@ActiveProfiles({Profiles.POSTGRES,Profiles.DATAJPA})
public class DataJPAUserMealServiceTest extends UserMealServiceTest {
    @Test
    public void testGetWithUser() throws Exception {
        UserMeal actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, actual);
    }

}