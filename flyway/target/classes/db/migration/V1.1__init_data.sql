insert into  sc_user(user_name,name,age,balance,create_time) values ("account1","张三",20,120,now());
insert into  sc_user(user_name,name,age,balance,create_time) values ("account2","李四",28,150,now());
insert into  sc_user(user_name,name,age,balance,create_time) values ("account3","王五",30,80,now());

INSERT INTO sc_cron(cron,type) VALUES ('0/5 * * * * ?',1);