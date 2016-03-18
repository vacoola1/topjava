package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by r.vakulenko on 16.03.2016.
 */
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        this.save(new User(1, "admin", "admin@gmail.com", "pwd", Role.ROLE_ADMIN, Role.ROLE_USER));
        this.save(new User(2, "user1", "user2@gmail.com", "pwd", Role.ROLE_USER));
        this.save(new User(3, "user3", "user3@gmail.com", "pwd", Role.ROLE_USER));
    }

    @Override
    public User save(User user) {
        if (isUserUnique(user)) {
            if (user.isNew()) {
                user.setId(counter.incrementAndGet());
            }
            repository.put(user.getId(), user);
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.values()
                .stream()
                .filter(user -> (user.getEmail().equals(email))).findAny().get();
    }

    @Override
    public List<User> getAll() {
        return repository.values().stream().sorted((u1, u2) -> (u1.getName().compareTo(u2.getName()))).collect(Collectors.toList());
    }

    private boolean isUserUnique(User user) {
        return repository.values()
                .stream()
                .filter(user1 -> ((!user1.getId().equals(user.getId()))) && (user1.getEmail().equals(user.getEmail())))
                .count() == 0;
    }

}
