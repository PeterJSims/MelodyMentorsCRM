-- noinspection SqlNoDataSourceInspectionForFile

create table if not exists Student(
    id  int  NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    instrument VARCHAR(25),
    customer_since DATE,
    birth_year YEAR,
    street_address VARCHAR(60),
    zip VARCHAR(5),
    phone_number VARCHAR(12),
    email_address VARCHAR(50),
    teacher_name VARCHAR(50),
    primary key(id)
    );