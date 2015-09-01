-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mutrack
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mutrack` ;

-- -----------------------------------------------------
-- Schema mutrack
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mutrack` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mutrack` ;

-- -----------------------------------------------------
-- Table `mutrack`.`tb_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mutrack`.`tb_user` ;

CREATE TABLE IF NOT EXISTS `mutrack`.`tb_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mutrack`.`tb_package`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mutrack`.`tb_package` ;

CREATE TABLE IF NOT EXISTS `mutrack`.`tb_package` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL,
  `code` VARCHAR(20) NOT NULL,
  `description` VARCHAR(150) NULL,
  `register_date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_package_tb_user_idx` (`owner_id` ASC),
  CONSTRAINT `fk_tb_package_tb_user`
    FOREIGN KEY (`owner_id`)
    REFERENCES `mutrack`.`tb_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mutrack`.`tb_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mutrack`.`tb_permission` ;

CREATE TABLE IF NOT EXISTS `mutrack`.`tb_permission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_UNIQUE` (`role` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mutrack`.`tb_user_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mutrack`.`tb_user_permission` ;

CREATE TABLE IF NOT EXISTS `mutrack`.`tb_user_permission` (
  `permission_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`permission_id`, `user_id`),
  INDEX `fk_tb_permission_has_tb_user_tb_user1_idx` (`user_id` ASC),
  INDEX `fk_tb_permission_has_tb_user_tb_permission1_idx` (`permission_id` ASC),
  CONSTRAINT `fk_tb_permission_has_tb_user_tb_permission1`
    FOREIGN KEY (`permission_id`)
    REFERENCES `mutrack`.`tb_permission` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_permission_has_tb_user_tb_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mutrack`.`tb_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mutrack`.`tb_permission`
-- -----------------------------------------------------
START TRANSACTION;
USE `mutrack`;
INSERT INTO `mutrack`.`tb_permission` (`id`, `role`) VALUES (1, 'admin');
INSERT INTO `mutrack`.`tb_permission` (`id`, `role`) VALUES (2, 'user');

COMMIT;

