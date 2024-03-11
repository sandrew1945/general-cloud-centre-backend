-- -------------------------------------------------------------
-- TablePlus 5.4.2(506)
--
-- https://tableplus.com/
--
-- Database: general
-- Generation Time: 2024-03-05 15:52:29.6270
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE TABLE `tm_dept` (
  `dept_id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dept_code` varchar(50) DEFAULT NULL COMMENT '部门代码',
  `dept_name` varchar(200) DEFAULT NULL COMMENT '部门名称',
  `dept_desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `father_dept` int(8) DEFAULT NULL COMMENT '上级部门',
  `status` int(8) DEFAULT NULL COMMENT '状态',
  `is_delete` int(8) DEFAULT NULL COMMENT '删除标记',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

CREATE TABLE `tm_fixcode` (
  `fixcode_id` int(8) NOT NULL,
  `type` varchar(8) CHARACTER SET utf8 DEFAULT NULL,
  `type_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `code_desc` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`fixcode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

CREATE TABLE `tm_menu` (
  `menu_id` int(8) NOT NULL AUTO_INCREMENT,
  `path` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `icon` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `func_order` int(3) DEFAULT NULL,
  `father_id` int(8) DEFAULT NULL,
  `is_delete` int(8) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='菜单表';

CREATE TABLE `tm_post` (
  `post_id` int(8) NOT NULL AUTO_INCREMENT COMMENT '职位id',
  `post_code` varchar(50) DEFAULT NULL COMMENT '职位代码',
  `post_name` varchar(200) DEFAULT NULL COMMENT '职位名称',
  `post_desc` varchar(500) DEFAULT NULL COMMENT '职位描述',
  `dept_id` int(8) DEFAULT NULL COMMENT '所属部门',
  `type` int(8) DEFAULT NULL COMMENT '职位类型：高管、部门领导、员工等',
  `status` int(8) DEFAULT NULL COMMENT '状态',
  `is_delete` int(8) DEFAULT NULL COMMENT '删除标记',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=588 DEFAULT CHARSET=utf8mb4 COMMENT='职位表';

CREATE TABLE `tm_role` (
  `role_id` int(8) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `role_name` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `role_type` int(8) DEFAULT NULL,
  `role_status` int(8) DEFAULT NULL,
  `is_delete` int(8) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

CREATE TABLE `tm_user` (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `sex` int(8) DEFAULT NULL,
  `phone` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `mobile` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `avatar` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '头像',
  `user_type` int(8) DEFAULT NULL,
  `user_status` int(8) DEFAULT NULL,
  `is_delete` int(8) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3425 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `tr_role_func` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `role_id` int(8) DEFAULT NULL,
  `menu_id` int(8) DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `tr_user_dept` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) DEFAULT NULL,
  `dept_id` int(8) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3407 DEFAULT CHARSET=utf8mb4 COMMENT='用户部门关系表';

CREATE TABLE `tr_user_post` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) DEFAULT NULL,
  `post_id` int(8) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户职位关系表';

CREATE TABLE `tr_user_role` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) DEFAULT NULL,
  `role_id` int(8) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

INSERT INTO `tm_dept` (`dept_id`, `dept_code`, `dept_name`, `dept_desc`, `father_dept`, `status`, `is_delete`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(1, NULL, '总公司', NULL, NULL, 10011001, 10031002, NULL, NULL, NULL, NULL),
(2, NULL, '高管人员', NULL, 1, 10011001, 10031002, NULL, NULL, NULL, NULL),
(3, NULL, '总裁助理', NULL, 1, 10011001, 10031002, NULL, NULL, NULL, NULL),
(4, NULL, '总监', NULL, 1, 10011001, 10031002, NULL, NULL, NULL, NULL),
(16, NULL, '财富与机构条线', NULL, 1, 10011001, 10031002, NULL, NULL, NULL, NULL),
(152, NULL, '浙江分公司', NULL, 16, 10011001, 10031002, NULL, NULL, NULL, NULL),
(296, NULL, '浙江xxx营业部', NULL, 152, 10011001, 10031002, NULL, NULL, NULL, NULL);

INSERT INTO `tm_fixcode` (`fixcode_id`, `type`, `type_name`, `code_desc`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(10011001, '1001', '状态', '有效', '-1', '2016-09-02 00:00:00', NULL, NULL),
(10011002, '1001', '状态', '无效', '-1', '2016-09-02 00:00:00', NULL, NULL),
(10021001, '1002', '性别', '男', '-1', '2016-09-02 00:00:00', NULL, NULL),
(10021002, '1002', '性别', '女', '-1', '2016-09-02 00:00:00', NULL, NULL),
(10021003, '1002', '性别', '未知', '-1', '2016-09-02 00:00:00', NULL, NULL),
(10031001, '1003', '是否', '是', '-1', '2016-09-02 00:00:00', NULL, NULL),
(10031002, '1003', '是否', '否', '-1', '2016-09-02 00:00:00', NULL, NULL),
(20011001, '2001', '员工状态', '在职', '-1', '2023-02-16 14:11:52', NULL, NULL),
(20011002, '2001', '员工状态', '离职', '-1', '2023-02-16 14:11:52', NULL, NULL),
(20011003, '2001', '员工状态', '退休', '-1', '2023-02-16 14:11:52', NULL, NULL),
(20011004, '2001', '员工状态', '离岗退养', '-1', '2023-02-16 14:11:52', NULL, NULL);

INSERT INTO `tm_menu` (`menu_id`, `path`, `title`, `icon`, `func_order`, `father_id`, `is_delete`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(1, '/perm', '权限管理', 'fingerprint', 1000, NULL, 10031002, '-1', '2020-11-10 14:54:34', '1', '2020-11-11 10:40:53'),
(2, '/perm/user/manager', '用户管理', 'person_add', 1001, 1, 10031002, '-1', '2020-11-10 14:55:23', '1', '2020-11-12 08:51:02'),
(3, '/perm/role/manager', '角色管理', 'people', 1002, 1, 10031002, '-1', '2020-11-10 14:55:23', '1', '2020-11-11 10:40:53'),
(4, '/perm/function/manager', '菜单管理', 'list', 1003, 1, 10031002, '-1', '2020-11-10 15:11:29', 'admin', '2023-11-22 16:40:50'),
(5, '/index/dashboard', '首页', 'dashboard', 0, NULL, 10031002, '1', '2020-11-11 13:23:42', NULL, NULL);

INSERT INTO `tm_post` (`post_id`, `post_code`, `post_name`, `post_desc`, `dept_id`, `type`, `status`, `is_delete`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(1, NULL, '销售经理', NULL, NULL, NULL, 10011001, 10031002, '-1', '2023-02-16 14:59:50', NULL, NULL),
(2, NULL, '项目助理', NULL, NULL, NULL, 10011001, 10031002, '-1', '2023-02-16 14:59:53', NULL, NULL);

INSERT INTO `tm_role` (`role_id`, `role_code`, `role_name`, `role_type`, `role_status`, `is_delete`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(1, 'r_admin', '系统管理员', NULL, 10011001, 10031002, '-1', '2016-09-02 09:44:27', '1', '2016-09-19 16:17:05'),
(2, 'r_service_manager', '服务管理员', NULL, 10011001, 10031002, '-1', '2016-09-07 16:23:23', '1', '2020-04-09 14:23:29'),
(3, 'r_developer', '研发人员', NULL, 10011001, 10031002, '1', '2020-04-09 14:24:06', '1', '2020-11-13 16:00:04'),
(4, 'r_test', '测试角色2', NULL, 10011001, 10031002, 'admin', '2023-11-22 14:41:26', 'admin', '2023-11-22 16:42:15');

INSERT INTO `tm_user` (`user_id`, `user_code`, `user_name`, `password`, `sex`, `phone`, `mobile`, `email`, `birthday`, `avatar`, `user_type`, `user_status`, `is_delete`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES
(1, 'admin', '系统管理员', 'e10adc3949ba59abbe56e057f20f883e', 10021003, '', '', 'publish@nesc.cn', '1899-12-31', '/avatar/2023112213562632965.jpg', NULL, 20011001, 10031002, '2016-09-02 00:00:00', '-1', '2023-11-22 13:56:33', 'admin'),
(2, 'test', '测试账号', 'e10adc3949ba59abbe56e057f20f883e', 10021003, '89807654', '13888888888', 'test@nesc.cn', '2023-11-22', '', NULL, 20011001, 10031001, '2023-11-22 14:05:31', 'admin', '2023-11-22 14:07:17', 'admin');

INSERT INTO `tr_role_func` (`id`, `role_id`, `menu_id`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(23, 1, 1, '-1', '2016-09-21 15:39:52', NULL, NULL),
(24, 1, 2, '-1', '2016-09-21 15:39:52', NULL, NULL),
(25, 1, 3, '-1', '2023-11-22 14:23:50', NULL, NULL),
(26, 1, 4, '-1', '2023-11-22 14:23:50', NULL, NULL),
(27, 1, 5, '-1', '2023-11-22 14:23:50', NULL, NULL),
(28, 4, 5, 'admin', '2023-11-22 16:41:46', NULL, NULL),
(29, 4, 1, 'admin', '2023-11-22 16:41:46', NULL, NULL),
(30, 4, 2, 'admin', '2023-11-22 16:41:46', NULL, NULL),
(31, 4, 3, 'admin', '2023-11-22 16:41:46', NULL, NULL),
(32, 4, 4, 'admin', '2023-11-22 16:41:46', NULL, NULL);

INSERT INTO `tr_user_dept` (`id`, `user_id`, `dept_id`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(2, 3, 2, NULL, NULL, NULL, NULL),
(501, 504, 6, NULL, NULL, NULL, NULL);


INSERT INTO `tr_user_role` (`id`, `user_id`, `role_id`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(1, 1, 1, '-1', '2023-10-30 14:15:24', NULL, NULL),
(2, 1, 2, '-1', '2023-10-30 14:46:12', NULL, NULL),
(5, 1, 3, 'admin', '2023-11-16 16:16:34', NULL, NULL);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;