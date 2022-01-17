create table TAB_USER
(
   USER_ID              int(20) not null auto_increment comment '用户标识',
   USER_NAME            varchar(256) comment '用户名称',
   GENDER               varchar(2) comment '性别',
   user_state           varchar(4) comment '用户状态',
   EMAIL                varchar(100) comment '邮箱',
   LINK_PHONE           varchar(100) comment '手机号码',
   PASSWORD             varchar(100) comment '密码',
   ADMIN_FLAG           int(1) comment '是否为ADMIN账号',
   VALID_DATE           datetime comment '生效日期',
   EXPIRE_DATE          datetime comment '失效日期',
   TENANT_CODE          varchar(32) comment '租户编码',
   primary key (USER_ID)
);