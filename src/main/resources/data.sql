INSERT INTO PUBLIC.DIFFICULTY (VALUE, LABEL)
VALUES (1, 'Beginner');
INSERT INTO PUBLIC.DIFFICULTY (VALUE, LABEL)
VALUES (2, 'Easy');
INSERT INTO PUBLIC.DIFFICULTY (VALUE, LABEL)
VALUES (3, 'Medium');
INSERT INTO PUBLIC.DIFFICULTY (VALUE, LABEL)
VALUES (4, 'Hard');
INSERT INTO PUBLIC.DIFFICULTY (VALUE, LABEL)
VALUES (5, 'Expert');

INSERT INTO PUBLIC.TECHNOLOGY_ENTITY (ID, CREATE_DATE, UPDATE_DATE, DESCRIPTION, IMAGE, NAME)
VALUES (1, '2021-11-10 20:18:34.188138', '2021-11-10 20:26:08.386956',
        'Lorem Ipsum is simply dummy text of the printing and typesetting industrdsy. Lorem Ipsum has been the industry''s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
        '3fb76742-399c-4922-a707-be1170420c79.jpg', 'Java Script');

INSERT INTO PUBLIC.USER_ACCOUNT (ID, EMAIL, ENABLED, FIRST_NAME, IS_USING2FA, LAST_NAME, PASSWORD, SECRET)
VALUES (1, 'admin@admin.com', true, 'admin', false, 'admin',
        '$2a$11$02x3bN05vZqctGn.J0XJXuPvSdkN3DDXoDJeXbmAQU218zVzsE5ge', null);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE)
VALUES (1, '<script>', null, 'some code');

INSERT INTO PUBLIC.QUESTION (ID, BODY, CORRECT_ANSWER_ID, CREATOR_ID, DIFFICULTY_VALUE, TECHNOLOGY_ENTITY_ID,
                             CODE_ATTACHMENT, CODE_LANG)
VALUES (1, 'Inside which HTML element do we put the JavaScript?', 1, null, 2, 1, 'some java code', 'java');

INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID)
VALUES (2, '<js>', 1);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE)
VALUES (3, '<javascript>', 1, 'some code 111');
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE)
VALUES (4, '<scriptiong', 1, 'some code 111');


INSERT INTO PUBLIC.USER_ATTEMPT (ID, END_DATE, GAME_TYPE, START_DATE, TECHNOLOGY_ENTITY_ID, USER_ACCOUNT_ID)
VALUES ('fbc02259-c608-493d-b13e-592d9314f13f', null, 'QUIZ', '2021-11-10 20:29:07.091371', 1, 1);

INSERT INTO PUBLIC.USER_ATTEMPT (ID, END_DATE, GAME_TYPE, START_DATE, TECHNOLOGY_ENTITY_ID, USER_ACCOUNT_ID)
VALUES ('jakas-tam-proba', null, 'QUIZ', '2021-11-09 20:29:07.091371', 1, 1);

INSERT INTO PUBLIC.HISTORY_ENTRY (ID, ANSWER_RESULT, DATE, QUESTION_ID, USER_ATTEMPT_ID)
VALUES (1, true, '2021-11-10 20:30:02.092545', 1, 'fbc02259-c608-493d-b13e-592d9314f13f');
INSERT INTO PUBLIC.HISTORY_ENTRY (ID, ANSWER_RESULT, DATE, QUESTION_ID, USER_ATTEMPT_ID)
VALUES (2, false, '2021-11-10 20:35:31.947413', 1, 'fbc02259-c608-493d-b13e-592d9314f13f');
INSERT INTO PUBLIC.HISTORY_ENTRY (ID, ANSWER_RESULT, DATE, QUESTION_ID, USER_ATTEMPT_ID)
VALUES (3, false, '2021-11-10 20:58:21.997822', 1, 'fbc02259-c608-493d-b13e-592d9314f13f');

INSERT INTO PUBLIC.HISTORY_ENTRY (ANSWER_RESULT, DATE, QUESTION_ID, USER_ATTEMPT_ID)
VALUES (false, '2021-11-09 20:35:31.947413', 1, 'jakas-tam-proba');
INSERT INTO PUBLIC.HISTORY_ENTRY (ANSWER_RESULT, DATE, QUESTION_ID, USER_ATTEMPT_ID)
VALUES (false, '2021-11-09 20:58:21.997822', 1, 'jakas-tam-proba');

