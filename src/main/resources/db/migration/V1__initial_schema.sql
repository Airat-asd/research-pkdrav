drop table if exists blood_test;
drop table if exists patient;

create table patient (
        id varchar(255) not null primary key,
        date_of_birth date,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        middle_name varchar(255),
        snils varchar(255)
    );

create table blood_test (
        id varchar(255) not null primary key,
        hgb smallint,
        mchc smallint,
        mcv smallint,
        rbc int,
        date date,
        patient_id varchar(255)
    );

insert into patient values
('1750f483-1684-4a06-b72e-485a559a2359', '11-01-2000', 'Алексей', 'Иванов', 'Сергеевич', '123-456-789 00'),
('1750f484-1684-4a06-b72e-485a559a2359', '11-02-2001', 'Иван', 'Сергеев', 'Сергеевич', '123-456-789 01'),
('1750f485-1684-4a06-b72e-485a559a2359', '01-06-2010', 'Сергей', 'Алексеев', 'Иванович', '123-456-789 02'),
('1750f486-1684-4a06-b72e-485a559a2359', '10-01-1950', 'Игорь', 'Иванов', 'Сергеевич', '123-456-789 03'),
('1750f487-1684-4a06-b72e-485a559a2359', '11-01-1999', 'Алла', 'Курчатова', 'Николаевна', '123-456-789 04');

insert into blood_test values
('1750f488-1684-4a06-b72e-485a559a2359', 11, 12, 13, 440, '11-01-2000', '1750f483-1684-4a06-b72e-485a559a2359'),
('1750f489-1684-4a06-b72e-485a559a2359', 10, 13, 14, 540, '11-02-2001', '1750f484-1684-4a06-b72e-485a559a2359'),
('1750f490-1684-4a06-b72e-485a559a2359', 9, 15, 6, 340, '01-06-2010', '1750f485-1684-4a06-b72e-485a559a2359'),
('1750f491-1684-4a06-b72e-485a559a2359', 8, 14, 7, 640, '09-01-1950', '1750f486-1684-4a06-b72e-485a559a2359'),
('1750f492-1684-4a06-b72e-485a559a2359', 7, 16, 9, 740, '11-01-1999', '1750f487-1684-4a06-b72e-485a559a2359');

commit;