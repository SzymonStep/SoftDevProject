CREATE SCHEMA IF NOT EXISTS `SalesSystem` DEFAULT CHARACTER SET utf8;
USE `SalesSystem`;

CREATE TABLE Equipment (
	equipmentId int AUTO_INCREMENT PRIMARY KEY,
    equipmentName VARCHAR(50) NOT NULL,
    equipmentType VARCHAR(50) NOT NULL,
    equipmentSpecifications TEXT NOT NULL,
	quantityAvailable INT NOT NULL,
    equipmentPrice DECIMAL(10, 2) NOT NULL
);
 
CREATE TABLE Customer (
	customerId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(10) NOT NULL
);

CREATE TABLE Staff (
	staffId INT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    staffRole VARCHAR(10) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL
);

CREATE TABLE Orders (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    customerId INT NOT NULL,
    orderDate DATETIME NOT NULL,
    totalAmount DECIMAL(10, 2),
    orderStatus VARCHAR(50) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);

CREATE TABLE FaultyItems (
    faultId INT AUTO_INCREMENT PRIMARY KEY,
    equipmentId INT NOT NULL,
    batchNumber INT NOT NULL,
    faultDescription TEXT NOT NULL,
    reportedDate DATETIME NOT NULL,
    FOREIGN KEY (equipmentId) REFERENCES Equipment(equipmentId)
); 