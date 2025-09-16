# Usage Guide for CCRM

## Sample Commands

### Add Student
```
Choice: 1
1-Add 2-List
1
RegNo: R1001
Name: Alice Kumar
Email: alice@example.com
```

### Add Course
```
Choice: 2
Action: add
Course code: CS-101
Title: Database Systems
Credits: 4
Instructor: Dr. Navathe
Dept: CSE
```

### Enroll Student
```
Choice: 3
StudentId: 1000
Course code: CS-101
```

### Export
```
Choice: 4
Action: 2
Export completed → see output/students.json, courses.json
```

## Demo Mode
```
Choice: 9
```
Runs scripted commands from `data/demo-input.txt`.

---

## Data Files
### test-data/students.csv
```
regNo,name,email
R2001,Raj Patel,raj@example.com
R2002,Neha Gupta,neha@example.com
```

### test-data/courses.csv
```
code,title,credits,instructor,department
CS-301,Machine Learning,4,Prof. Andrew Ng,CSE
CS-302,Cloud Computing,3,Prof. Rajkumar Buyya,CSE
```