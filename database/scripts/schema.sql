USE jegy;

-- Allow access from any host
CREATE USER `jegy`@`localhost` IDENTIFIED BY 'changeit';
CREATE USER `jegy`@`%` IDENTIFIED BY 'changeit';
GRANT ALL PRIVILEGES ON *.* TO `jegy`@`localhost` WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO `jegy`@`%` WITH GRANT OPTION;
FLUSH PRIVILEGES;

--
-- Table structure for table `ticket`
--

CREATE TABLE `ticket` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `summary` varchar(256) NOT NULL,
  `body` varchar(2048) NOT NULL,
  `requester_id` int(12) NOT NULL,
  `assignee_id` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(256) NOT NULL,
  `last_name` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
);
