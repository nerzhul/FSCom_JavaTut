-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Ven 22 Janvier 2010 à 15:04
-- Version du serveur: 5.1.36
-- Version de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de données: `messenger`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `uid` int(5) NOT NULL AUTO_INCREMENT,
  `user` varchar(20) NOT NULL,
  `sha_pass` int(40) NOT NULL,
  `pseudo` varchar(50) NOT NULL,
  `phr_perso` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user` (`user`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `account`
--


-- --------------------------------------------------------

--
-- Structure de la table `acc_contact`
--

DROP TABLE IF EXISTS `acc_contact`;
CREATE TABLE IF NOT EXISTS `acc_contact` (
  `uid` int(5) NOT NULL,
  `contact` int(5) NOT NULL,
  `blocked` int(1) NOT NULL,
  `comment` varchar(50) NOT NULL,
  `group` int(2) NOT NULL,
  `accepted` int(1) NOT NULL,
  PRIMARY KEY (`uid`,`contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `acc_contact`
--


-- --------------------------------------------------------

--
-- Structure de la table `acc_group`
--

DROP TABLE IF EXISTS `acc_group`;
CREATE TABLE IF NOT EXISTS `acc_group` (
  `uid` int(5) NOT NULL,
  `gid` int(2) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`uid`,`gid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `acc_group`
--

CREATE TABLE `acc_invitation` (
  `uid` int(5) NOT NULL,
  `contact` int(5) NOT NULL,
  PRIMARY KEY (`uid`,`contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1

