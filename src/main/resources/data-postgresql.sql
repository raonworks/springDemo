INSERT INTO posts(title, content, author, created_at) VALUES ('title-1', 'content', 'nomad.coding', now());
INSERT INTO posts(title, content, author, created_at) VALUES ('title-2', 'content', 'nomad.coding', now());
INSERT INTO posts(title, content, author, created_at) VALUES ('title-3', 'content', 'nomad.coding', now());
INSERT INTO posts(title, content, author, created_at) VALUES ('title-4', 'content', 'nomad.coding', now());

CREATE EXTENSION IF NOT EXISTS pgcrypto; //pgcrypto 확장 설치 활성화
INSERT INTO users(email, password, name) VALUES ('admin@gmail.com', crypt('1234', gen_salt('bf')), 'nomad.coding');
