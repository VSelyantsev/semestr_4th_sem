alter table t_task
alter column expiration_date type TIMESTAMP WITHOUT TIME ZONE;

alter table t_user
add constraint name_constraint UNIQUE(username);

alter table t_user
add constraint email_constraint UNIQUE(email);