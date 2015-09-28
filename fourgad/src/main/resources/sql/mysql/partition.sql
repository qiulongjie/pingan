
-- 创建分区表 
CREATE TABLE ad_req_log_partition (
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	channel VARCHAR(20) NOT NULL,
	ipaddr VARCHAR(15) NOT NULL,
	prov VARCHAR(30) NULL DEFAULT NULL,
	vtime VARCHAR(20) NULL DEFAULT NULL,
	vmonth INT(6) NULL DEFAULT NULL,
	ua VARCHAR(40) NULL DEFAULT NULL,
	vstr1 VARCHAR(20) NULL DEFAULT NULL,
	PRIMARY KEY (id,vmonth)
)
COLLATE='utf8_general_ci'
ENGINE=MyISAM
PARTITION BY RANGE (vmonth) (
    PARTITION p201506 VALUES LESS THAN (201507),
    PARTITION p201507 VALUES LESS THAN (201508),
    PARTITION p201508 VALUES LESS THAN (201509),
	PARTITION p201509 VALUES LESS THAN (201510),
	PARTITION p201510 VALUES LESS THAN (201511)
);

-- call add_range_partition_pro('ad_req_log_partition','month');

-- 删除存储过程
-- drop procedure add_range_partition_pro

-- 创建存储过程（根据表名和分区的类型（日分区还是月分区）添加范围分区）
DELIMITER //
create procedure add_range_partition_pro(IN vi_table_name varchar(30),IN vi_partition_type varchar(10) )
begin
 -- 分区名称
 declare par_name varchar(10);
 -- 分区范围值
 declare par_value varchar(10);
 -- 下个分区值
 declare par_next varchar(10);
 -- 该分区总数
 declare par_count int default 0;
 -- 执行分区的sql
 declare exe_sql varchar(10);
 
  -- 日分区
 if strcmp(vi_partition_type,'day') = 0 then 
  -- 获取下一天日期
   select date_format(date_add(curdate(),interval 1 day),'%Y%m%d') into par_next;
    -- 获取下下天日期
   select date_format(date_add(curdate(),interval 2 day),'%Y%m%d') into par_value;
 end if;
 -- 月分区
 if strcmp(vi_partition_type,'month') = 0 then 
  -- 获取下月份
  select date_format(date_add(curdate(),interval 1 month),'%Y%m') into par_next;
  -- 获取下下月份
  select date_format(date_add(curdate(),interval 2 month),'%Y%m') into par_value;
 end if;
 
  set par_name = concat('p',par_next);
  
  -- 查询是否已有该分区
  select count(1) into par_count 
  from INFORMATION_SCHEMA.partitions 
  where TABLE_SCHEMA = schema() 
    and TABLE_NAME=vi_table_name
    and partition_name=par_name;  
  -- 如果没有就添加该分区
  if par_count = 0 then
   set @sql = concat('alter table ',vi_table_name,' add partition (PARTITION ',par_name,' values less than (',par_value,'))');
   prepare exe_sql from @sql;
   execute exe_sql;
  end if;
end;
//
DELIMITER ;

-- 删除任务
-- drop event add_par_ad_req_log_partition_month
-- 创建任务  设定从当月的最后一天开始，每月的最后一天1点执行
DELIMITER //
create event add_par_ad_req_log_partition_month
on schedule every 1 month starts date_add(last_day(curdate()),interval 1 hour)
on completion preserve enable
do
begin 
call add_range_partition_pro('ad_req_log_partition','month');
end;
//
DELIMITER ;