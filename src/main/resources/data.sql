INSERT INTO roles(name) VALUES('ROLE_DOCTOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_SUPERADMIN');

INSERT INTO vaccination_center(address, city, name, postal_code) VALUES('2 bis Place Thiers', 'Nancy', 'Centre des Congrès Prouvé', '54000');

INSERT INTO users(email, first_name, last_name, password, phone, username, dtype) VALUES ('doctor@mail.com', 'Michel', 'LeDocteur', '$2a$10$G6Ls1GJ2jO5haXcNxokGnOV/aklGUP9BNd6FxoV.NUGSbR3XVCqkW', '111111111', 'doctor', 'User');
INSERT INTO user_roles(role_id, user_id) VALUES ('1', '1');

INSERT INTO users(email, first_name, last_name, password, phone, username, dtype) VALUES ('admin@mail.com', 'Michel', 'LAdmin', '$2a$10$G6Ls1GJ2jO5haXcNxokGnOV/aklGUP9BNd6FxoV.NUGSbR3XVCqkW', '222222222', 'admin', 'User');
INSERT INTO user_roles(role_id, user_id) VALUES ('2', '2');

INSERT INTO users(email, first_name, last_name, password, phone, username, dtype) VALUES ('superadmin@mail.com', 'Michel', 'LeSuperAdmin', '$2a$10$G6Ls1GJ2jO5haXcNxokGnOV/aklGUP9BNd6FxoV.NUGSbR3XVCqkW', '333333333', 'superadmin', 'User');
INSERT INTO user_roles(role_id, user_id) VALUES ('3', '3');