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
    Description TEXT,
    Rating INT NOT NULL CHECK (Rating > 0 AND Rating < 6),
    FOREIGN KEY (UGTID) REFERENCES Undergraduate(GTID),
    FOREIGN KEY (TGTID) REFERENCES Tutor(GTID),
    FOREIGN KEY (School, Number) REFERENCES Course(School, Number),
    PRIMARY KEY (UGTID, School, Number)
);

CREATE TABLE Recommends (
    PGTID       CHAR(9)     NOT NULL,
    TGTID       CHAR(9)     NOT NULL,
    Description TEXT,
    Rating      INT         NOT NULL CHECK(Rating > 0 AND Rating < 6),
    FOREIGN KEY (PGTID) REFERENCES Professor(GTID),
    FOREIGN KEY (TGTID) REFERENCES Student(GTID),
    PRIMARY KEY (PGTID, TGTID)
);
