DROP DATABASE IF EXISTS test;
CREATE DATABASE test CHARACTER SET utf8 COLLATE utf8_general_ci;

USE test;

DROP TABLE IF EXISTS book;
CREATE TABLE book
(
  id          INT(8) UNSIGNED NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  title       VARCHAR(100)    NOT NULL,
  description VARCHAR(255)    NOT NULL,
  autor       VARCHAR(100)    NOT NULL,
  isbn        VARCHAR(20)     NOT NULL,
  print_year   INT(4)          NOT NULL,
  read_already BOOLEAN         NOT NULL,
  CONSTRAINT id_UNIQUE
  UNIQUE (id)
)
  ENGINE = innoDB
  DEFAULT CHARACTER SET = utf8
;

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Мастер и Маргарита',
   'Один из самых загадочных и удивительных романов XX века.',
   'Михаил Афанасьевич Булгаков',
   '978-5-00112-063-6',
   2010, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Гарри Поттер и узник Азкабана',
   'В Хогвартскую Школу Чародейства и Волшебства пробрался убийца, на счету которого множество жизней и людей, и волшебников.',
   'Джоан Роулинг',
   '978-1-78110-190-2',
   2017, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Зеленая миля',
   'Стивен Кинг приглашает читателей в жуткий мир тюремного блока смертников, откуда уходят, чтобы не вернуться, приоткрывает дверь последнего пристанища тех, кто преступил закон и теперь отсчитывает последние часы.',
   'Стивен Кинг',
   '978-5-17-103631-7',
   2016, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Граф Монте-Кристо',
   'История молодого парижанина, которого приятели в шутку засадили в тюрьму, почерпнута автором в архивах парижской полиции.',
   'Александр Дюма',
   '978-5-389-12741-8',
   2017, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Крестный отец',
   'Роман "Крестный отец" принес своему создателю всемирную известность в 1969 году.',
   'Марио Пьюзо',
   '5-352-01091-0',
   2005, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Властелин колец',
   'Удивительный, сказочный, полный опасностей и приключений, находящийся под вечным противостоянием добра и зла.',
   'Джон Рональд Руэл Толкин',
   '978-5-17-105265-2',
   2016, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Побег из Шоушенка',
   'История невинного человека, приговоренного к пожизненному заключению в тюремном аду.',
   'Стивен Кинг',
   '978-5-21929-0',
   2011, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Три товарища',
   'Трое друзей — Робби, отчаянный автогонщик Кестер и «последний романтик» Ленц прошли Первую мировую войну.',
   'Ремарк Эрих Мария',
   '978-5-17-101186-4',
   2017, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Собака Баскервилей',
   '«Собака Баскервилей» (1900) оказалась не просто первым детективным произведением ХХ века, но и одновременно своего рода каноном классического детектива.',
   'Артур Конан Дойл',
   '978-5-00112-000-1',
   2017, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Инферно',
   'Оказавшись в самом загадочном городе Италии – Флоренции, профессор Лэнгдон, специалист по кодам, символам и истории искусства, неожиданно попадает в водоворот событий, которые способны привести к гибели все человечество…',
   'Дэн Браун',
   '978-5-17-098858-7',
   2013, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Игра престолов',
   'На земли Семи Королевств надвигается долгая изнурительная зима, и война за Железный трон началась.',
   'Джордж Мартин',
   '978-5-271-34087-1 ',
   2012, FALSE );

INSERT INTO book (title, description, autor, isbn, print_year, read_already) VALUES
  ('Атлант расправил плечи',
   'К власти в США приходят социалисты и правительство берет курс на «равные возможности», считая справедливым за счет талантливых и состоятельных сделать богатыми никчемных и бесталанных.',
   'Айн Рэнд',
   '978-5-9614-2004-3 ',
   2011, FALSE );
