package ru.javawebinar.topjava.service.JPA;


import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserMealServiceTest;

@ActiveProfiles({Profiles.POSTGRES,Profiles.JPA})
public class JPAUserMealServiceTest extends UserMealServiceTest {

}