-- phpMyAdmin SQL Dump
-- version 2.11.5
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Mar 20, 2023 at 08:06 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `bibliotheque`
--

-- --------------------------------------------------------

--
-- Table structure for table `livre`
--

CREATE TABLE `livre` (
  `idLiv` int(11) NOT NULL,
  `titre` varchar(40) character set utf8 default NULL,
  `genre` varchar(40) character set utf8 default NULL,
  `auteur` varchar(50) character set utf8 default NULL,
  `quantite` int(11) default NULL,
  PRIMARY KEY  (`idLiv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `livre`
--

INSERT INTO `livre` (`idLiv`, `titre`, `genre`, `auteur`, `quantite`) VALUES
(1, 'BootStrap', 'Design', 'EL Hiaine', 30),
(2, 'JAVA', 'Programmation', 'Bouaabid', 23),
(3, 'UML', 'conception', 'elhiane', 21),
(4, 'BootStrap', 'Design', 'EL Hiaine', 20);

-- --------------------------------------------------------

--
-- Table structure for table `locataire`
--

CREATE TABLE `locataire` (
  `idLoc` int(11) NOT NULL,
  `nomprenom` varchar(60) character set utf8 default NULL,
  `niveau` varchar(20) character set utf8 default NULL,
  `filiere` varchar(30) character set utf8 default NULL,
  `idLiv` int(11) default NULL,
  PRIMARY KEY  (`idLoc`),
  KEY `idLiv` (`idLiv`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `locataire`
--

INSERT INTO `locataire` (`idLoc`, `nomprenom`, `niveau`, `filiere`, `idLiv`) VALUES
(1, 'Zackaria jamal Eddine', '2ème année', 'PME/PMI', 4),
(2, 'Adam jihad', '2ème année', 'MI', 4);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `locataire`
--
ALTER TABLE `locataire`
  ADD CONSTRAINT `locataire_ibfk_1` FOREIGN KEY (`idLiv`) REFERENCES `livre` (`idLiv`) ON DELETE CASCADE ON UPDATE CASCADE;
