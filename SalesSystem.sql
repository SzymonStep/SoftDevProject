CREATE TABLE Equipment (
	equipmentId int AUTO_INCREMENT PRIMARY KEY,
    equipmentType VARCHAR(50) NOT NULL,
    equipmentSpecifications VARCHAR(50) NOT NULL,
	quantityAvailable INT
);

CREATE TABLE Customer (
	customerId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(10) NOT NULL
);


CREATE TABLE ServiceStaff (
	staffId INT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,



CREATE TABLE orderReturns (

	