alter table users add is_active tinyint;
update users set is_active =1;