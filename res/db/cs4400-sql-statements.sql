/*
=======================
=   SQL STATEMENTS    =
=======================
*/

/*
CREATE VIEW HiringTutorInfo AS (
	SELECT T.GTID AS TGTID,
	H.GTID AS UGTID, S.Email, S.Name,
	T.School, T.Number,
	H.Time, H.Semester, H.Weekday
	FROM (Tutors T INNER JOIN Hires H ON T.School=H.School AND T.Number=H.Number)
	INNER JOIN TimeSlot TS ON TS.Time=H.Time AND TS.Semester=H.Semester AND TS.Weekday=H.Weekday AND TS.GTID=T.GTID
	INNER JOIN Student S ON H.GTID=S.GTID
);
*/

/* RECOMMENDS SCREEN */
INSERT INTO Recommends VALUES ("900000003", "700000000", "This student was the best student I've ever had.", 1);
