-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dbo_hira
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dbo_hira
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbo_hira` DEFAULT CHARACTER SET utf8mb4 ;
USE `dbo_hira` ;

-- -----------------------------------------------------
-- Table `dbo_hira`.`tsg_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_hira`.`tsg_user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `no_username` VARCHAR(45) NOT NULL,
  `hira_id` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_hira`.`tax_locations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_hira`.`tax_locations` (
  `id_location` INT NOT NULL,
  `val_lat` VARCHAR(45) NOT NULL,
  `val_long` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_location`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_hira`.`tma_routes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_hira`.`tma_routes` (
  `id_route` INT NOT NULL AUTO_INCREMENT,
  `tag_enviroment` VARCHAR(45) NOT NULL,
  `val_noise` DECIMAL(3,2) NOT NULL,
  `val_airquality` DECIMAL(3,2) NOT NULL,
  `val_forestation` DECIMAL(3,2) NOT NULL,
  `pt_start` INT NOT NULL,
  `pt_end` INT NOT NULL,
  PRIMARY KEY (`id_route`),
  INDEX `fk_tma_routes_tax_locations_idx` (`pt_start` ASC) VISIBLE,
  INDEX `fk_tma_routes_tax_locations1_idx` (`pt_end` ASC) VISIBLE,
  CONSTRAINT `fk_tma_routes_tax_locations`
    FOREIGN KEY (`pt_start`)
    REFERENCES `dbo_hira`.`tax_locations` (`id_location`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tma_routes_tax_locations1`
    FOREIGN KEY (`pt_end`)
    REFERENCES `dbo_hira`.`tax_locations` (`id_location`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbo_hira`.`tmv_likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo_hira`.`tmv_likes` (
  `id_like` INT NOT NULL AUTO_INCREMENT,
  `fe_liked` DATE NOT NULL,
  `user_id` INT NOT NULL,
  `route_id` INT NOT NULL,
  PRIMARY KEY (`id_like`),
  INDEX `fk_tmv_likes_tsg_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_tmv_likes_tma_routes1_idx` (`route_id` ASC) VISIBLE,
  CONSTRAINT `fk_tmv_likes_tsg_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dbo_hira`.`tsg_user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tmv_likes_tma_routes1`
    FOREIGN KEY (`route_id`)
    REFERENCES `dbo_hira`.`tma_routes` (`id_route`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
