[
"CREATE DATABASE IF NOT EXISTS `clojure`;"

"USE `clojure`;"

"DROP TABLE IF EXISTS `candidates`;"

"CREATE TABLE `clojure`.`candidates` (
  `id` INT AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `age` INT NOT NULL,
  `first_aid_certificate` TINYINT NOT NULL,
  `theoretical_classes_listened` INT NOT NULL,
  `theoretical_exam_passed` TINYINT NOT NULL,
  `driving_classes_listened` INT NOT NULL,
  `driving_exam_passed` TINYINT NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;"

"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('1', 'Milica Bacic', '24', '1', '30', '0', '0', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('2', 'Pera Peric', '27', '0', '40', '0', '0', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('3', 'Laza Lazic', '22', '1', '40', '1', '16', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('4', 'Mika Mikic', '18', '1', '40', '1', '40', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('5', 'Sofija Sofic', '20', '1', '40', '1', '40', '1');"
]