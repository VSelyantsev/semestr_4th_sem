create table t_blog(
    id uuid not null,
    title varchar(60) not null,
    content text not null,
    user_id uuid UNIQUE,
    foreign key (user_id) references t_user(id)
)