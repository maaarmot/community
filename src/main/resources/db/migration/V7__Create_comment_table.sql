CREATE TABLE comment
(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    parent_id bigint NOT NULL,
    type int NOT NULL,
    commentator int NOT NULL,
    gmt_create bigint NOT NULL,
    gmt_modified bigint NOT NULL,
    like_count bigint default 0
);
COMMENT ON COLUMN comment.id IS '评论id';
COMMENT ON COLUMN comment.parent_id IS '父类id';
COMMENT ON COLUMN comment.type IS '类型一级或二级';
COMMENT ON COLUMN comment.commentator IS '评论人id';