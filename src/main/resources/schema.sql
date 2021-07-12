DROP TABLE IF EXISTS `account_transaction`;
DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `transaction_type`;
DROP TABLE IF EXISTS `currency`;
DROP TABLE IF EXISTS `account_type`;


CREATE TABLE `account_type` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `type` varchar(255) DEFAULT NULL,
    `optlock` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE `currency` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `type` varchar(255) DEFAULT NULL,
    `optlock` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE `transaction_type` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `type` varchar(255) DEFAULT NULL,
    `optlock` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `first_name` varchar(255) DEFAULT NULL,
    `last_name` varchar(255) DEFAULT NULL,
    `optlock` int NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
);

CREATE TABLE `account` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `account_name` varchar(255) DEFAULT NULL,
    `account_number` varchar(255) DEFAULT NULL,
    `available_balance` double NOT NULL,
    `balance_date` date DEFAULT NULL,
    `optlock` int NOT NULL DEFAULT '0',
    `account_type_id` bigint NOT NULL,
    `currency_id` bigint NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKgw84mgpacw9htdxcs2j1p7u6j` (`account_type_id`),
    KEY `FK316pn109iutn6yqoxrqp09cpc` (`currency_id`),
    KEY `FK7m8ru44m93ukyb61dfxw0apf6` (`user_id`),
    CONSTRAINT `FK316pn109iutn6yqoxrqp09cpc` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`),
    CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKgw84mgpacw9htdxcs2j1p7u6j` FOREIGN KEY (`account_type_id`) REFERENCES `account_type` (`id`)
);

CREATE TABLE `account_transaction` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL,
    `narration` varchar(255) DEFAULT NULL,
    `transaction_date` date DEFAULT NULL,
    `optlock` int NOT NULL DEFAULT '0',
    `account_id` bigint DEFAULT NULL,
    `transaction_type_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FKqonh25s0w6r5cf8jq88m6kd8o` (`account_id`),
    KEY `FK6yberemxcs5bqedy0vjdlu12i` (`transaction_type_id`),
    CONSTRAINT `FK6yberemxcs5bqedy0vjdlu12i` FOREIGN KEY (`transaction_type_id`) REFERENCES `transaction_type` (`id`),
    CONSTRAINT `FKqonh25s0w6r5cf8jq88m6kd8o` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
);



