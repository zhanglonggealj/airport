CREATE DATABASE IF NOT EXISTS `airport_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;
USE `airport_db`;

#系统token表
CREATE TABLE `t_token`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
    `bind_id`     bigint(20)  NOT NULL COMMENT '绑定对应的userId',
    `category`    int(1)      NOT NULL COMMENT 'token类别 1为微信小程序用户 2为 ？',
    `token_code`  varchar(50) NOT NULL COMMENT 'token Code',
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='token表';

/*创建tokencode 索引 提高查找速度*/
CREATE INDEX token_code ON t_token (token_code);


CREATE TABLE `t_bas_user`
(
    `id`               bigint(20)  NOT NULL AUTO_INCREMENT,
    `mobile_phone`     varchar(20) NOT NULL COMMENT '用户手机号',
    `nick_name`        varchar(100)         DEFAULT '' COMMENT '用户昵称',
    `head_portrait`    varchar(200)         DEFAULT '' COMMENT '用户头像',
    `sex`              int(1)      NOT NULL DEFAULT '0' COMMENT '1男  2女 0未定义',
    `password`         varchar(50)          DEFAULT NULL COMMENT '密码',
    `wx_openid`        varchar(50)          DEFAULT '' COMMENT '微信openid',
    `old_mobile_phone` varchar(20)          DEFAULT NULL COMMENT '上一次注册的手机号',
    `last_login_time`  datetime COMMENT '最后登录时间',
    `status`           tinyint(1)           DEFAULT '1' COMMENT '1:有效，0:禁止',
    `update_time`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='用户注册基本信息';

CREATE TABLE `t_bas_cons`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT,
    `cons_no`      varchar(16)  NOT NULL COMMENT '商户编号',
    `cons_name`    varchar(100) NOT NULL COMMENT '商户名称',
    `cons_address` varchar(256) NOT NULL COMMENT '商户地址',
    `cons_type`    varchar(2)   NOT NULL COMMENT '商户类型:01:专变, 02:公变, 03:低压',
    `org_no`       varchar(16)           DEFAULT NULL COMMENT '供电单位',
    `build_date`   datetime COMMENT '建档日期',
    `tariff_id`    bigint(16)            DEFAULT NULL COMMENT '电价类型',
    `tariff_name`  varchar(100)          DEFAULT NULL COMMENT '电价名称',
    `contract_cap` decimal(16, 6)        DEFAULT NULL COMMENT '受电容量',
    `volt_code`    varchar(8)            DEFAULT NULL COMMENT '电压等级',
    `tg_id`        varchar(32)           DEFAULT NULL COMMENT '台区id',
    `line_id`      bigint(16)            DEFAULT NULL COMMENT '所属线区',
    `mobile_phone` varchar(20)  NOT NULL COMMENT '用户手机号',
    `remark`       varchar(32)           DEFAULT NULL COMMENT '备注',
    `status_code`  varchar(32)  NOT NULL COMMENT '商户状态 1: 正常 2: 销户',
    `update_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='客户基本信息表';
# 需要加入时间和商户id索引 && 商户名称加入索引但是like索引无效
ALTER TABLE `airport_db`.`t_bas_cons`
    ADD UNIQUE INDEX `cons_index` (`cons_no`) USING BTREE COMMENT '商户号唯一索引';


