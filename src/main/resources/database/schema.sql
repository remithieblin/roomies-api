CREATE TABLE `account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `provider_id` varchar(255) NOT NULL,
  `provider_token` varchar(255) NOT NULL,
  `created` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  CONSTRAINT uniq_ftoken UNIQUE (provider_token)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `account_info` (
  `account_id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '' ,
  `phone` varchar(255) NOT NULL DEFAULT '',
  `created` int(11) NOT NULL DEFAULT '0',
  CONSTRAINT uniq_email UNIQUE (email),
  CONSTRAINT uniq_phone UNIQUE (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `house` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `zip` varchar(255) ,
  `city` varchar(255) ,
  `created` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `house_id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) NOT NULL DEFAULT '' ,
  `phone` varchar(255) NOT NULL DEFAULT '',
  `created` int(11) NOT NULL DEFAULT '0',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) unsigned NOT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(75) NOT NULL DEFAULT '',
  `phone` varchar(20) NOT NULL DEFAULT '',
  `hour_of_day` int(11) NOT NULL DEFAULT '-1',
  `day_of_week` int(11) NOT NULL DEFAULT '-1',
  `day_of_month` int(11) NOT NULL DEFAULT '-1',
  `minute_of_hour` int(11) NOT NULL DEFAULT '-1',
  `timezone` varchar(100) NOT NULL DEFAULT 'PST',
  `created` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `house_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `house_id` int(11) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL DEFAULT '',
  `frequency` varchar(255) ,
  `start_time` int(11) NOT NULL DEFAULT '0',
  `end_time` int(11) NOT NULL DEFAULT '0',
  `ordered_accounts` varchar(255) NOT NULL DEFAULT '',
  `created` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;