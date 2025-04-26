# 🗳️ Voterz App - Online Voting System

Voterz App is a simple and secure **online voting system** built with **Spring Boot** and **MySQL** (hosted on **Railway**).  
It allows registered users to cast their votes for predefined candidates while providing an admin panel to manage the election process.

### ✨ Key Features
- **User Authentication**: Admin login for managing the voting system.
- **Candidate Management**: Preloaded candidates in the database.
- **Voting Process**: 
  - Users can log in using their credentials.
  - Each user is allowed to vote **only once**.
  - The system tracks the voting status of each user.
- **Admin Panel**:
  - View total votes per candidate.
  - Monitor voting activity.
- **Secure Backend**:
  - Database connection is managed securely via environment variables (`.env` file).
  - Server deployed with dynamic port configuration for Railway hosting.

### 🛠️ Tech Stack
- **Backend**: Spring Boot
- **Database**: MySQL (Railway DB)
- **Frontend**: Thymeleaf Templates (Spring MVC)
- **ORM**: Spring Data JPA (Hibernate)
- **Deployment**: Railway

### ⚙️ How It Works
- On application start, the system automatically inserts:
  - One **Admin User** (`username: admin`, `password: admin` by default).
  - Four **candidates** to vote for.
- Users vote through a web interface.
- Admins can monitor votes and manage candidates easily.

