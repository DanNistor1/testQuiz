CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL);

CREATE TABLE question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(30) NOT NULL,
    questionType VARCHAR(30) NOT NULL CHECK (questionType = 'OPEN' or questionType = 'CHOICE'),
    questionDifficulty VARCHAR(30) NOT NULL CHECK (questionDifficulty = 'LOW' OR questionDifficulty = 'MEDIUM' OR questionDifficulty = 'HIGH')
    category_id INT,
    constraint category_fk foreign key (category_id) references category(id) ON DELETE CASCADE
    );

alter table question add column category_id int;
alter table question add constraint category_fk foreign key (category_id) references category(id) ON DELETE CASCADE;

CREATE TABLE answer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(30) NOT NULL,
    value boolean NOT NULL CHECK (value = 'TRUE' or value = 'FALSE'),
    question_id INT,
    constraint question_fk foreign key (question_id) references question(id) ON DELETE CASCADE
    );