package com.fms.enums.entity_enums;

public enum CommentTypeEnum {
    A("A"),
    B("B"),
    C("C");
    private final String commentType;
    

    CommentTypeEnum(String ct) {
        this.commentType = ct;
    }
    public String getCommentType() {
        return this.commentType;
    }
}
