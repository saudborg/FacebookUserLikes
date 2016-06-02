
CREATE TABLE IF NOT EXISTS `facebook_page_entity` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `facebook_user_entity` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `facebook_user_entity_pages` (
  `facebook_user_entity_id` varchar(255) NOT NULL,
  `pages_id` varchar(255) NOT NULL,
  UNIQUE KEY `UK_hnk26x9cr3hlins14ij62vq6e` (`pages_id`),
  KEY `FK_iqdj37pp400i7lw6t5f0ohi7e` (`facebook_user_entity_id`),
  CONSTRAINT `FK_iqdj37pp400i7lw6t5f0ohi7e` FOREIGN KEY (`facebook_user_entity_id`) REFERENCES `facebook_user_entity` (`id`),
  CONSTRAINT `FK_hnk26x9cr3hlins14ij62vq6e` FOREIGN KEY (`pages_id`) REFERENCES `facebook_page_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;