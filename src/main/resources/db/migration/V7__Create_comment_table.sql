create table comment
(
    id bigint auto_increment primary key,
    parent_id bigint not null,
    type int not null,
    commentator int not null,
    gmt_create bigint not null,
    gmt_modified bigint not null,
    like_count bigint default 0
);
comment on column comment.id is '评论id';
comment on column comment.parent_id is '父类id';
comment on column comment.type is '类型一级或二级';
comment on column comment.commentator is '评论人id';