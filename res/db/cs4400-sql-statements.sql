/*
=======================
=   SQL STATEMENTS    =
=======================
*/

/* ===== LOGIN SCREEN ===== */

/* Checking valid login */
SELECT * FROM User WHERE GTID='700000007' AND Password='student';
SELECT * FROM User WHERE GTID='700000015' AND Password='student';
SELECT * FROM User WHERE GTID='900000003' AND Password='prof';
SELECT * FROM User WHERE GTID='555555555' AND Password='admin';

/* Checking user type */
SELECT * FROM Student WHERE GTID='555555555';
SELECT * FROM Tutor WHERE GTID='555555555';
SELECT * FROM Professor WHERE GTID='555555555';
SELECT * FROM Administrator WHERE GTID='555555555';


/* ===== SEARCH & SCHEDULE SCREEN ===== */

/* Get courses which has available tutors */
SELECT DISTINCT School, Number
    FROM AvailableTimeSlots NATURAL JOIN Tutors
    WHERE Semester="Summer";

/* Find available tutors */
SELECT T.Name, T.Email, T.AvgProf, T.NumProf, T.AvgProf, T.NumProf, A.Weekday, A.Time
    FROM TutorRatings T NATURAL JOIN AvailableTimeSlots A
        NATURAL JOIN Tutors
    WHERE Semester="Summer" AND School="CS" AND Number="4400"
        AND Weekday IN ("Monday","Wednesday","Friday")
        AND Time IN ("1PM","10AM")
    ORDER BY T.AvgStudent ASC, T.GTID ASC;

/* Schedule a tutor */
INSERT INTO Hires VALUES ("700000000","CS","1332","10AM","Summer","Monday");


/* ===== RATES SCREEN ===== */
SELECT DISTINCT School, Number FROM Hires
    WHERE GTID="700000000" AND Semester="Summer";

SELECT GTID from Tutor WHERE Name="bob 16";

INSERT INTO Rates VALUES ("700000007", "700000016", "CS", "1332", "Fall", "This tutor was the best tutor I've ever had.", 1);


/* ===== APPLY SCREEN ===== */

/* gets all courses */
SELECT * FROM Course;

/* insert tutor as tutor candidate, with recommendation validation */
SELECT TGTID FROM Recommends WHERE TGTID="700000015";
INSERT IGNORE INTO Tutor VALUES ("700000015","6786786789",3.5);

/* insert all courses that tutor can tutor for, with gta validation */
SELECT GTID FROM Graduate WHERE GTID="700000015";
INSERT IGNORE INTO Tutors VALUES ("700000015","CS","1331",0);
INSERT IGNORE INTO Tutors VALUES ("700000015","CS","2110",0);

/* insert all courses into timeslots */
INSERT IGNORE INTO TimeSlot VALUES ("700000015","2PM","Summer","Monday");
INSERT IGNORE INTO TimeSlot VALUES ("700000015","2PM","Summer","Tuesday");
INSERT IGNORE INTO TimeSlot VALUES ("700000015","2PM","Summer","Wednesday");
INSERT IGNORE INTO TimeSlot VALUES ("700000015","2PM","Summer","Thursday");
INSERT IGNORE INTO TimeSlot VALUES ("700000015","2PM","Summer","Friday");


/* ===== FIND SCREEN ===== */
SELECT Weekday, Time, Name, Email, School, Number
    FROM TutorScheduleInfo
    WHERE TGTID="700000015" AND Semester="Summer";


/* ===== RECOMMENDS SCREEN ===== */
INSERT INTO Recommends VALUES ("900000003", "700000000", "This student was the best student I've ever had.", 1);


/* ===== SUM1 SCREEN ===== */
select H.School, H.Number, H.Semester,
        count(DISTINCT H.GTID) as SGTID, count(DISTINCT S.GTID) as TGTID
    from Hires H left outer join TimeSlot S
    on S.Time=H.Time and S.Weekday=H.Weekday and S.Semester=H.Semester
    where H.Semester IN ("Fall","Spring")
    group by H.School, H.Number, H.Semester
    order by H.School ASC


/* ===== SUM2 SCREEN ===== */
select R.School, R.Number, R.Semester,
        count(T.GTA) as nonTA, avg(R.Rating) as avgRating
    from Rates R left outer join Tutors T
    on T.GTID=R.TGTID and T.School=R.School and T.Number=R.Number
    where T.GTA=0 and semester in("Fall","Spring")
    group by R.School, R.Number, R.Semester
    order by R.School ASC

select R.School, R.Number, R.Semester,
        count(T.GTA) as TA, avg(R.Rating) as avgRating
    from Rates R left outer join Tutors T
    on T.GTID=R.TGTID and T.School=R.School and T.Number=R.Number
    where T.GTA=1 and semester in("Fall","Spring")
    group by R.School, R.Number, R.Semester
    order by R.School ASC


/* ===== VIEWS ===== */
CREATE OR REPLACE VIEW TutorScheduleInfo AS (
	SELECT T.GTID AS TGTID,
	H.GTID AS UGTID, S.Email, S.Name,
	T.School, T.Number,
	H.Time, H.Semester, H.Weekday
	FROM (Tutors T INNER JOIN Hires H ON T.School=H.School AND T.Number=H.Number)
	INNER JOIN TimeSlot TS ON TS.Time=H.Time AND TS.Semester=H.Semester AND TS.Weekday=H.Weekday AND TS.GTID=T.GTID
	INNER JOIN Student S ON H.GTID=S.GTID
);

CREATE OR REPLACE VIEW TutorComplete AS (
    SELECT * FROM User NATURAL JOIN Student NATURAL JOIN Tutor
);

CREATE OR REPLACE VIEW TimeSlotComplete AS (
    SELECT * FROM TutorComplete TC NATURAL JOIN TimeSlot TS
);

CREATE OR REPLACE VIEW AvailableTimeSlots AS (
	SELECT DISTINCT T.GTID, T.Name, T.Email, T.Time, T.Semester, T.Weekday
		FROM TimeSlotComplete T LEFT OUTER JOIN Hires H
		ON T.Time=H.Time AND T.Semester=H.Semester AND T.Weekday=H.Weekday
		WHERE H.GTID IS NULL
);

CREATE OR REPLACE VIEW TutorRatings AS (
	SELECT T.GTID, T.Name, T.Email, AVG(DISTINCT P.Rating) AvgProf, COUNT(DISTINCT P.PGTID) NumProf,
			AVG(DISTINCT S.Rating) AvgStudent, COUNT(DISTINCT S.UGTID) NumStudent
		FROM AvailableTimeSlots T INNER JOIN Recommends P
			ON T.GTID=P.TGTID
		INNER JOIN Rates S
			ON T.GTID=S.TGTID
			AND S.TGTID=P.TGTID
		GROUP BY T.GTID, T.Name, T.Email
		ORDER BY AvgStudent ASC, T.Name ASC
);
