INSERT INTO PUBLIC.USER_ACCOUNT (EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, CREATE_DATE, POINTS)
VALUES ('admin@admin.com', 'admin', 'admin',
        '$2a$11$02x3bN05vZqctGn.J0XJXuPvSdkN3DDXoDJeXbmAQU218zVzsE5ge', {ts '2012-09-17 18:47:52.69'}, 11);

insert into ROLE (NAME)
values ('ROLE_ADMIN');
insert into ROLE (NAME)
values ('ROLE_MOD');
insert into ROLE (NAME)
values ('ROLE_USER');

insert into USERS_ROLES (USER_EMAIL, ROLE_NAME)
VALUES ('admin@admin.com', 'ROLE_USER');
insert into USERS_ROLES (USER_EMAIL, ROLE_NAME)
VALUES ('admin@admin.com', 'ROLE_MOD');
insert into USERS_ROLES (USER_EMAIL, ROLE_NAME)
VALUES ('admin@admin.com', 'ROLE_ADMIN');

-- Technology 1
INSERT INTO PUBLIC.TECHNOLOGY (ID, CREATE_DATE, UPDATE_DATE, DESCRIPTION, IMAGE, NAME)
VALUES (1, '2021-11-10 20:18:34.188138', '2021-11-10 20:26:08.386956',
        'JavaScript (JS) to skryptowy (interpretowany lub kompilowany metodą JIT) język programowania, w którym funkcje są "obywatelami pierwszej kategorii" - obiektami, które można przechowywać w zmiennych jako referencje i przekazywać jak każde inne obiekty. Chociaż JavaScript jest najbardziej znany jako język skryptowy dla stron internetowych, używa go również wiele środowisk poza przeglądarką, takich jak Node.js, Apache CouchDB czy Adobe Acrobat. JavaScript jest językiem opartym na prototypach, wielu paradygmatach, dynamicznej składni, zorientowanym obiektowo, o stylu imperatywnym i deklaratywnym (np. programowanie funkcyjne).
Ta sekcja naszego serwisu jest poświęcona samemu językowi JavaScript, a nie zagadnieniom związanym ze stronami internetowymi lub innymi środowiskami hosta. Informacje dotyczące Interfejsów API specyficznych dla stron internetowych, prosimy szukać w źródłach: Internetowe interfesy API i DOM.',
        'f5aad04b-d291-475e-b142-d1ce4a3a2f5f.jpg', 'Java Script');

-- Technology 1 Question 1

INSERT INTO PUBLIC.QUESTION (ID, BODY, CREATOR_EMAIL, DIFFICULTY, TECHNOLOGY_ID,
                             CODE_ATTACHMENT, CODE_LANG, PUBLISHED)
VALUES (1, 'W jakim elemencie HTML umieszczamy kod Javascript?', 'admin@admin.com', 2, 1, null, null, true);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (1, '<script>', 1, null, true);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (2, '<js>', 1, null, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (3, '<javascript>', 1, null, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (4, '<scriptiong', 1, null, false);

-- Technology 1 Question 2
INSERT INTO PUBLIC.QUESTION (ID, BODY, CREATOR_EMAIL, DIFFICULTY, TECHNOLOGY_ID,
                             CODE_ATTACHMENT, CODE_LANG, PUBLISHED)
VALUES (2, 'Jaki jest poprawny kod JavaScript do zmiany zwartości elementu HTML przedstawionego niżej?', null, 2, 1,
        '<p id="demo">This is a demonstration.</p>', 'html', false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (5, ' document.getElementById("demo").innerHTML = "Hel, falselo World!";', 2, null, true);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CORRECT)
VALUES (6, 'document.getElementByName("p").innerHTML = "Hello World!";', 2, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CORRECT)
VALUES (7, '#demo.innerHTML = "Hello World!";', 2, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CORRECT)
VALUES (8, 'document.getElement("p").innerHTML = "Hello World!";', 2, false);

-- Technology 2
INSERT INTO PUBLIC.TECHNOLOGY (ID, CREATE_DATE, UPDATE_DATE, DESCRIPTION, IMAGE, NAME)
VALUES (2, '2021-11-10 20:18:34.188138', '2021-11-10 20:26:08.386956',
        'Java – współbieżny, oparty na klasach, obiektowy język programowania ogólnego zastosowania[5]. Został stworzony przez grupę roboczą pod kierunkiem Jamesa Goslinga z firmy Sun Microsystems. Java jest językiem tworzenia programów źródłowych kompilowanych do kodu bajtowego, czyli postaci wykonywanej przez maszynę wirtualną. Język cechuje się silnym typowaniem. Jego podstawowe koncepcje zostały przejęte z języka Smalltalk (maszyna wirtualna, zarządzanie pamięcią) oraz z języka C++ (duża część składni i słów kluczowych).',
        'c031203d-8f16-4c1d-8850-1ad318890e28.png', 'Java');

-- Technology 2 Question 1
INSERT INTO PUBLIC.QUESTION (ID, BODY, CREATOR_EMAIL, DIFFICULTY, TECHNOLOGY_ID,
                             CODE_ATTACHMENT, CODE_LANG, PUBLISHED)
VALUES (3, 'Jaki jest poprawna składnia do wypisania "Hello World w Javie?', null, 1, 2, null, null, true);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (9, ' System.out.println("Hello World");', 3, null, true);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CORRECT)
VALUES (10, ' echo("Hello World");', 3, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (11, ' Console.WriteLine("Hello World");', 3, null, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (12, ' print ("Hello World");', 3, null, false);

-- Technology 2 Question 2
INSERT INTO PUBLIC.QUESTION (ID, BODY, CREATOR_EMAIL, DIFFICULTY, TECHNOLOGY_ID,
                             CODE_ATTACHMENT, CODE_LANG, PUBLISHED)
VALUES (4, 'Co wypisze kod?', null, 3, 2, 'import java.util.Arrays;
import java.util.Comparator;
public class ComparatorTest {
public static void main(String args[])
{
String[] ar= {“c”,”d”,”b”,”a”,”e”};
InnerClass in=new InnerClass();
Arrays.parallelSort(ar, in);
for(String str : ar)
System.out.println(str +””);
System.out.println(Arrays.binarySearch(ar, “b”));
}
static class InnerClass implements Comparator<String>
{
public int compare(String s1, String s2)
{
return s2.compareTo(s1);
}
}
}', 'java', true);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CODE, CORRECT)
VALUES (13, ' e d c b a -1', 4, null, true);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CORRECT)
VALUES (14, ' a b c 0 e d', 4, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CORRECT)
VALUES (15, ' d b c e a 1', 4, false);
INSERT INTO PUBLIC.ANSWER (ID, BODY, QUESTION_ID, CORRECT)
VALUES (16, ' e b a d c', 4, false);


INSERT INTO PUBLIC.USER_ATTEMPT (ID, END_DATE, START_DATE, USER_ACCOUNT_EMAIL)
VALUES ('fbc02259-c608-493d-b13e-592d9314f13f', null, '2021-11-10 20:29:07.091371', 'admin@admin.com');

INSERT INTO PUBLIC.USER_ATTEMPT (ID, END_DATE, START_DATE, USER_ACCOUNT_EMAIL)
VALUES ('jakas-tam-proba', null, '2021-11-09 20:29:07.091371', 'admin@admin.com');

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

