## SecureVault - Secure File Sharing System

SecureVault is a secure file sharing web application developed using Spring Boot and MySQL. The application allows users to register, log in securely, upload files, 
and download them whenever required. Before storing any uploaded file, the system encrypts it using the AES (Advanced Encryption Standard) algorithm, ensuring that 
files remain protected even if the storage is compromised.

User passwords are securely stored using BCrypt hashing, providing strong protection against password theft.

## Technology Stack

- Maven
- VS Code
- MySQL Workbench
- Git
- GitHub

## Installation

1. Clone the repository
```bash
git clone https://github.com/Kamaleswari-M/SecureVault.git

cd SecureVault
```

2. Create a MySQL database:
   
CREATE DATABASE securefileshare;

3. Configure your database credentials in application.properties

4. Start the application:
```bash
mvn spring-boot:run
```

5.Open your browser:
http://localhost:8080


<img width="1536" height="768" alt="Screenshot 2026-06-26 162901" src="https://github.com/user-attachments/assets/76219a8e-37d4-4ae9-96eb-5d9a19545718" />
<img width="1532" height="769" alt="Screenshot 2026-06-26 163216" src="https://github.com/user-attachments/assets/107ca9b1-c005-4ccd-92bc-4b18ad8a11ce" />
<img width="1533" height="770" alt="Screenshot 2026-06-26 163506" src="https://github.com/user-attachments/assets/85d329b7-6f3a-4df5-9503-f20f47bbb317" />




