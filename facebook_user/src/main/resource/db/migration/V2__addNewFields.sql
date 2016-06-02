ALTER TABLE `roi_hunter`.`facebook_user_entity` ADD COLUMN `gender` VARCHAR(45) NULL  AFTER `name` , ADD COLUMN `image_url` VARCHAR(255) NULL  AFTER `gender` ;
ALTER TABLE `roi_hunter`.`facebook_page_entity` ADD COLUMN `description` BLOB NULL  AFTER `name` , ADD COLUMN `image_url` VARCHAR(255) NULL  AFTER `description` ;
