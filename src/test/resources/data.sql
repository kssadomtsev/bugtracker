DELETE FROM comment;
DELETE FROM ticket;
DELETE FROM user_role;
DELETE FROM users;
DELETE FROM company;

INSERT INTO company(id, name, address)
VALUES (1, 'R&D company', 'Moscow, Tverskaya str., building 45'),
       (2, 'Customer company', 'Saint Petersburg, Nevskiy avenue, building 1A'),
       (3, 'Partner company', 'Vladivostok, Morskaya str., building 16');

INSERT INTO users(id, first_name, last_name, email, password, company_id)
VALUES (1, 'Alice', 'White', 'alice_dev@rd.com', '$2a$10$q3SrkFSQAJ1ueE/yrG8c4OmCkvqRwxs5jlZBqdere7jAkT3o5I8bC', 1),
       (2, 'John', 'Johnson', 'john_dev@rd.com', '$2a$10$q3SrkFSQAJ1ueE/yrG8c4OmCkvqRwxs5jlZBqdere7jAkT3o5I8bC', 1),
       (3, 'Anna', 'Tatler', 'anna_qa@rd.com', '$2a$10$q3SrkFSQAJ1ueE/yrG8c4OmCkvqRwxs5jlZBqdere7jAkT3o5I8bC', 1),
       (4, 'Bob', 'Brown', 'bob_admin@rd.com', '$2a$10$q3SrkFSQAJ1ueE/yrG8c4OmCkvqRwxs5jlZBqdere7jAkT3o5I8bC', 1),

       (5, 'Nick', 'Black', 'nick@customer.com', '$2a$10$q3SrkFSQAJ1ueE/yrG8c4OmCkvqRwxs5jlZBqdere7jAkT3o5I8bC', 2),
       (6, 'Nina', 'Wasserman', 'nina@customer.com', '$2a$10$q3SrkFSQAJ1ueE/yrG8c4OmCkvqRwxs5jlZBqdere7jAkT3o5I8bC', 2),

       (7, 'Swen', 'Swenson', 'swen@partner.com', '$2a$10$q3SrkFSQAJ1ueE/yrG8c4OmCkvqRwxs5jlZBqdere7jAkT3o5I8bC', 3);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 2),
       (2, 2),
       (3, 3),
       (4, 1),
       (5, 4),
       (6, 4),
       (7, 4);

INSERT INTO ticket(id, severity, created_at, updated_at, solved_at, reporter_id, responsible_id, status, description)
VALUES (1, 'LOW','2022-05-05 21:36:48', '2022-05-05 21:36:48', null, 5, null, 'NEW', 'Low severity software bug'),
       (2, 'MINOR','2022-04-05 21:36:48', '2022-05-07 21:36:48', null, 6, 1, 'ASSIGNED', 'Minor severity software bug'),
       (3, 'MAJOR','2022-04-23 21:36:48', '2022-04-27 21:36:48', null, 7, 2, 'SOLVED','Major severity software bug'),
       (4, 'CRITICAL','2022-04-13 21:36:48', '2022-04-17 21:36:48', null, 5, 1, 'SOLVED','Critical severity software bug'),
       (5, 'MAJOR','2022-04-14 21:36:48', '2022-04-19 21:36:48','2022-05-05 21:36:48', 6, 2, 'CLOSED','Major severity software bug 2'),
       (6, 'LOW','2022-05-01 21:36:48', '2022-05-08 21:36:48', '2022-05-05 21:36:48', 7, 1, 'REOPEN', 'Low severity software bug 2');

INSERT INTO comment(id, ticket_id, message, created_at, author_id)
VALUES (1, 2, 'Assigned ticket to Alice White', '2022-05-07 21:36:48', 1),

       (2, 3, 'Assigned ticket to John Johnson', '2022-04-24 21:36:48', 4),
       (3, 3, 'Solved by John Johnson', '2022-04-25 21:36:48', 2),

       (4, 4, 'Assigned ticket to Alice White', '2022-04-15 21:36:48', 1),
       (5, 4, 'Solved by  Alice White', '2022-04-17 21:36:48', 1),

       (6, 5, 'Assigned ticket to John Johnson', '2022-04-15 21:36:48', 2),
       (7, 5, 'Solved by John Johnson', '2022-04-16 21:36:48', 2),
       (8, 5, 'Verified and closed by Anna Tatler', '2022-04-19 21:36:48', 3),

       (9, 6, 'Assigned ticket to Alice White', '2022-05-02 21:36:48', 1),
       (10, 6, 'Solved by Alice White', '2022-05-03 21:36:48', 1),
       (11, 6, 'Verified and closed by Anna Tatler', '2022-05-05 21:36:48', 3),
       (12, 6, 'Reopened by Swen Swenson', '2022-05-08 21:36:48', 7);

SELECT setval('company_id_seq', 4, true);
SELECT setval('users_id_seq', 7, true);
SELECT setval('ticket_id_seq', 6, true);
SELECT setval('comment_id_seq', 12, true);