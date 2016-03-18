package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.LoggedUser;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity{

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final Integer userId;

    public UserMeal(Integer userId, LocalDateTime dateTime, String description, int calories) {
        this(userId ,null, dateTime, description, calories);
    }

    public UserMeal(Integer userId, Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.userId = userId;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
