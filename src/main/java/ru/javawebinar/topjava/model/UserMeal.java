package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;
import ru.javawebinar.topjava.util.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */

@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal um WHERE um.user = :user AND um.id=:id"),
        @NamedQuery(name = UserMeal.GET, query = "SELECT um FROM UserMeal um WHERE um.user =:user AND um.id = :id"),
        @NamedQuery(name = UserMeal.GET_ALL, query = "SELECT um FROM UserMeal um WHERE um.user=:user ORDER BY um.dateTime DESC"),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT um FROM UserMeal um WHERE um.user=:user AND um.dateTime BETWEEN :startDate AND :endDate ORDER BY um.dateTime DESC"),
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(name = "meals_unique_user_datetime_idx", columnNames = {"user_id", "date_time"})})
public class UserMeal extends BaseEntity {

    public static final String DELETE = "UserMeal.delete";
    public static final String GET = "UserMeal.get";
    public static final String GET_ALL = "UserMeal.getAll";
    public static final String GET_BETWEEN = "UserMeal.getBetween";

    @Column(name = "date_time")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dateTime;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    protected int calories;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        this(null, null, dateTime, description, calories);
    }
    public UserMeal(Integer id, User user, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.user = user;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
