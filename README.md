# 🗳️ Voterz App - Online Voting System

![User Dashboard](/p1.png)
Voterz App is a simple and secure **online voting system** built with **Spring Boot** and **MySQL**.  
It allows registered users to cast their votes for predefined candidates while providing an admin panel to manage the election process.

### ✨ Key Features
- **User Authentication**: Admin login for managing the voting system.
![User Dashboard](/p2.png)
![User Dashboard](/p3.png)
- **Candidate Management**: Preloaded candidates in the database.
- **Voting Process**: 
  - Users can log in using their credentials.
  - Each user is allowed to vote **only once**.
  - The system tracks the voting status of each user.
![User Dashboard](/p4.png)
![User Dashboard](/p5.png)
![User Dashboard](/p6.png)

- **Admin Panel**:
  - View total votes per candidate.
  - Monitor voting activity.
![User Dashboard](/p7.png)
![User Dashboard](/p8.png)
- **Secure Backend**:
  - Database connection is managed securely.

### 🛠️ Tech Stack
- **Backend**: Spring Boot
- **Database**: MySQL 
- **Frontend**: Thymeleaf Templates (Spring MVC)
- **ORM**: Spring Data JPA (Hibernate)

### ⚙️ How It Works
- On application start, the system automatically inserts:
  - One **Admin User** (`username: admin`, `password: admin` by default).
  - Four **candidates** to vote for.
- Users vote through a web interface.
- Admins can monitor votes and manage candidates easily.

