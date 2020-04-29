--
-- --
-- Table structure for table `account`
--
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(200) NOT NULL,
  `balance` double unsigned NOT NULL DEFAULT '0',
  `created_time` timestamp  DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tx_count` int(11) NOT NULL DEFAULT '0',
  `total_input` double DEFAULT NULL,
  `total_output` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_address_unique` (`address`),
  KEY `account_address_index` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Table structure for table `block`
--
DROP TABLE IF EXISTS `block`;
CREATE TABLE `block` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `height` bigint(20) unsigned DEFAULT NULL,
  `hash` varchar(200) NOT NULL,
  `prev_block_hash` varchar(200) NOT NULL,
  `next_block_hash` varchar(200) DEFAULT NULL,
  `merkle_root` varchar(200) NOT NULL,
  `miner_address` varchar(200) DEFAULT NULL,
  `tx_count` int(11) NOT NULL DEFAULT '0',
  `difficulty` double  DEFAULT NULL,
  `sum_difficulty` double unsigned DEFAULT NULL,
  `hash_rate` double DEFAULT NULL,
  `transaction_fees` double NOT NULL DEFAULT '0',
  `output_total` double NOT NULL DEFAULT '0',
  `size` bigint(20)  NOT NULL,
  `version` bigint(20)  DEFAULT NULL,
  `version_hex` varchar(1000) DEFAULT NULL,
  `nonce` bigint(20) unsigned DEFAULT NULL,
  `bits` bigint(20) unsigned DEFAULT NULL,
  `confirmations` int(11) NOT NULL DEFAULT '0',
  `reward` double unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `height_UNIQUE` (`height`),
  KEY `block_height_index` (`height`),
  KEY `block_hash_index` (`hash`),
  KEY `block_created_time` (`created_time`),
  KEY `block_tx_count` (`tx_count`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Table structure for table `block_chain`
--
DROP TABLE IF EXISTS `block_chain`;
CREATE TABLE `block_chain` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(200)  DEFAULT NULL,
  `type` int(2)  NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `block_chain_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Table structure for table `block_chain`
--
DROP TABLE IF EXISTS `orphan_block`;
CREATE TABLE `orphan_block` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `height` bigint(20) unsigned DEFAULT NULL,
  `hash` varchar(200) NOT NULL,
  `merkle_root` varchar(200) NOT NULL,
  `miner_address` varchar(200) DEFAULT NULL,
  `tx_count` int(11) NOT NULL DEFAULT '0',
  `difficulty` double  DEFAULT NULL,
  `transaction_fees` double NOT NULL DEFAULT '0',
  `size` bigint(20)  NOT NULL,
  `nonce` bigint(20) unsigned DEFAULT NULL,
  `bits` bigint(20) unsigned DEFAULT NULL,
  `confirmations` int(11) NOT NULL DEFAULT '0',
  `reward` double unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `height_UNIQUE` (`height`),
  KEY `orphan_height_index` (`height`),
  KEY `orphan_hash_index` (`hash`),
  KEY `orphan_created_time` (`created_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Table structure for table `sync_account`
--
DROP TABLE IF EXISTS `sync_account`;
CREATE TABLE `sync_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(200) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_locked` datetime DEFAULT NULL,
  `origin` varchar(45) DEFAULT NULL,
  `tx_timestamp` datetime DEFAULT NULL,
  `node_id` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `address_unique` (`address`,`origin`),
  KEY `address_index` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



--
-- Table structure for table `sync_block`
--
DROP TABLE IF EXISTS `sync_block`;
CREATE TABLE `sync_block` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `block_num` bigint(20) unsigned DEFAULT NULL,
  `date_start` datetime(3) DEFAULT NULL,
  `date_end` datetime(3) DEFAULT NULL,
  `date_locked` datetime(3) DEFAULT NULL,
  `node_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `block_num_UNIQUE` (`block_num`),
  KEY `block_num_index` (`block_num`),
  KEY `date_start_index` (`date_start`),
  KEY `date_end_index` (`date_end`),
  KEY `date_locked_index` (`date_locked`),
  KEY `node_id_index` (`node_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `sync_node`
--
DROP TABLE IF EXISTS `sync_node`;
CREATE TABLE `sync_node` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `node_id` int(11) NOT NULL,
  `is_validating` varchar(45) NOT NULL DEFAULT '0',
  `ping` datetime DEFAULT NULL,
  `sync_start_full` bigint(20) DEFAULT NULL,
  `sync_end_full` bigint(20) DEFAULT NULL,
  `start_full_date` datetime DEFAULT NULL,
  `end_full_date` datetime DEFAULT NULL,
  `sync_start_solidity` bigint(20) DEFAULT NULL,
  `sync_end_solidity` bigint(20) DEFAULT NULL,
  `start_solidity_date` datetime DEFAULT NULL,
  `end_solidity_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `node_id_UNIQUE` (`node_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




--
-- Table structure for table `transaction`
--
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `hash` varchar(200) NOT NULL,
  `block_hash` varchar(200) NOT NULL,
  `block_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `created_time` timestamp DEFAULT CURRENT_TIMESTAMP,
  `size` bigint(20) DEFAULT NULL,
  `weight` bigint(20) DEFAULT NULL,
  `fees` double unsigned DEFAULT NULL,
  `total_input` double unsigned DEFAULT NULL,
  `total_output` double unsigned DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `confirmations` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `hex` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `hash_UNIQUE` (`hash`),
  KEY `transaction_block_hash` (`block_hash`),
  KEY `transaction_hash` (`hash`),
  KEY `transaction_created_time` (`created_time` desc ),
  KEY `transaction_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




--
-- Table structure for table `transfer_in`
--
DROP TABLE IF EXISTS `transfer_in`;
CREATE TABLE `transfer_in` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `amount` double unsigned NOT NULL default '0',
  `transaction_id` bigint(20) unsigned DEFAULT NULL,
  `transaction_hash` varchar(200) DEFAULT NULL,
  `address` varchar(200)  DEFAULT NULL,
  `sequence` bigint(20)  DEFAULT NULL,
  `asm` varchar(1000) DEFAULT NULL,
  `hex` varchar(1000) DEFAULT NULL,
  `coin_base` varchar(1000) DEFAULT NULL,
  `vout` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transfer_in_transaction_id` (`transaction_id`),
  KEY `transfer_in_transaction_hash` (`transaction_hash`),
  KEY `transfer_in_address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



--
-- Table structure for table `transfer_out`
--
DROP TABLE IF EXISTS `transfer_out`;
CREATE TABLE `transfer_out` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `amount` double unsigned NOT NULL default '0',
  `transaction_id` bigint(20) unsigned DEFAULT NULL,
  `transaction_index` int(11) DEFAULT NULL,
  `address` varchar(200)  DEFAULT NULL,
  `asm` varchar(1000) DEFAULT NULL,
  `hex` varchar(1000) DEFAULT NULL,
  `script_pubkey_type` varchar(200) DEFAULT NULL,
  `reqsigs` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transfer_out_transaction_id` (`transaction_id`),
  KEY `transfer_out_address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


--
-- Table structure for table `transfer_utxo`
--
DROP TABLE IF EXISTS `transfer_utxo`;
CREATE TABLE `transfer_utxo` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `amount` double unsigned NOT NULL default '0',
  `tx_hash` varchar(200) DEFAULT NULL,
  `address` varchar(200)  DEFAULT NULL,
  `transfer_out_id` bigint(20) unsigned DEFAULT NULL,
  `vout` int(11) DEFAULT NULL,
  `script_pub_key` varchar(1000) DEFAULT NULL,
  `coin_base` int(2) NOT NULL DEFAULT '0',
  `status` int(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `t_utxo_transaction_hash` (`tx_hash`),
  KEY `t_utxo_transfer_out_id` (`transfer_out_id`),
  KEY `t_utxo_address` (`address`),
  KEY `t_utxo_coin_base` (`coin_base`),
  KEY `t_utxo_status` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


