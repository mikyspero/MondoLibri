# MondoLIbri-Generation-Italy
E-commerce Application: Final Project for Generation Italy Java Developer Course
Project Overview
This project is a monolithic Jakarta EE application that demonstrates our understanding of core Java web development concepts, including CRUD operations, session-based authentication, and the basics of the Model-View-Controller (MVC) architecture. The application is an e-commerce platform that allows users to browse products, add them to a cart, and complete purchases.
Key Features

User Authentication and Authorization

User registration and login functionality
Session-based authentication to secure access to user-specific features
Role-based access control for customer and admin functionalities


Product Management (CRUD Operations)

Display a list of products with details (Read)
Add new products to the catalog (Create) - Admin only
Update existing product information (Update) - Admin only
Remove products from the catalog (Delete) - Admin only


Shopping Cart Functionality

Add products to the cart
View and modify cart contents
Calculate total price


Order Processing

Create orders from cart contents
View order history
Update order status (Admin only)


User Profile Management

View and update user information
Change password functionality



Technical Implementation

Frontend: JSP (JavaServer Pages) with JSTL for dynamic content rendering
Backend: Jakarta EE (Servlets, EJB)
Database: MySQL with JPA (Java Persistence API) for ORM
Authentication: Custom session-based authentication using Jakarta Security
Dependency Management: Maven
Server: Apache Tomcat

MVC Architecture

Model: Java classes representing entities (User, Product, Order, etc.)
View: JSP pages for rendering the user interface
Controller: Servlets handling HTTP requests and responses

Thanks
I wish to thank my team members that worked with me on the version before the fork
Conclusion
This project showcases our ability to build a functional e-commerce application using Jakarta EE technologies. It demonstrates our understanding of full-stack Java development, including frontend and backend integration, database management, and implementation of security features.
We've applied best practices in software development, such as separation of concerns through the MVC pattern, and implemented core functionalities essential to e-commerce applications. This project serves as a comprehensive demonstration of the skills acquired during the Generation Italy Java developer intensive course.
Inserimento di un nuovo ordine di acquisto 
Recupero degli ordini di acquisto di un utente
Recupero di tutti gli ordini di acquisto (solo per amministratori) 
Recupero di statistiche sugli acquisti per ogni prodotto (solo per amministratori)
