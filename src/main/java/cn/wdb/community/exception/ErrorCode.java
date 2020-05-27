package cn.wdb.community.exception;

public enum ErrorCode implements IErrorCode{
    QUESTION_NOT_FOUND("你找的问题不见了，请换一个");
    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
