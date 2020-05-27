package cn.wdb.community.exception;

public class ExceptionMessage extends RuntimeException{
    private String message;

    public ExceptionMessage(IErrorCode errorCode)        {
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
