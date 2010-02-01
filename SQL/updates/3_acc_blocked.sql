ALTER TABLE acc_contact
DROP COLUMN blocked;

CREATE TABLE `acc_blocked` (
  `uid` int(5) NOT NULL,
  `contact` int(5) NOT NULL,
  `blocked` int(1) NOT NULL,
  PRIMARY KEY (`uid`,`contact`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1