CREATE TABLE `t_day_residual_money`
(
    `id`           bigint(20)     NOT NULL AUTO_INCREMENT,
    `batch_no`     bigint(20)     NOT NULL COMMENT '批次号',
    `cons_no`      varchar(16)    NOT NULL COMMENT '商户唯一id',
    `meter_id`     bigint(16)     NOT NULL COMMENT '电表号',
    `data_date`    datetime                DEFAULT NUll COMMENT '数据日期',
    `org_no`       varchar(32)             DEFAULT NULL COMMENT '管理单位编号',
    `col_time`     datetime                DEFAULT NULl COMMENT '终端抄表时间',
    `remain_enegy` decimal(18, 6) COMMENT '剩余电量',
    `remain_money` decimal(18, 2) NOT NULL COMMENT '剩余金额',
    `alarm_enegy`  decimal(18, 6) COMMENT '报警电量',
    `fail_enegy`   decimal(18, 6) COMMENT '故障电量',
    `sum_enegy`    decimal(18, 6) COMMENT '累计购电量',
    `sum_money`    decimal(18, 2) COMMENT '累计购电金额',
    `buy_num`      int(8) COMMENT '购电次数',
    `overdr_limit` decimal(18, 2) COMMENT '欠费限阈值',
    `overdr_enegy` decimal(18, 6) COMMENT '透支电量',
    `status`       tinyint(1)              DEFAULT '1' COMMENT '1:有效，0:禁止',
    `update_time`  timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`  timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='用户剩余金额表';

CREATE TABLE `t_bas_user_cons`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`     bigint(20) NOT NULL COMMENT '用户id',
    `cons_no`     varchar(16)         DEFAULT NULL COMMENT '商户唯一id',
    `status`      tinyint(1)          DEFAULT '1' COMMENT '1:有效，0:禁止',
    `update_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='用户商户绑定关系表';
# 需要加入用户id索引

CREATE TABLE `t_bas_meter`
(
    `id`                bigint(20) NOT NULL AUTO_INCREMENT,
    `cons_no`           varchar(16)         DEFAULT NULL COMMENT '商户唯一id',
    `meter_id`          bigint(16)          DEFAULT NULL COMMENT '电表唯一标识',
    `meter_made_no`     varchar(50)         DEFAULT NULL COMMENT '电表出厂号（有可能会重复）',
    `type_code`         varchar(8)          DEFAULT NULL COMMENT '电表类型',
    `inst_loc`          varchar(256)        DEFAULT NULL COMMENT '安装位置',
    `t_factor`          decimal(10, 2)      DEFAULT NULL COMMENT '倍率',
    `comm_addr`         varchar(16)         DEFAULT NULL COMMENT '通讯地址',
    `protocol_code`     varchar(8)          DEFAULT NULL COMMENT '通讯规约',
    `comm_mode`         varchar(8)          DEFAULT NULL COMMENT '通讯方式',
    `wiring_mode`       varchar(32)         DEFAULT NULL COMMENT '接线方式',
    `manufacturer`      varchar(16)         DEFAULT NULL COMMENT '表计厂家',
    `made_date`         varchar(32)         DEFAULT NULL COMMENT '出厂日期',
    `volt_code`         varchar(8)          DEFAULT NULL COMMENT '额定电压',
    `rated_current`     varchar(8)          DEFAULT NULL COMMENT '额定电流',
    `carrier_wave_id`   bigint(16)          DEFAULT NULL COMMENT '通讯模块信息',
    `prepay_flag`       varchar(8)          DEFAULT NULL COMMENT '是否预付费',
    `init_money`        int(8)              DEFAULT NULL COMMENT '预制金额，可能是元',
    `carry_money`       decimal(18, 2)      DEFAULT NULL COMMENT '结转金额 有负数 -',
    `addition_money`    decimal(18, 2)      DEFAULT NULL COMMENT '补加扣减金额 有负数 -',
    `remark`            varchar(200)        DEFAULT NULL COMMENT '备注',
    `meter_code`        varchar(8)          DEFAULT NULL COMMENT '表计状态 007: 运行 008: 在库 009:拆除',
    `purchase_count`    int(11)             DEFAULT '1' COMMENT '购电次数',
    `use_prepay_flag`   int(1)              DEFAULT '1' COMMENT '是否扣减预制金',
    `use_carry_flag`    int(1)              DEFAULT '1' COMMENT '是否结转费用',
    `use_addition_flag` int(1)              DEFAULT '1' COMMENT '是否补加扣减',
    `update_time`       timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`       timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='表计基本信息';
# 需要加入时间和商户id索引

ALTER TABLE `airport_db`.`t_bas_meter`
    ADD UNIQUE INDEX `meter_id` (`meter_id`) USING BTREE;

CREATE TABLE `t_purchase_order`
(
    `id`             bigint(20)  NOT NULL AUTO_INCREMENT,
    `order_id`       varchar(50) NOT NULL COMMENT '订单 单号',
    `pay_type`       tinyint(1)           DEFAULT '0' COMMENT '支付方式 0 无 1支付宝 2微信 3余额',
    `total_fee`      bigint(20)           DEFAULT '0' COMMENT '订单金额',
    `user_id`        bigint(20)           DEFAULT '-1' COMMENT '用户id 考虑非平台用户下单情况 非平台用户下单-1',
    `cons_no`        varchar(16) NOT NULL COMMENT '商户唯一id',
    `meter_id`       bigint(16)  NOT NULL COMMENT '电表唯一标识',
    `openid`         varchar(50)          DEFAULT '' COMMENT '微信openID',
    `transaction_id` varchar(50)          DEFAULT '' COMMENT '第三方系统交易订单号 根据pay_type 记录不对平台的支付交易订单 用于退款 和查询相关操作',
    `type`           tinyint(1)           DEFAULT '1' COMMENT '1下单未付款，2下单已付款',
    `notify_time`    datetime             DEFAULT NULL COMMENT '第三方支付通知时间',
    `issued_time`    datetime             DEFAULT NULL COMMENT '开始下发购电单时间',
    `issued_run`     tinyint(1)           DEFAULT '1' COMMENT '购电单发布次数',
    `issued_isrun`   tinyint(1)           DEFAULT '1' COMMENT '是否下发购电单中  1为没有执行 2为正在执行 3为执行完毕',
    `type2`          tinyint(1)           DEFAULT '1' COMMENT '1购电单下发单未通知，2购电单下发成功，3购电下发回调处理成功',
    `err_msg`        varchar(250)         DEFAULT '' COMMENT '错误原因',
    `send_time`      datetime             DEFAULT NULL COMMENT '下发购电单结果通知时间',
    `issued_type`    tinyint(1)           DEFAULT '0' COMMENT '1平台 2应急系统',
    `send_money`     decimal(11, 2)              DEFAULT '0' COMMENT '下发金额',
    `addition_money` decimal(11, 2)       DEFAULT '0' COMMENT '补加扣减',
    `init_money`     decimal(18, 2)       DEFAULT '0' COMMENT '预制金额',
    `status`         tinyint(1)           DEFAULT '1' COMMENT '1:有效，0:无效',
    `update_time`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='购电单信息表';

ALTER TABLE `airport_db`.`t_purchase_order`
    ADD INDEX `notify_time_type`(`notify_time`, `type`) USING BTREE COMMENT '统计关键索引';
ALTER TABLE `airport_db`.`t_purchase_order`
    ADD INDEX `order_id`(`order_id`) USING BTREE COMMENT '订单id索引';

CREATE TABLE `t_day_freeze`
(
    `id`            bigint(20)     NOT NULL AUTO_INCREMENT,
    `batch_no`      bigint(20)     NOT NULL COMMENT '批次号',
    `cons_no`       varchar(16)             DEFAULT NULL COMMENT '商户唯一id',
    `meter_id`      bigint(16)     NOT NULL COMMENT '电表唯一标识',
    `data_date`     datetime       NOT NULL COMMENT '数据日期',
    `org_no`        varchar(32)    NOT NULL COMMENT '数据日期',
    `col_time`      datetime       NOT NULL COMMENT '终端抄表时间',
    `pap_r`         decimal(11, 4) NOT NULL COMMENT '正向有功总电能示值',
    `pap_r1`        decimal(11, 4) COMMENT '正向有功费率1电能示值',
    `pap_r2`        decimal(11, 4) COMMENT '正向有功费率2电能示值',
    `pap_r3`        decimal(11, 4) COMMENT '正向有功费率3电能示值',
    `pap_r4`        decimal(11, 4) COMMENT '正向有功费率4电能示值',
    `data_res_flag` bigint(3) COMMENT '',
    `crc`           decimal(20, 4) COMMENT '',
    `status`        tinyint(1)              DEFAULT '1' COMMENT '1:有效，0:禁止',
    `update_time`   timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`   timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='日冻结示数';


# CREATE TABLE `merchant_electricity`
# (
#     `id`            bigint(20) NOT NULL AUTO_INCREMENT,
#     `merchant_id`   varchar(100)        DEFAULT NULL COMMENT '商户唯一id',
#     `merchant_name` varchar(100)        DEFAULT NULL COMMENT '商户名称',
#     `address`       varchar(50)         DEFAULT NULL COMMENT '商户地址 1b-1f-27',
#     `amount`        bigint(20)          DEFAULT '0' COMMENT '消费度数 ',
#     `money`         bigint(20)          DEFAULT '0' COMMENT '消费金额 单位分 ',
#     `start_time`    timestamp  NOT NULL COMMENT '统计开始时间',
#     `end_time`      timestamp  NOT NUll COMMENT '统计结束时间',
#     `status`        tinyint(1)          DEFAULT '1' COMMENT '1:有效，0:无效',
#     `update_time`   timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
#     `create_time`   timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     PRIMARY KEY (`id`)
# ) ENGINE = InnoDB
#   CHARSET = utf8mb4 COMMENT ='商户表用电明细表';
# # 需要加入时间和商户id索引


/*事件消息通知*/
CREATE TABLE `t_bas_msg_push`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `event_type`   int(4)       NOT NULL COMMENT '事件类型 1:1级余额不足 2:2级余额不足 3:3级余额不足 ...',
    `push_type`    int(1)       NOT NULL COMMENT '推送类型 1为短信',
    `cons_no`      varchar(16)  NOT NULL COMMENT '商户号',
    `mobile_phone` varchar(20)  NOT NULL COMMENT '手机号',
    `push_time`    datetime              DEFAULT CURRENT_TIMESTAMP COMMENT '事件推送时间',
    `push_text`    varchar(200) NOT NULL COMMENT '推送消息内容',
    `status`       int(1)       NOT NULL DEFAULT '1' COMMENT '默认1 -1 推送消息异常 2为消息推送成功 ',
    `update_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='事件消息通知';

CREATE TABLE `t_config`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `config_name` VARCHAR(30) COMMENT '配置名称',
    `config_data` VARCHAR(1023) COMMENT '配置内容',
    `status`      tinyint(1)          DEFAULT '1' COMMENT '1:有效，0:无效',
    `updateTime`  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `createTime`  timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='系统总配置表';


CREATE TABLE `t_day_bill`
(
    `id`                      bigint(20)  NOT NULL AUTO_INCREMENT,
    `purchase_id`             varchar(50) NOT NULL COMMENT '购电单标识（购电模块产生的ID）',
    `transaction_id`          varchar(50) NOT NULL COMMENT '微信产生的购电单号',
    `purchase_date`           datetime COMMENT '购电时间',
    `wx_total_fee`            decimal(18, 2) COMMENT '微信收款金额',
    `poundage`                varchar(50) COMMENT '手续费',
    `poundage_rate`           varchar(50) COMMENT '费率',
    `cons_no`                 varchar(16) COMMENT '商户唯一id',
    `meter_id`                bigint(16) COMMENT '电表号',
    `purchase_money`          decimal(18, 2) COMMENT '购电金额',
    `addition_money`          decimal(18, 2) COMMENT '补加扣减',
    `init_money`              decimal(18, 2) COMMENT '预制金额',
    `send_money`              decimal(18, 2) COMMENT '下发金额',
    `abnormal_purchase_money` decimal(18, 2) COMMENT '异常购电金额',
    `abnormal_addition_money` decimal(18, 2) COMMENT '异常_补加扣减',
    `abnormal_init_money`     decimal(18, 2) COMMENT '异常_预制金额',
    `abnormal_send_money`     decimal(18, 2) COMMENT '异常_下发金额',
    `send_date`               datetime COMMENT '下发时间',
    `wx_ok`                   int(1)               DEFAULT '0' COMMENT '微信完成对账',
    `platform_ok`             int(1)               DEFAULT '0' COMMENT '平台完成对账',
    `bill_status`             int(1)               DEFAULT '0' COMMENT '订单状态是否异常，当微信和购电平台对账后，出现未配对数据则异常',
    `updateTime`              timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time`             timestamp            DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='购电系统订单数据';

CREATE TABLE `t_bas_text_push`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `data`        text       NOT NULL COMMENT '待发送的数据',
    `type`        tinyint(4) NOT NULL COMMENT '待发送的数据类型',
    `status`      tinyint(1) DEFAULT '0' COMMENT '0:，0:待发送 1:已发送',
    `update_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_time` timestamp  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='第三方信息待推送表';

