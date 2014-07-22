CS4400 Project - GTTutors
================

Project for CS4400 (Introduction to Databases)

Contains the following features:
- A login with menu screen customized based on user type
- Students
  - Can search and schedule for tutors
  - Can rate a tutor that they've had before during that semester
  - Can apply to become a tutor
- Tutors
  - Can complete any task that a student can complete
  - Can look up their schedule
- Professors
  - Can recommend any student to become a tutor
- System Administrators
  - Can look at summaries of tutor statistics grouped by course and semester

Deviations from Project Setup
- Found it better to merge the Search and Schedule screens to 1 screen, out of coding convenience.
- In order for a student to apply to become a tutor, they must first have a professor recommend them. In the relational table Recommends, professors can recommend any student, instead of only tutors.
- Since students need to apply to become a tutor, the Apply screen now appears for all students.
- Because all tutors are students, all screens related to students also appear with tutors.