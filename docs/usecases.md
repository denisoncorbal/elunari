# Elunari Use Cases

## 1 - Register student

### 1.1 - Overview

- Actor: Registrar office
- Objective: Register the information from the new student at school system
- Bounded Context: Registration
- Description: The parents provide the student information to the registrar office from school who register this information into the school system

### 1.2 - Business Rules

- A student must have only one register
- A student must hava at least the informations of full name, date of birth, parents' full names, parents' phone numbers, parents' emails, gender, nationality, CPF, RG, address
- A student can have other informations like phone numbers and emails
- A student must have an unique identifier to be used on the system
- A student must hava an information about if a register is active or not

### 1.3 - Use Case Flow

- Receive command `RegisterStudentCommand`
- Validate if the student already exists by the informations provided
- Create the entity `Student`
- Persist student
- Publish event `StudentRegisteredEvent`
- Return `StudentId`

### 1.4 - Technical Notes

-

### 1.5 - Traceability

-

## 2 - Register teacher

### 2.1 - Overview

- Actor: Registrar office
- Objective: Register the information from the new teacher at school system
- Bounded Context: Registration
- Description: The teacher provide his information to the registrar office from school after his admission who register this information into the school system

### 2.2 - Business Rules

- A teacher must have only one register
- A teacher must hava at least the informations of full name, date of birth, gender, nationality, CPF, address, phone numbers and emails
- A teacher must have an unique identifier to be used on the system
- A teacher must hava an information about if a register is active or not

### 2.3 - Use Case Flow

- Receive command `RegisterTeacherCommand`
- Validate if the teacher already exists by the informations provided
- Create the entity `Teacher`
- Persist teacher
- Publish event `TeacherRegisteredEvent`
- Return `TeacherId`

### 2.4 - Technical Notes

-

### 2.5 - Traceability

-

## 3 - Register subject

### 3.1 - Overview

- Actor: Registrar office
- Objective: Register the creation of a subject to be used on a system
- Bounded Context: Academic
- Description: The school direction define the subjects that will be used on school and then request the registrar office to create them on system

### 3.2 - Business Rules

- A subject must have only one register
- A subject must hava at least the information of name
- The subject name must be unique
- A subject must have an unique identifier to be used on the system
- A subject must hava an information about if a register is active or not

### 3.3 - Use Case Flow

- Receive command `RegisterSubjectCommand`
- Validate if the subject already exists by the informations provided
- Validate if the name is already in use
- Create the entity `Subject`
- Persist subject
- Publish event `SubjectRegisteredEvent`
- Return `SubjectId`

### 3.4 - Technical Notes

-

### 3.5 - Traceability

-

## 4 - Register school class

### 4.1 - Overview

- Actor: Registrar office
- Objective: Register the creation of a school class to be used on a system
- Bounded Context: Academic
- Description: The school direction define the school classes that will be used on school and then request the registrar office to create them on system

### 4.2 - Business Rules

- A school class must have only one register
- A school class must hava at least the information of name, grade and reference year
- The school class name must be unique
- A school class must have an unique identifier to be used on the system
- A school class must hava an information about if a register is active or not

### 4.3 - Use Case Flow

- Receive command `RegisterSchoolClassCommand`
- Validate if the school class already exists by the informations provided
- Validate if the name is already in use
- Create the entity `SchoolClass`
- Persist school class
- Publish event `SchoolClassRegisteredEvent`
- Return `SchoolClassId`

### 4.4 - Technical Notes

-

### 4.5 - Traceability

-

## 5 - Allocate teacher

### 5.1 - Overview

- Actor: Registrar office
- Objective: Allocate a teacher to teach a subject on a school class
- Bounded Context: Academic
- Description: The school direction define who teacher will be teaching which subject on which school class. Then they request the registrar office to allocate them on system

### 5.2 - Business Rules

- An allocation must have only one register
- An allocation must hava at least the information of teacher unique identifier, subject unique identifier, school class unique identifier, start time and end time
- Two allocations for the same teacher cannot have conflit on the start time and end time
- An allocation must have an unique identifier to be used on the system
- An allocation must hava an information about if it is active or not

### 5.3 - Use Case Flow

- Receive command `AllocateTeacherCommand`
- Validate if there is not any conflit on start and end time for the teacher
- Create the aggregate `Allocation`
- Persist allocation
- Publish event `TeacherAllocatedEvent`
- Return `AllocationId`

### 5.4 - Technical Notes

-

### 5.5 - Traceability

-

## 6 - Enroll student

### 6.1 - Overview

- Actor: Registrar office
- Objective: Enroll a student to study the reference year on a defined school class
- Bounded Context: Academic
- Description: The school direction define which school class the student will be taking for the reference year and request the registrar office to enroll the student into the school class

### 6.2 - Business Rules

- An enrollment must have only one register
- An enrollment must hava at least the information of student unique identifier, school class unique identifier, start time and end time
- Each student can have only one active enrollment
- An enrollment must have an unique identifier to be used on the system
- An enrollment must hava an information about if it is active or not

### 6.3 - Use Case Flow

- Receive command `EnrollStudentCommand`
- Validate if there is not another active enrollment for the same student
- Create the aggregate `Enrollment`
- Persist enrollment
- Publish event `StudentEnrolledEvent`
- Return `EnrollmentId`

### 6.4 - Technical Notes

-

### 6.5 - Traceability

-

## 7 - Enter english language development record

### 7.1 - Overview

- Actor: Teacher
- Objective: School class teacher need to record information about english language development about his students
- Bounded Context: Academic
- Description: Teacher observes his students and then enter in the system information about his english language development

### 7.2 - Business Rules

- An english language development record must have only one register
- An english language development record must hava at least the information of teacher unique identifier, student unique identifier, school class unique identifier, data to be recorded
- An english language development record must have an unique identifier to be used on the system
- An english language development record must hava an information about if it is active or not

### 7.3 - Use Case Flow

- Receive command `EnterEnglishLanguageDevelopmentRecordCommand`
- Create the aggregate `EnglishLanguageDevelopmentRecord`
- Persist english language development record
- Publish event `EnglishLanguageDevelopmentRecordEnteredEvent`
- Return `EnglishLanguageDevelopmentRecordId`

### 7.4 - Technical Notes

-

### 7.5 - Traceability

-

## 8 - Enter response to intervention record

### 8.1 - Overview

- Actor: Teacher
- Objective: School class teacher need to record information about response to intervention about his students
- Bounded Context: Academic
- Description: Teacher observes his students and then enter in the system information about his response to intervention

### 8.2 - Business Rules

- An response to intervention record must have only one register
- An response to intervention record must hava at least the information of teacher unique identifier, student unique identifier, school class unique identifier, data to be recorded
- An response to intervention record must have an unique identifier to be used on the system
- An response to intervention record must hava an information about if it is active or not

### 8.3 - Use Case Flow

- Receive command `EnterResponseToInterventionRecordCommand`
- Create the aggregate `ResponseToInterventionRecord`
- Persist response to intervention record
- Publish event `ResponseToInterventionRecordEnteredEvent`
- Return `ResponseToInterventionRecordId`

### 8.4 - Technical Notes

-

### 8.5 - Traceability

-
