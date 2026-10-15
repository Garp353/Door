import java.time.LocalDateTime;

public class Log extends CodeHandler {

    public Log(CodeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleCode(String code, Door door) {
        String isLocked = door.isLocked() ? "is locked, " : "is unlocked, ";
        System.out.println("[LOG] Door " + door.getId() + " " + isLocked
                + "entered code: " + code + " at " + LocalDateTime.now());

        super.handleCode(code, door);
    }
}