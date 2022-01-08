INSERT INTO PUBLIC.USER_ACCOUNT (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, CREATE_DATE, POINTS)
VALUES ('admin@admin.com', 'admin', 'admin',
        '$2a$11$02x3bN05vZqctGn.J0XJXuPvSdkN3DDXoDJeXbmAQU218zVzsE5ge', {ts '2012-09-17 18:47:52.69'}, 11);
INSERT INTO PUBLIC.USER_ACCOUNT (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, CREATE_DATE, POINTS)
VALUES ('user@user.com', 'user', 'user',
        '$2a$11$SIKI1dTxV7z8Uozk9d5soee.LDfnOKbJjRx1KPvuAw8w7ns3TILAG', {ts '2012-09-17 18:47:52.69'}, 2);

insert into ROLE (NAME)
values ('ROLE_ADMIN');
insert into ROLE (NAME)
values ('ROLE_MOD');
insert into ROLE (NAME)
values ('ROLE_USER');

-- ADMIN
insert into USERS_ROLES (USER_EMAIL, ROLE_NAME)
VALUES ('admin@admin.com', 'ROLE_USER');
insert into USERS_ROLES (USER_EMAIL, ROLE_NAME)
VALUES ('admin@admin.com', 'ROLE_MOD');
insert into USERS_ROLES (USER_EMAIL, ROLE_NAME)
VALUES ('admin@admin.com', 'ROLE_ADMIN');
-- USER
insert into USERS_ROLES (USER_EMAIL, ROLE_NAME)
VALUES ('user@user.com', 'ROLE_USER');
