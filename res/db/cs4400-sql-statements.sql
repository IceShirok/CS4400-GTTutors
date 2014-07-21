/*
=======================
=   SQL STATEMENTS    =
=======================
*/

/* SEARCH SCREEN */

/* Get courses which has available tutors */
SELECT DISTINCT T.School, T.Number
    FROM TimeSlotComplete T LEFT OUTER JOIN Hires H
	ON T.School=H.School AND T.Number=H.Number
	AND T.Time=H.Time AND T.Semester=H.Semester AND T.Weekday=H.Weekday
	WHERE H.GTID IS NULL AND T.Semester="Summer";

/* Find available tutors */
SELECT T.Name, T.Email, T.AvgProf, T.NumProf, T.AvgProf, T.NumProf
    FROM TutorRatings T NATURAL JOIN AvailableTimeSlots
    WHERE Semester="Summer"
        AND School="CS"
        AND Number="4400"
        AND Weekday IN ("Thursday","Friday")
        AND Time IN ("12PM","1PM")
    GROUP BY T.GTID
    ORDER BY T.AvgStudent ASC, T.GTID ASC;


/* RECOMMENDS SCREEN */
INSERT INTO Recommends VALUES ("900000003", "700000000", "This student was the best student I've ever had.", 1);


/* VIEWS */
/*
CREATE VIEW TutorScheduleInfo AS (
	SELECT T.GTID AS TGTID,
	H.GTID AS UGTID, S.Email, S.Name,
	T.School, T.Number,
	H.Time, H.Semester, H.Weekday
	FROM (Tutors T INNER JOIN Hires H ON T.School=H.School AND T.Number=H.Number)
	INNER JOIN TimeSlot TS ON TS.Time=H.Time AND TS.Semester=H.Semester AND TS.Weekday=H.Weekday AND TS.GTID=T.GTID
	INNER JOIN Student S ON H.GTID=S.GTID
);

CREATE VIEW TimeSlotComplete AS (
    SELECT * FROM TutorComplete TC NATURAL JOIN TimeSlot TS NATURAL JOIN Tutors T
);

CREATE VIEW AvailableTimeSlots AS (
	SELECT DISTINCT T.GTID, T.Name, T.Email, T.School, T.Number, T.Time, T.Semester, T.Weekday
		FROM TimeSlotComplete T LEFT OUTER JOIN Hires H
		ON T.School=H.School AND T.Number=H.Number
		AND T.Time=H.Time AND T.Semester=H.Semester AND T.Weekday=H.Weekday
		WHERE H.GTID IS NULL
);

CREATE VIEW TutorRatings AS (
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

*/
