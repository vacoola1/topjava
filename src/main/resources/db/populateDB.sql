DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories) VALUES
  (100000, MAKE_TIMESTAMP(2015, 5, 30, 10, 0, 0), 'Завтрак', 500),
  (100000, MAKE_TIMESTAMP(2015, 5, 30, 13, 0, 0), 'Обед', 1000),
  (100000, MAKE_TIMESTAMP(2015, 5, 30, 20, 0, 0), 'Ужин', 500),
  (100000, MAKE_TIMESTAMP(2015, 5, 31, 10, 0, 0), 'Завтрак', 500),
  (100000, MAKE_TIMESTAMP(2015, 5, 31, 13, 0, 0), 'Обед', 1000),
  (100000, MAKE_TIMESTAMP(2015, 5, 31, 20, 0, 0), 'Ужин', 510),
  (100001, MAKE_TIMESTAMP(2015, 6, 1, 14, 0, 0), 'Админ ланч', 510),
  (100001, MAKE_TIMESTAMP(2015, 6, 1, 21, 0, 0), 'Админ ужин', 1500);
