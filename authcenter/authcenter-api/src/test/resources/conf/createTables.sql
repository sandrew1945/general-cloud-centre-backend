-- -------------------------------------------------------------
-- TablePlus 5.4.2(506)
--
-- https://tableplus.com/
--
-- Database: general
-- Generation Time: 2023-11-01 13:40:13.8650
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE TABLE `tm_dept`
(
    `dept_id`     int(8) PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
    `dept_code`   varchar(50)  DEFAULT NULL COMMENT '部门代码',
    `dept_name`   varchar(200) DEFAULT NULL COMMENT '部门名称',
    `dept_desc`   varchar(500) DEFAULT NULL COMMENT '描述',
    `father_dept` int(8) DEFAULT NULL COMMENT '上级部门',
    `status`      int(8) DEFAULT NULL COMMENT '状态',
    `is_delete`   int(8) DEFAULT NULL COMMENT '删除标记',
    `create_by`   varchar(50)  DEFAULT NULL COMMENT '创建时间',
    `create_date` datetime     DEFAULT NULL COMMENT '创建人',
    `update_by`   varchar(50)  DEFAULT NULL COMMENT '更新时间',
    `update_date` datetime     DEFAULT NULL COMMENT '更新人'
) ENGINE=InnoDB COMMENT='部门表';

CREATE TABLE `tm_fixcode`
(
    `fixcode_id`  int(8) PRIMARY KEY,
    `type`        varchar(8)  DEFAULT NULL,
    `type_name`   varchar(50) DEFAULT NULL,
    `code_desc`   varchar(50) DEFAULT NULL,
    `create_by`   varchar(50) DEFAULT NULL,
    `create_date` datetime    DEFAULT NULL,
    `update_by`   varchar(50) DEFAULT NULL,
    `update_date` datetime    DEFAULT NULL
) ENGINE=InnoDB COMMENT='字典表';


CREATE TABLE `tm_menu`
(
    `menu_id`     int(8) PRIMARY KEY AUTO_INCREMENT,
    `path`        varchar(50) DEFAULT NULL,
    `title`       varchar(50) DEFAULT NULL,
    `icon`        varchar(50) DEFAULT NULL,
    `func_order`  int(3) DEFAULT NULL,
    `father_id`   int(8) DEFAULT NULL,
    `is_delete`   int(8) DEFAULT NULL,
    `create_by`   varchar(50) DEFAULT NULL,
    `create_date` datetime    DEFAULT NULL,
    `update_by`   varchar(50) DEFAULT NULL,
    `update_date` datetime    DEFAULT NULL
) ENGINE=InnoDB COMMENT='菜单表';

CREATE TABLE `tm_post`
(
    `post_id`     int(8) PRIMARY KEY AUTO_INCREMENT COMMENT '职位id',
    `post_code`   varchar(50)  DEFAULT NULL COMMENT '职位代码',
    `post_name`   varchar(200) DEFAULT NULL COMMENT '职位名称',
    `post_desc`   varchar(500) DEFAULT NULL COMMENT '职位描述',
    `dept_id`     int(8) DEFAULT NULL COMMENT '所属部门',
    `type`        int(8) DEFAULT NULL COMMENT '职位类型：高管、部门领导、员工等',
    `status`      int(8) DEFAULT NULL COMMENT '状态',
    `is_delete`   int(8) DEFAULT NULL COMMENT '删除标记',
    `create_by`   varchar(50)  DEFAULT NULL COMMENT '创建人',
    `create_date` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(50)  DEFAULT NULL COMMENT '更新人',
    `update_date` datetime     DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB COMMENT='职位表';

CREATE TABLE `tm_role`
(
    `role_id`     int(8) PRIMARY KEY AUTO_INCREMENT,
    `role_code`   varchar(30) DEFAULT NULL,
    `role_name`   varchar(30) DEFAULT NULL,
    `role_type`   int(8) DEFAULT NULL,
    `role_status` int(8) DEFAULT NULL,
    `is_delete`   int(8) DEFAULT NULL,
    `create_by`   varchar(50) DEFAULT NULL,
    `create_date` datetime    DEFAULT NULL,
    `update_by`   varchar(50) DEFAULT NULL,
    `update_date` datetime    DEFAULT NULL
) ENGINE=InnoDB COMMENT='角色表';


CREATE TABLE tm_user
(
    `user_id`     int(8) PRIMARY KEY AUTO_INCREMENT,
    `user_code`   varchar(50)  DEFAULT NULL,
    `user_name`   varchar(50)  DEFAULT NULL,
    `password`    varchar(50)  DEFAULT NULL,
    `sex`         int(8) DEFAULT NULL,
    `phone`       varchar(30)  DEFAULT NULL,
    `mobile`      varchar(30)  DEFAULT NULL,
    `email`       varchar(30)  DEFAULT NULL,
    `birthday`    date         DEFAULT NULL,
    `avatar`      varchar(100) DEFAULT NULL COMMENT '头像',
    `user_type`   int(8) DEFAULT NULL,
    `user_status` int(8) DEFAULT NULL,
    `is_delete`   int(8) DEFAULT NULL,
    `create_date` datetime     DEFAULT NULL,
    `create_by`   varchar(50)  DEFAULT NULL,
    `update_date` datetime     DEFAULT NULL,
    `update_by`   varchar(50)  DEFAULT NULL
) ENGINE=InnoDB COMMENT='用户表';

CREATE TABLE `tr_role_func`
(
    `id`          int(8) PRIMARY KEY AUTO_INCREMENT,
    `role_id`     int(8) DEFAULT NULL,
    `menu_id`     int(8) DEFAULT NULL,
    `create_by`   varchar(50) DEFAULT NULL,
    `create_date` datetime DEFAULT NULL,
    `update_by`   varchar(50) DEFAULT NULL,
    `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE `tr_user_dept`
(
    `id`          int(8) PRIMARY KEY AUTO_INCREMENT,
    `user_id`     int(8) DEFAULT NULL,
    `dept_id`     int(8) DEFAULT NULL,
    `create_by`   varchar(50) DEFAULT NULL,
    `create_date` datetime    DEFAULT NULL,
    `update_by`   varchar(50) DEFAULT NULL,
    `update_date` datetime    DEFAULT NULL
) ENGINE=InnoDB COMMENT='用户部门关系表';

CREATE TABLE `tr_user_post`
(
    `id`          int(8) PRIMARY KEY AUTO_INCREMENT,
    `user_id`     int(8) DEFAULT NULL,
    `post_id`     int(8) DEFAULT NULL,
    `create_by`   varchar(50) DEFAULT NULL,
    `create_date` datetime    DEFAULT NULL,
    `update_by`   varchar(50) DEFAULT NULL,
    `update_date` datetime    DEFAULT NULL
) ENGINE=InnoDB COMMENT='用户职位关系表';

CREATE TABLE `tr_user_role`
(
    `id`          int(8) PRIMARY KEY AUTO_INCREMENT,
    `user_id`     int(8) DEFAULT NULL,
    `role_id`     int(8) DEFAULT NULL,
    `create_by`   varchar(50) DEFAULT NULL,
    `create_date` datetime    DEFAULT NULL,
    `update_by`   varchar(50) DEFAULT NULL,
    `update_date` datetime    DEFAULT NULL
) ENGINE=InnoDB COMMENT='用户角色关系表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;