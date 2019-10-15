-- auto Generated on 2019-10-15 09:58:16 
-- DROP TABLE IF EXISTS `person`; 
CREATE TABLE `person`(
    `person_id` VARCHAR (50) NOT NULL COMMENT 'personId',
    `person_name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'personName',
    `person_age` INT (11) NOT NULL DEFAULT -1 COMMENT 'personAge',
    `person_birthday` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'personBirthday',
    `remark` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'remark',
    PRIMARY KEY (`person_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`person`';
