CREATE DATABASE IF NOT EXISTS `airport_middle_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;;
USE `airport_middle_db`;

CREATE TABLE `t_day_electricity_freeze`
(
    `id`            bigint(20)     NOT NULL AUTO_INCREMENT,
    `batch_no`      bigint(20)     NOT NULL COMMENT '批次号',
    `cons_no`       varchar(16)    NOT NULL COMMENT '商户唯一id',
    `meter_id`      bigint(16)     NOT NULL COMMENT '电表唯一标识',
    `data_date`     datetime       NOT NULL COMMENT '数据日期',
    `org_no`        varchar(32)    NOT NULL COMMENT '数据日期',
    `col_time`      datetime       NOT NULL COMMENT '终端抄表时间',
    `pap_r`         decimal(11, 4) NOT NULL COMMENT '正向有功总电能示值',
    `pap_r1`        decimal(11, 4) COMMENT '正向有功费率1电能示值',
    `pap_r2`        decimal(11, 4) COMMENT '正向有功费率2电能示值',
    `pap_r3`        decimal(11, 4) COMMENT '正向有功费率3电能示值',
    `pap_r4`        decimal(11, 4) COMMENT '正向有功费率4电能示值',
    `pap_r5`        decimal(11, 4) COMMENT '正向有功费率5电能示值',
    `pap_r6`        decimal(11, 4) COMMENT '正向有功费率6电能示值',
    `pap_r7`        decimal(11, 4) COMMENT '正向有功费率7电能示值',
    `pap_r8`        decimal(11, 4) COMMENT '正向有功费率8电能示值',
    `pap_r9`        decimal(11, 4) COMMENT '正向有功费率9电能示值',
    `pap_r10`       decimal(11, 4) COMMENT '正向有功费率10电能示值',
    `pap_r11`       decimal(11, 4) COMMENT '正向有功费率11电能示值',
    `pap_r12`       decimal(11, 4) COMMENT '正向有功费率12电能示值',
    `pap_r13`       decimal(11, 4) COMMENT '正向有功费率13电能示值',
    `prp_r`         decimal(11, 4) COMMENT '反向无功费率总电能示值',
    `prp_r1`        decimal(11, 4) COMMENT '反向无功费率总1电能示值',
    `prp_r2`        decimal(11, 4) COMMENT '反向无功费率总2电能示值',
    `prp_r3`        decimal(11, 4) COMMENT '反向无功费率总3电能示值',
    `prp_r4`        decimal(11, 4) COMMENT '反向无功费率总4电能示值',
    `prp_r5`        decimal(11, 4) COMMENT '反向无功费率总5电能示值',
    `prp_r6`        decimal(11, 4) COMMENT '反向无功费率总6电能示值',
    `prp_r7`        decimal(11, 4) COMMENT '反向无功费率总7电能示值',
    `prp_r8`        decimal(11, 4) COMMENT '反向无功费率总8电能示值',
    `prp_r9`        decimal(11, 4) COMMENT '反向无功费率总9电能示值',
    `prp_r10`       decimal(11, 4) COMMENT '反向无功费率总10电能示值',
    `prp_r11`       decimal(11, 4) COMMENT '反向无功费率总11电能示值',
    `prp_r12`       decimal(11, 4) COMMENT '反向无功费率总12电能示值',
    `prp_r13`       decimal(11, 4) COMMENT '反向无功费率总13电能示值',
    `prp_r14`       decimal(11, 4) COMMENT '反向无功费率总14电能示值',
    `rp1_r`         decimal(11, 4) COMMENT '一象限无功电能示值',
    `rp2_r`         decimal(11, 4) COMMENT '二象限无功电能示值',
    `rp3_r`         decimal(11, 4) COMMENT '三象限无功电能示值',
    `rp4_r`         decimal(11, 4) COMMENT '四象限无功电能示值',
    `data_res_flag` bigint(3) COMMENT '',
    `crc`           decimal(20, 4) COMMENT '',
    `create_time`   timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='日冻结示数';

ALTER TABLE `airport_middle_db`.`t_day_electricity_freeze`
    ADD INDEX `freeze_batch_no`(`batch_no`) USING BTREE COMMENT 'batch_no唯一索引';

CREATE TABLE `t_day_money_residue`
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
    `create_time`  timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='用户剩余金额表';

ALTER TABLE `airport_middle_db`.`t_day_money_residue`
    ADD INDEX `residue_batch_no`(`batch_no`) USING BTREE COMMENT 'batch_no唯一索引';

CREATE TABLE `t_day_bill`
(
    `id`             bigint(20)     NOT NULL AUTO_INCREMENT,
    `batch_no`       bigint(20)     NOT NULL COMMENT '批次号',
    `purchase_id`    varchar(50)    NOT NULL COMMENT '购电单标识（购电模块产生的ID）',
    `purchase_date`  datetime       NOT NULL COMMENT '购电时间',
    `cons_no`        varchar(16)    NOT NULL COMMENT '商户唯一id',
    `meter_id`       bigint(16)     NOT NULL COMMENT '电表号',
    `charge_id`      varchar(50)    NOT NULL COMMENT '微信产生的购电单号',
    `purchase_count` int(11)        NOT NULl COMMENT '购电次数',
    `purchase_money` decimal(18, 2) NOT NULL COMMENT '购电金额',
    `addition_money` decimal(18, 2) NOT NULL COMMENT '补加扣减',
    `init_money`     decimal(18, 2) NOT NULL COMMENT '预制金额',
    `send_money`     decimal(18, 2) NOT NULL COMMENT '下发金额',
    `send_date`      datetime       NOT NULL COMMENT '下发时间',
    `create_time`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='对账单';

ALTER TABLE `airport_middle_db`.`t_day_bill`
    ADD INDEX `bill_batch_no`(`batch_no`) USING BTREE COMMENT 'batch_no唯一索引';

CREATE TABLE `t_day_trans_flag`
(
    `id`         bigint(20)  NOT NULL AUTO_INCREMENT,
    `data_item`  varchar(20) NOT NULL COMMENT '数据项日冻结：freeze、商户余额：residue、对账单：bill',
    `data_date`  datetime    NOT NULL COMMENT '数据时间',
    `batch_no`   bigint(20)  NOT NULL COMMENT '批次号',
    `input_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据写入时间',
    `read_time`  datetime COMMENT '数据写入时间',
    `read_flag`  char(1)     NOT NULL DEFAULT '0' COMMENT '读取标识 0 1',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT ='中间标志库 ';

ALTER TABLE `airport_middle_db`.`t_day_trans_flag`
    ADD UNIQUE INDEX `trans_flag_batch_no`(`batch_no`) USING BTREE COMMENT 'batch_no唯一索引';