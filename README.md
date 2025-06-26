# 🔐 Secure Java JDBC Project — From Vulnerable to Hardened

##      <<< Quick Comprehension >>>

This project is a **step-by-step security enhancement of a Java-based application** that interacts with a MySQL database containing sensitive data. It starts with an intentionally vulnerable implementation and evolves through multiple stages, applying secure coding principles and best practices. Through a series of progressive stages (4–5 parts, maybe more), I apply security best practices including input sanitization, encryption, authentication, and access control. The goal is to highlight common vulnerabilities and showcase how to properly secure a software system handling sensitive information. Security is life. 

-------------------------------------------------------------------------

##      <<< Project Goals >>>

- Demonstrate the risks of insecure database access
- Show how to secure data through Java code
- Apply encryption, access control, and authentication
- Build a strong foundation for secure software development

-------------------------------------------------------------------------

##      <<< Project Structure >>>

The-Nevermmore/

├── .gitignore

├── LICENSE

├── README.md

├── config.example.properties <<<< Example config file (no secrets)

├── config.properties <<<< Actual config (NOT committed)

├── src/
│ ├── Main.java

│ ├── db/
│ │ ├── DBConnection.java
│ │ └── SensitiveDAO.java

│ ├── security/
│ │ └── AESUtil.java

│ └── AccessControl/
│ └── ManagerOfAccess.java

-------------------------------------------------------------------------

##      <<< Project Phases >>>

| Phase | Description                                 |
|-------|---------------------------------------------|
| 1️⃣    | Vulnerable base implementation              |
| 2️⃣    | Input validation and SQL injection defense |
| 3️⃣    | AES encryption for sensitive columns       |
| 4️⃣    | Password hashing with bcrypt               |
| 5️⃣    | Access control and authentication logic    |

-------------------------------------------------------------------------

##      <<< Technologies Used >>>

- Java (JDK 17+)
- JDBC (MySQL)
- MySQL Database
- jBCrypt for password hashing
- AES (Javax.crypto) for encryption
- VS Code with SQLTools extension

##      <<< Technologies Used >>>

- Java (JDK 17+)
- JDBC (MySQL)
- MySQL Database
- jBCrypt for password hashing
- AES (Javax.crypto) for encryption
- VS Code with SQLTools extension



## <<<<<<<<<<<<<<<<<<<<<<<<<<<<<< Getting Started >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

1. **Clone the repository**
   
   #    <<< In terminal... >>>
   git clone https://github.com/Progloading/The-Nevermore.git
   cd The-Nevermmore

2. **Rename and Configure your DB connection**

    #   <<< In terminal... >>>
    cp config.exxample.properties config.properties

    - Fill in your specific DB credentials and connection info inside config.properties

3. **Compile and Run**

    # <<< In terminal... >>>
    javax -d bin src/**/*.java
    java -cp bin Main