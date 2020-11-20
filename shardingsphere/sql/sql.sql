-- 主库db0
CREATE DATABASE IF NOT EXISTS `db0`;
USE `db0`; SET NAMES utf8mb4; SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for user_0 --
-- --------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
-- Table structure for user_1 --
-- --------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;
-- 主库db1
CREATE DATABASE IF NOT EXISTS `db1`;
USE `db1`; SET NAMES utf8mb4; SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for user_0 --
-- --------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
-- Table structure for user_1 --
-- --------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;

-- 从库sdb0
CREATE DATABASE IF NOT EXISTS `sdb0`;
USE `sdb0`; SET NAMES utf8mb4; SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for user_0 --
-- --------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
-- Table structure for user_1 --
-- --------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;

-- 从库sdb1
CREATE DATABASE IF NOT EXISTS `sdb1`;
USE `sdb1`; SET NAMES utf8mb4; SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
-- Table structure for user_0 --
-- --------------------------
DROP TABLE IF EXISTS `user_0`;
CREATE TABLE `user_0` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- ----------------------------
-- Table structure for user_1 --
-- --------------------------
DROP TABLE IF EXISTS `user_1`;
CREATE TABLE `user_1` ( `id` bigint(11) NOT NULL, `name` varchar(255) DEFAULT NULL, `age` int(11) DEFAULT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;