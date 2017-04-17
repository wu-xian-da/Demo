CREATE DATABASE `witplat` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE witplat;

CREATE TABLE `t_department` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `sort` int(11) NOT NULL DEFAULT '0',
  `parent_id` bigint(20) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_menu"
#

CREATE TABLE `t_menu` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `href` varchar(200) NOT NULL DEFAULT '',
  `permission` varchar(80) NOT NULL DEFAULT '',
  `type` smallint(1) NOT NULL DEFAULT '0',
  `sort` int(4) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '-1',
  `icon` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_role"
#

CREATE TABLE `t_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_role_menu"
#

CREATE TABLE `t_role_menu` (
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "t_user"
#

CREATE TABLE `t_user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '',
  `loginName` varchar(20) NOT NULL DEFAULT '',
  `tel` varchar(20) DEFAULT NULL,
  `password` varchar(32) NOT NULL DEFAULT '',
  `role_id` bigint(20) NOT NULL DEFAULT '-1',
  `department_id` bigint(20) NOT NULL DEFAULT '-1',
  `status` smallint(1) NOT NULL DEFAULT '0',
  `salt` varchar(32) NOT NULL DEFAULT '',
  `createDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
#2017-04-11 15:00 ath begin
#

#
# Structure for table "tb_img_news"
#

CREATE TABLE `tb_img_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `img_path` varchar(500) DEFAULT NULL,
  `content` longtext,
  `status` smallint(1) DEFAULT NULL COMMENT '待审核;\n            审核通过;\n            已上刊;\n            已下刊',
  `check_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `push_status` smallint(1) DEFAULT NULL COMMENT '已推送;未推送',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片新闻表';

#
# Structure for table "tb_nav_base"
#

CREATE TABLE `tb_nav_base` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nav_name` varchar(200) DEFAULT NULL,
  `nav_type` smallint(1) DEFAULT NULL COMMENT '下辖二级菜单;\n            无二级菜单;\n            URL外链',
  `nav_level` int(11) DEFAULT NULL COMMENT '一级栏目;\n            二级栏目;',
  `nav_icon` varchar(500) DEFAULT NULL,
  `nav_order_num` int(11) DEFAULT '0',
  `nav_status` smallint(1) DEFAULT NULL COMMENT '展示;\n            取消展示;',
  `parent_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目基础表';

#
# Structure for table "tb_nav_content"
#

CREATE TABLE `tb_nav_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content_head_icon` varchar(500) DEFAULT NULL,
  `content_template_id` bigint(20) DEFAULT NULL,
  `nav_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表扩展_内容';

#
# Structure for table "tb_nav_info"
#

CREATE TABLE `tb_nav_info` (
  `id` bigint(20) NOT NULL,
  `title` varchar(500) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '交通类;文章类等',
  `status` smallint(1) DEFAULT NULL COMMENT '待审核;\n            审核通过;\n            已上刊;\n            已下刊',
  `content` longtext,
  `nav_id` bigint(20) DEFAULT NULL,
  `check_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `push_status` smallint(1) DEFAULT NULL COMMENT '已推送;未推送',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目信息表';

#
# Structure for table "tb_nav_second_menu"
#

CREATE TABLE `tb_nav_second_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_head_icon` varchar(500) DEFAULT NULL,
  `menu_list_template_id` bigint(20) DEFAULT NULL,
  `menu_content_template_id` bigint(20) DEFAULT NULL,
  `nav_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表扩展_下辖二级菜单';

#
# Structure for table "tb_nav_url"
#

CREATE TABLE `tb_nav_url` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(500) DEFAULT NULL,
  `target` varchar(6) DEFAULT NULL COMMENT '_blank:新页面打开;\n            _self:当前页打开;',
  `nav_id` bigint(20) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目表扩展_外链';

#
# Structure for table "tb_notice"
#

CREATE TABLE `tb_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `content` longtext,
  `status` smallint(1) DEFAULT NULL COMMENT '待审核;\n            审核通过;\n            已上刊;\n            已下刊',
  `check_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `push_status` smallint(1) DEFAULT NULL COMMENT '已推送;未推送',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='紧急公告表';

#
# Structure for table "tb_template"
#

CREATE TABLE `tb_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(500) DEFAULT NULL,
  `img_path` varchar(500) DEFAULT NULL,
  `file_path` varchar(500) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '列表页模板;\n            内容页模板;',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模板表';

#
#2017-04-11 15:00 ath end
#
