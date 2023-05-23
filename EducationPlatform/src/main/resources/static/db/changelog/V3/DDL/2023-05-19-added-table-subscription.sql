create table t_subscription(
    id uuid primary key,
    user_id uuid not null,
    friend_id uuid not null,
    status varchar(20) not null,
    foreign key (user_id) references t_user(id),
    foreign key (friend_id) references t_user(id)
)