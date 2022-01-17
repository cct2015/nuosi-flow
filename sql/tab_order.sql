create table TAB_ORDER
(
   ORDER_ID              int(20) not null auto_increment comment '订单标识',
   ACCEPT_MONTH           int(2) comment '受理月份',
   ORDER_TYPE_CODE      varchar(256) comment '订单类型',
   USER_ID              bigint(20) not null  comment '用户标识',
   ORDER_state          varchar(4) comment '订单状态',
   ORDER_FEE           int(8) comment '订单金额',
   ORDER_OP_ID           varchar(100) comment '受理员工',
   ORDER_ORG_ID           varchar(100) comment '受理部门',
   VALID_DATE           datetime comment '生效日期',
   EXPIRE_DATE          datetime comment '失效日期',
   primary key (ORDER_ID)
);