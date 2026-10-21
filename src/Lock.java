// This class locks the door. Otherwise, it passes the request to the next handler in the chain.
public class Lock extends CodeHandler {
    private static final int MAX_TRIALS = 3;

    public Lock(CodeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleCode(String code, Door door) {
        System.out.println("handleLock");
        System.out.println(door.getNumTrials() + " trials");
        if (!door.isLocked() && door.getNumTrials() >= MAX_TRIALS) {
            door.lock();
            System.out.println("door " + door.getId() + " is now locked");
        } else {
            super.handleCode(code, door);
        }
    }
}