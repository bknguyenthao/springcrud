CREATE TABLE `customer` (
 `id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(50) NOT NULL,
 `address` varchar(150) NULL,
 `email` varchar(50) NULL,
 `phone` varchar(20) NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8