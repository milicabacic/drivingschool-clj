[
"CREATE DATABASE IF NOT EXISTS `clojure`;"

"USE `clojure`;"

"DROP TABLE IF EXISTS `candidates`;"
"DROP TABLE IF EXISTS `driving_exam`;"
"DROP TABLE IF EXISTS `candidate_driving_exam`;"
"DROP TABLE IF EXISTS `theory_exam`;"
"DROP TABLE IF EXISTS `candidate_theory_exam`;"



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

"CREATE TABLE `clojure`.`driving_exam` (
   `id` INT NOT NULL,
   `date` DATE NOT NULL,
   `policeman` VARCHAR(80) NOT NULL,
   PRIMARY KEY (`id`, `date`))
 ENGINE = InnoDB
 DEFAULT CHARACTER SET = utf8;
"

"CREATE TABLE `clojure`.`candidate_driving_exam` (
   `candidate_id` INT NOT NULL,
   `driving_exam_id` INT NOT NULL,
   `driving_exam_date` DATE NOT NULL,
   `points` INT NOT NULL,
   PRIMARY KEY (`candidate_id`, `driving_exam_id`, `driving_exam_date`),
   INDEX `fk_driving_exam_idx` (`driving_exam_id` ASC, `driving_exam_date` ASC) VISIBLE,
   CONSTRAINT `fk_candidate`
     FOREIGN KEY (`candidate_id`)
     REFERENCES `clojure`.`candidates` (`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
   CONSTRAINT `fk_driving_exam`
     FOREIGN KEY (`driving_exam_id` , `driving_exam_date`)
     REFERENCES `clojure`.`driving_exam` (`id` , `date`)
     ON DELETE CASCADE
     ON UPDATE CASCADE);
"

"CREATE TABLE `clojure`.`theory_exam` (
   `id` INT NOT NULL,
   `date` DATE NOT NULL,
   `policeman` VARCHAR(60) NOT NULL,
   PRIMARY KEY (`id`, `date`))
 ENGINE = InnoDB
 DEFAULT CHARACTER SET = utf8;
"

"CREATE TABLE `clojure`.`candidate_theory_exam` (
   `candidate_id` INT NOT NULL,
   `theory_exam_id` INT NOT NULL,
   `theory_exam_date` DATE NOT NULL,
   `points` INT NOT NULL,
   PRIMARY KEY (`candidate_id`, `theory_exam_id`, `theory_exam_date`),
   INDEX `fk_theory_exam_idx` (`theory_exam_id` ASC, `theory_exam_date` ASC) VISIBLE,
   CONSTRAINT `fk_candidate`
     FOREIGN KEY (`candidate_id`)
     REFERENCES `clojure`.`candidates` (`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
   CONSTRAINT `fk_theory_exam`
     FOREIGN KEY (`theory_exam_id` , `theory_exam_date`)
     REFERENCES `clojure`.`theory_exam` (`id` , `date`)
     ON DELETE CASCADE
     ON UPDATE CASCADE);
"

"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('1', 'Milica Bacic', '24', '1', '30', '0', '0', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('2', 'Pera Peric', '27', '0', '40', '0', '0', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('3', 'Laza Lazic', '22', '1', '40', '1', '16', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('4', 'Mika Mikic', '18', '1', '40', '1', '40', '0');"
"INSERT INTO `clojure`.`candidates` (`id`, `name`, `age`, `first_aid_certificate`, `theoretical_classes_listened`, `theoretical_exam_passed`, `driving_classes_listened`, `driving_exam_passed`) VALUES ('5', 'Sofija Sofic', '20', '1', '40', '1', '40', '1');"
]