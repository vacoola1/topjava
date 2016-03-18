package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {

    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(Integer userId, int id) {

        UserMeal user = repository.get(id);
        if (!user.getUserId().equals(userId)) {
            return false;
        }
        return user.getUserId().equals(userId) && repository.remove(id) != null;
    }

     @Override
    public UserMeal get(Integer userId, int id) {

         UserMeal user = repository.get(id);
         if (!user.getUserId().equals(userId)) {
             user = null;
         }
         return user;
    }

    @Override
    public List<UserMeal> getAll(Integer userId) {
        return getFiltered(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public List<UserMeal> getFiltered(Integer userId, LocalDate startDate, LocalDate endDate) {
        return repository.values()
                .stream()
                .filter(userMeal -> (userMeal.getUserId().equals(userId)
                        && TimeUtil.isDateBetween(userMeal.getDateTime().toLocalDate(), startDate, endDate)))
                .sorted((m1, m2) -> (m1.getDescription().compareTo(m2.getDescription())))
                .collect(Collectors.toList());
    }
}
