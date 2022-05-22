# kitchen-story

Kitchen Story is an e-commerce portal that lets people shop food items online. User can select any food item and order
it through a dummy payment gateway by logging into the site. Admin has the right to add and remove food-items, can view
users and their orders.

NOTE:

1. This application requires internet connection to add dishes. While adding dish you need to provide the url of the
   dish image, the application internally renders the url and convert the data to byte array and stores it into the
   database. Once the dish is stored into database, it doesn't require internet connection.

# Frameworks and Technologies used

01. Maven
02. Java 
03. Spring Boot 
04. Thymeleaf
05. Spring Security
06. Spring Data JPA
07. Spring Entity Validation
08. PostgreSQL
09. HTML
10. CSS
11. Javascript
12. jQuery
13. DataTables
14. Bootstrap CSS and JS
15. XML
16. properties and yaml
17. Git and Github
18. Agile Scrum Methodology

# Concepts and Algorithms used

1. Object-Oriented Programing - To Create objects and so on.
2. List, ArrayList, Map - To store and access data easily.
3. Streams - To access the data in the collection. It also makes the code easy to read, and it reduces no.of lines
4. Collections - To store and perform looping, searching, and sorting operations.

# Database and Structure

In the project root directory I've added a database.png image, in that you will find out the structure of the database.
Also, I've a provided database.sql file which contains the database, table creation and data that required to run the
program as well.

If you want to start from scratch without using the data in the database.sql file you can it, but you must create a
database and tables accordingly, then you can register yourself in the application by choosing the sign-up option. If
you sign-up using "prakashmaidupn7@gmail.com" you will get admin rights by default, if not you will be registered as regular
user with no special rights. I recommend registering using "prakashnaidupn7@gmail.com" email id with any data in other fields.

NOTE:

1. I'm using PostgreSQL database and, I've designed the database.sql file related to PostgreSQL database. Please make
   necessary changes if you are using different database.
2. Please make changes in application.yaml file in the root directory. It contains the database related configuration
   like driver, url, username and, password, please do make changes in that file as well as per user database.

# Website roadmap

1. /kitchen-story                        ___ @Home page
2. /kitchen-story/home                   ___ @Home page
3. /kitchen-story/login                  ___ @Login page
4. /kitchen-story/user/sign-up           ___ @Sign-UP page
5. /kitchen-story/user/forgot-password   ___ @Forgot Password page
6. /kitchen-story/user/all               ___ @To view user details
7. /kitchen-story/menu                   ___ @To menu of dishes
8. /kitchen-story/dish/{id}              ___ @To view particular dish
9. /kitchen-story/orders /all            ___ @To view list of all orders
10. /kitchen-story/cart/all              ___ @To view the cart
11. /kitchen-story/logout                ___ @To logout (It will be redirected to Home page after logout)

# Admin pages

12. /kitchen-story/dish/add              ___ @To add dishes
13. /kitchen-story/dish/edit/{id}        ___ @To edit particular dish
14. /kitchen-story/dish/delete/{id}      ___ @To delete particular dish
15. /kitchen-story/dish/all              ___ @To view list of all dishes
16. /kitchen-story/order/all             ___ @To view orders of all users
17. /kitchen-story/order/{id}            ___ @To view particular order details
18. /kitchen-story/user/all              ___ @To view list of all users
19. /kitchen-story/user/{id}             ___ @To view particular user
20. /kitchen-story/user/edit/{id}        ___ @To edit particular user details

# Project Flow

Step - 1.0 : A welcome page will be shown to User, where user find all the possible options to choose.

Step - 1.1 : User will only be allowed to view Home page and Login, SignUp, and Forgot password pages if user is not
logged in.

Step - 1.2 : To view any page except home and login, SignUp, and Forgot password pages user must log in.

Step - 1.3 : Based on the user role application will provide additional option if the user has admin rights.

Step - 1.4 : If you are not existing user, there is an option to register yourself. By default, you will get user
rights.

Step - 2.0 : To log out user can click on logout button.

Step - 3.0 : Admin will get an option to view all the registered user.

Step - 3.1 : Admin has the rights to view the user details.

Step - 3.2 : Admin has the rights to update the user details.

Step - 3.3 : Admin has the rights to add dishes.

Step - 3.4 : Admin has the rights update the dish details.

Step - 3.5 : Admin has the rights to view all the dishes available.

Step - 3.6 : Admin has the rights to view all the order list.

Step - 3.7 : Admin has the rights to view all the order details.

Step - 4.0 : Based on the links provided you can choose to view dishes.

Step - 4.1 : You can choose any dish and add it to cart.

Step - 4.2 : You can check the dishes you have added to your cart.

Step - 4.3 : You can proceed to pay for the dishes in you cart.

Step - 4.4 : Once payment is done application will show the order details.

Step - 4.5 : You can always check the dishes you have ordered.

Step - 5.0 : There is an option to change password. User must be logged in to user this option.

Step - 5.1 : There is an option to change password. This option is available even if user is not logged in, but user
must provide the email id to change the password.
