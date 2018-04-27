-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 08, 2018 at 10:20 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `towmagic`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `InsertAddress`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAddress` (IN `LineOne` VARCHAR(50), IN `LineTwo` VARCHAR(50), IN `City` VARCHAR(30), IN `State` VARCHAR(2), IN `ZipCode` VARCHAR(5), OUT `InsID` INT)  BEGIN
    	INSERT INTO towmagic.address (AddressLineOne,AddressLineTwo,City,State,ZipCode)
        VALUES (LineOne, LineTwo, City, State, ZipCode);
        SET insID = LAST_INSERT_ID();
     END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `AddressLineOne` varchar(50) NOT NULL,
  `AddressLineTwo` varchar(50) DEFAULT NULL,
  `City` varchar(30) NOT NULL,
  `State` varchar(2) NOT NULL,
  `ZipCode` varchar(5) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`ID`, `AddressLineOne`, `AddressLineTwo`, `City`, `State`, `ZipCode`) VALUES
(1, '987 Fifth St', 'Apartment Two', 'Cincinanti', 'OH', '45219'),
(3, '3651 Brotherton Rd', '#3', 'Cincinanti', 'OH', '45209'),
(2, '3712 Rose Ct.', NULL, 'Cincinanti', 'OH', '45039'),
(4, '4321 Example Place', NULL, 'Cincinanti', 'OH', '45039'),
(5, '123 Test St', '#2', 'Test City', 'OH', '45209'),
(6, 'Test Line One', 'Test Line Two', 'Test City', 'OH', '45219');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Phone` varchar(10) NOT NULL,
  `AddressID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`),
  KEY `AddressID` (`AddressID`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`ID`, `FirstName`, `LastName`, `Phone`, `AddressID`) VALUES
(1, 'John', 'Smith', '123-456-78', 1),
(2, 'Jane', 'Doe', '098-765-43', 2),
(3, 'Blake', 'Maislin', '444-444-44', 2),
(4, 'Emily', 'Someone', '111-222-33', 4),
(5, 'Test First', 'Test Last', '1234567890', 6);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `Password` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`ID`, `FirstName`, `LastName`, `UserName`, `Password`) VALUES
(1, 'First', 'Trucker', 'first.trucker', 'trucker1'),
(2, 'Second', 'Trucker', 'second.trucker', 'trucker2'),
(3, 'Third', 'Trucker', 'third.trucker', 'trucker3'),
(4, 'Andrew', 'Holzman', 'holzmaad', '$2a$10$eF4Ryokhn.qkBhLX3DGxhexh7842wSfsNJ7Nhr6RmmunMxc8aChnm');

-- --------------------------------------------------------

--
-- Table structure for table `employee-manager`
--

DROP TABLE IF EXISTS `employee-manager`;
CREATE TABLE IF NOT EXISTS `employee-manager` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeID` int(11) NOT NULL,
  `ManagerID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`),
  KEY `EmployeeID` (`EmployeeID`),
  KEY `ManagerID` (`EmployeeID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee-manager`
--

INSERT INTO `employee-manager` (`ID`, `EmployeeID`, `ManagerID`) VALUES
(1, 1, 3),
(2, 2, 3),
(3, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tow`
--

DROP TABLE IF EXISTS `tow`;
CREATE TABLE IF NOT EXISTS `tow` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(11) NOT NULL,
  `DriverID` int(11) NOT NULL,
  `VehicleID` int(11) NOT NULL,
  `InitialTimeEstimate` datetime NOT NULL,
  `Status` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `DriverID` (`DriverID`),
  KEY `VehicleID` (`VehicleID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tow`
--

INSERT INTO `tow` (`ID`, `CustomerID`, `DriverID`, `VehicleID`, `InitialTimeEstimate`, `Status`) VALUES
(1, 1, 1, 1, '2018-03-19 18:33:37', 'Pending'),
(2, 2, 3, 2, '2018-03-19 18:33:37', 'Complete'),
(3, 3, 3, 1, '2018-03-19 18:33:37', 'In Progress');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE IF NOT EXISTS `vehicle` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Make` varchar(50) NOT NULL,
  `Model` varchar(50) NOT NULL,
  `Color` varchar(20) NOT NULL,
  `Plate` varchar(20) NOT NULL,
  `Year` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`ID`, `Make`, `Model`, `Color`, `Plate`, `Year`) VALUES
(1, 'Ford', 'F450', 'White', 'ABC1234', '2004'),
(2, 'Ford', 'F450', 'White', 'BCA4321', '2008'),
(3, 'Ford', 'F450', 'Red', 'EAD9987', '2012');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
