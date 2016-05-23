package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.TestUtil.userHttpBasic;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;

/**
 * GKislin
 * 10.04.2015.
 */
public class UserMealControllerTest extends AbstractControllerTest {

    @Test
    @WithMockUser(username = "USER")
    public void testMealList() throws Exception {
        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("mealList"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/mealList.jsp"))
//                .andExpect(model().attribute("mealList", hasSize(6)))
//                .andExpect(model().attribute("mealList", hasItem(
//                        allOf(
//                                hasProperty("id", is(MEAL1_ID)),
//                                hasProperty("description", is(MEAL1.getDescription()))
//                        )
//                )))
        ;
    }
}