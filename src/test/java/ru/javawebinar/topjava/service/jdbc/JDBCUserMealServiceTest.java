package ru.javawebinar.topjava.service.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

/**
 * Created by r.vakulenko on 11.04.2016.
 */
@ActiveProfiles({Profiles.POSTGRES,Profiles.JDBC})
public class JDBCUserMealServiceTest extends UserMealServiceTest {
}
