package com.example.demo.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_FOUND(2001,"您找的帖子不存在或已被删除"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务冒烟了，请稍后再来吧"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"您回复的评论不存在或已被删除"),
    CONTENT_IS_EMPTY(2007,"输入的内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"不可以偷看别人的信息"),
    NOTIFICATION_NOT_FOUND(2009,"您找的通知不存在或已被删除");

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
