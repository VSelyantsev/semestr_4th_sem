create table t_log_entity(
    id uuid not null,
    execution_date date not null,
    user_email varchar(60) not null,
    method_name varchar(60) not null,
    severity varchar(20) not null,
    user_id uuid not null,
    foreign key (user_id) references t_user(id)
)