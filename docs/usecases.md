# Elunari Use Cases
## 1 - Register student
### 1.1 - Overview
* Actor: Registrar office
* Objective: Register the information from the new student at school system
* Bounded Context: Registration
* Description: The parents provide the student information to the registrar office from school who register this information into the school system
### 1.2 - Business Rules
* A student must have only one register
* A student must hava at least the informations of full name, date of birth, parents' full names, parents' phone numbers, parents' emails, gender, nationality, CPF,  RG, address
* A student can have other informations like phone numbers and emails
* A student must have an unique identifier to be used on the system
* A student must hava an information about if a register is active or not
### 1.3 - Use Case Flow
* Receive command `RegisterStudentCommand`
* Validate if the student already exists by the informations provided
* Create the aggregate `Student`
* Persist student
* Publish event `StudentRegisteredEvent`
* Return `StudentId`
### 1.4 - Technical Notes
* 
### 1.5 - Traceability
* 