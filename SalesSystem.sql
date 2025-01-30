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

CREATE TABLE EquipmentCatalogue (
    catalogueId INT AUTO_INCREMENT PRIMARY KEY,
    equipmentId INT NOT NULL,
    FOREIGN KEY (equipmentId) REFERENCES Equipment(equipmentId)
);

CREATE TABLE Customer (
	customerId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    phoneNumber VARCHAR(10) NOT NULL
);


CREATE TABLE ServiceStaff (
	staffId INT AUTO_INCREMENT PRIMARY KEY,
	firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
)


CREATE TABLE Orders (
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    customerId INT NOT NULL,
    orderDate DATETIME NOT NULL,
    totalAmount DECIMAL(10, 2),
    orderStatus VARCHAR(50) NOT NULL,
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);

CREATE TABLE OrderReturns (
    returnId INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT NOT NULL,
    equipmentId INT NOT NULL,
    reason TEXT NOT NULL,
    orderReturnStatus VARCHAR(50) NOT NULL,
    replacementRequested BOOLEAN;
    FOREIGN KEY (orderId) REFERENCES Orders(orderId),
    FOREIGN KEY (equipmentId) REFERENCES Equipment(equipmentId)
);

CREATE TABLE CustomerFeedback (
    feedbackId INT AUTO_INCREMENT,
    customerId INT NOT NULL,
    orderId INT,
    comments TEXT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId),
    FOREIGN KEY (orderId) REFERENCES Orders(ordersId)
);

CREATE TABLE Delivery (
    deliveryId INT AUTO_INCREMENT PRIMARY KEY,
    orderId INT NOT NULL,
    estimatedDeliveryTime DATETIME NOT NULL:
    trackingStatus VARCHAR(50) NOT NULL,
    FOREIGN KEY (orderId) REFERENCES Orders(orderId)
);

CREATE TABLE Reports (
    reportId INT AUTO_INCREMENT PRIMARY KEY,
    reportType VARCHAR(50) NOT NULL,
    reportData TEXT NOT NULL,
    generateDate DATETIME NOT NULL,
);

CREATE TABLE FaultyItems (
    faultId INT AUTO_INCREMENT PRIMARY KEY,
    equipmentId INT NOT NULL,
    batchNumber VARCHAR(50) NOT NULL,
    faultDescription TEXT NOT NUll,
    reportedDate DATETIME NOT NULL<
    FOREIGN KEY (equipmentId) REFERENCES Equipment(equipmentId)
);

	