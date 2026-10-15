public class Lock extends CodeHandler {
    private static final int MAX_TRIALS = 3;

    public Lock(CodeHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleCode(String code, Door door) {
        if (!door.isLocked()) {
            door.incrementNumTrials();
            System.out.println(door.getNumTrials() + " trials");
            if (door.getNumTrials() >= MAX_TRIALS) {
                door.lock();
                System.out.println("door " + door.getId() + " is now locked");
            }
        }
        super.handleCode(code, door);
    }
}