/*
=======================
=   TABLES & VIEWS    =
=======================
*/

/* ===== Tables ===== */

CREATE TABLE User (
    GTID        CHAR(9)     PRIMARY KEY,
    Password    VARCHAR(30) NOT NULL
);

CREATE TABLE Administrator (
    GTID        CHAR(9)     PRIMARY KEY,
    FOREIGN KEY (GTID) REFERENCES User(GTID)
);

CREATE TABLE Student (
    GTID        CHAR(9)     PRIMARY KEY,
    Email       VARCHAR(30) NOT NULL,
    Name        VARCHAR(30) NOT NULL,
    FOREIGN KEY (GTID) REFERENCES User(GTID)
);

CREATE TABLE Professor (
    GTID        CHAR(9)     PRIMARY KEY,
    FOREIGN KEY (GTID) REFERENCES User(GTID)
);

CREATE TABLE Undergraduate (
    GTID        CHAR(9)     PRIMARY KEY,
    FOREIGN KEY (GTID) REFERENCES Student(GTID)
);

CREATE TABLE Graduate (
    GTID        CHAR(9)     PRIMARY KEY,
    FOREIGN KEY (GTID) REFERENCES Student(GTID)
);

CREATE TABLE Tutor (
    GTID        CHAR(9)     PRIMARY KEY,
    Phone       CHAR(10)    NOT NULL,
    GPA         DEC(3, 2)   NOT NULL,
    FOREIGN KEY (GTID) REFERENCES Student(GTID)
);

CREATE TABLE Course (
    School      VARCHAR(20) NOT NULL,
    Number      VARCHAR(10) NOT NULL,
    PRIMARY KEY (School, Number)
);

CREATE TABLE TimeSlot (
    GTID        CHAR(9)     NOT NULL,
    Time        VARCHAR(5)  NOT NULL,
    Semester    VARCHAR(9)  NOT NULL,
    Weekday     VARCHAR(9)  NOT NULL,
    FOREIGN KEY (GTID) REFERENCES Tutor(GTID),
    PRIMARY KEY (GTID, Time, Weekday, Semester)
);

CREATE TABLE Hires (
    GTID        CHAR(9)     NOT NULL,
    School      VARCHAR(20) NOT NULL,
    Number      VARCHAR(10) NOT NULL,
    Time        VARCHAR(5)  NOT NULL,
    Semester    VARCHAR(9)  NOT NULL,
    Weekday     VARCHAR(9)  NOT NULL,
    FOREIGN KEY (GTID) REFERENCES Undergraduate(GTID),
    FOREIGN KEY (School, Number) REFERENCES Course(School, Number),
    FOREIGN KEY (Time, Weekday, Semester) REFERENCES TimeSlot(Time, Weekday, Semester),
    PRIMARY KEY (GTID, School, Number, Time, Weekday)
);

CREATE TABLE Tutors (
    GTID        CHAR(9)     NOT NULL,
    School      VARCHAR(20) NOT NULL,
    Number      VARCHAR(10) NOT NULL,
    GTA         BIT,
    FOREIGN KEY (GTID) REFERENCES Tutor(GTID),
    FOREIGN KEY (School, Number) REFERENCES Course(School, Number),
    PRIMARY KEY (GTID, School, Number)
);

CREATE TABLE Rates (
    UGTID       CHAR(9)     NOT NULL,
    TGTID       CHAR(9)     NOT NULL,
    School      VARCHAR(20) NOT NULL,
    Number      VARCHAR(10) NOT NULL,
    Semester    VARCHAR(9)  NOT NULL,
    Description VARCHAR(150),
    Rating INT NOT NULL CHECK (Rating > 0 AND Rating < 6),
    FOREIGN KEY (UGTID) REFERENCES Undergraduate(GTID),
    FOREIGN KEY (TGTID) REFERENCES Tutor(GTID),
    FOREIGN KEY (School, Number) REFERENCES Course(School, Number),
    PRIMARY KEY (UGTID, School, Number)
);

CREATE TABLE Recommends (
    PGTID       CHAR(9)     NOT NULL,
    TGTID       CHAR(9)     NOT NULL,
    Description VARCHAR(150),
    Rating      INT         NOT NULL CHECK(Rating > 0 AND Rating < 6),
    FOREIGN KEY (PGTID) REFERENCES Professor(GTID),
    FOREIGN KEY (TGTID) REFERENCES Tutor(GTID),
    PRIMARY KEY (PGTID, TGTID)
);

/*
====== Views =====
*/

/*
CREATE VIEW AdminComplete AS (
    SELECT * FROM User INNER JOIN Administrator
    ON User.GTID=Administrator.GTID
)

CREATE VIEW ProfComplete AS (
    SELECT * FROM User INNER JOIN Professor
    ON User.GTID=Professor.GTID
)

CREATE VIEW UndergradComplete AS (
    SELECT * FROM (User INNER JOIN Student ON User.GTID=Student.GTID)
    INNER JOIN Undergraduate ON Student.GTID=Undergraduate.GTID
)

CREATE VIEW GradComplete AS (
    SELECT * FROM (User INNER JOIN Student ON User.GTID=Student.GTID)
    INNER JOIN Graduate ON Student.GTID=Graduate.GTID
)

CREATE VIEW TutorComplete AS (
    SELECT * FROM (User INNER JOIN Student ON User.GTID=Student.GTID)
    INNER JOIN Tutor ON Student.GTID=Tutor.GTID
)

CREATE VIEW GTA AS (
	SELECT * FROM (Tutor INNER JOIN Tutors ON Tutor.GTID=Tutors.GTID)
	WHERE Tutors.GTA=1
)

CREATE VIEW nonGTA AS (
	SELECT * FROM (Tutor INNER JOIN Tutors ON Tutor.GTID=Tutors.GTID)
	WHERE Tutors.GTA=0
)

CREATE VIEW GTARatings AS (
	SELECT *
	FROM Ratings INNER JOIN GTA
	ON Ratings.School = GTA.School AND Ratings.Number = GTA.Number
)

CREATE VIEW nonGTARatings AS (
	SELECT *
	FROM Ratings INNER JOIN nonGTA
	ON Ratings.School = nonGTA.School AND Ratings.Number = nonGTA.Number
)

CREATE VIEW AllTutorRatings AS (
    SELECT TutorComplete.GTID, TutorComplete.Name, TutorComplete.Email,
        AVG(Recommends.Rating) AS AvgProfRating,
        COUNT(Recommends.PGTID) AS NumProf,
        AVG(Rates.Rating) AS AvgStudentRating,
        COUNT(Rates.PGTID) AS NumStudent
    FROM (TutorComplete LEFT INNER JOIN Recommends ON Recommends.TGTID=TutorComplete.GTID) LEFT INNER JOIN Rates ON Rates.TGTID=Recommends.TGTID
    GROUP BY TutorComplete.Name, TutorComplete.Email
)
*/
