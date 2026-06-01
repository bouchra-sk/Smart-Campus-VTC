# Smart Campus VTC (University Carpooling System Backend)

An object-oriented Java backend application designed to manage and optimize university carpooling services at USTHB. The platform bridges the gap between drivers and passengers within the university community (Students, Professors, and Staff) by offering automated ride-matching, dynamic user reputation tracking, and administrative controls.

---

## ✨ Core Features

### 🚗 Ride Management & Optimization
* **Smart Matching Engine:** Implements a localized matching algorithm (`Match.java`) that evaluates spatial distance, identical travel routes, and overlapping preferences between drivers and passengers.
* **Route Geometry:** Models transit pathways with discrete pickup (`pointsRamassage`) and drop-off (`pointsDepot`) geographic coordinates.
* **Time-aware Planning:** Tracks active rides, daily schedules, and full historical archives via a dedicated `CourseManager`.

### 👥 User Roles & Access Control
* **Polymorphic Profiles:** Supports specialized schemas for Students (`Etudiant`), Faculty Members (`Enseignant`), and Administrative Staff (`ATS`) inheriting from a centralized base.
* **RBAC (Role-Based Access Control):** Separates core workflows for casual community members and verified administrative profiles (`Admin`).

### 🛡️ Safety & Reputation Guardianship
* **Automated Banning:** Computes weighted moving rating averages via a standalone evaluation sub-system. Users dropping below critical thresholds are restricted seamlessly.
* **Granular Statistics:** Enables admins to review active operational volumes, identify low-scoring profiles, and query top-performing drivers instantly.

---

## 🛠️ Architecture & Tech Stack
* **Language:** Java (JDK 17+)
* **Paradigm:** Pure Object-Oriented Programming (Polymorphism, Encapsulation, Inheritance)
* **Design Considerations:** Clean Architecture principles separating Data Modeling, Management Layers, and CLI Entry Points.