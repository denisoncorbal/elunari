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
- Create the aggregate `Student`
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
- Create the aggregate `Teacher`
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
- Description: The school direction define the subjects that will be used on school and then requet the registrar office to create them on system

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
- Create the aggregate `Subject`
- Persist subject
- Publish event `SubjectRegisteredEvent`
- Return `SubjectId`

### 3.4 - Technical Notes

-

### 3.5 - Traceability

-
