
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rice` varchar(255) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `shop` VALUES (1, '大米', 10);