CREATE TABLE departments(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
)

CREATE TABLE employees(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  salary DOUBLE,
  birth_date DATE,
  department_id INT,
  FOREIGN KEY (department_id) REFERENCES departments (id)
)

SHOW VARIABLES LIKE 'character\_set\_%';