package ru.javawebinar.topjava.model.to;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMealWithExceed {
    protected final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    private final Integer userId;

    public UserMealWithExceed(Integer userId, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this(userId, null, dateTime, description, calories, exceed);
    }

    public UserMealWithExceed(Integer userId, Integer id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.userId = userId;
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public Integer getId() {
        return id;
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

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }
}
