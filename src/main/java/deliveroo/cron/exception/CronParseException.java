package deliveroo.cron.exception;

public class CronParseException extends Exception {
    private String msg = "Expression Passed cannot be parsed";

    public CronParseException(Throwable e , String msg){
        this.msg = msg;
    }
    public CronParseException(String msg){
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }

    @Override
    public String toString() {
        return "deliveroo.cron.exception.CronParseException{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
