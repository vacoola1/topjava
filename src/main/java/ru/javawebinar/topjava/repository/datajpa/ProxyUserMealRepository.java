package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Modifying
    @Transactional
    @Query(name = UserMeal.DELETE)
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query(name = UserMeal.GET)
    UserMeal get(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query(name = UserMeal.GET_FETСH_EAGER)
    UserMeal getWithUser(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query(name = UserMeal.ALL_SORTED)
    List<UserMeal> getAll(@Param("userId") Integer userId);

    @Query(name = UserMeal.GET_BETWEEN)
    List<UserMeal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") Integer userId);


}
