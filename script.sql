CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(80) NOT NULL,
  `name` varchar(80) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `user` ADD UNIQUE INDEX `uk-user_email` (`email` ASC) VISIBLE;

CREATE TABLE `account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(36) NOT NULL,
  `type` enum('CURRENT','SAVINGS') NOT NULL,
  `balance` decimal(11,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk-account_user_id-user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `movement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint NOT NULL,
  `date` date NOT NULL,
  `type` enum('CREDIT','DEBIT') NOT NULL,
  `amount` decimal(11,2) NOT NULL,
  `balance` decimal(11,2) NOT NULL,
  `description` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk-movement_account_id-account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
);
