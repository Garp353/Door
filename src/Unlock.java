public class Unlock extends CodeHandler {
    private String codeUnlock;

    public Unlock(String code, CodeHandler nextHandler) {
        super(nextHandler);
        this.codeUnlock = code;
    }

    @Override
    public void handleCode(String code, Door door) {
        System.out.println("handleUnlock");
        if (door.isLocked()) {
            if (codeUnlock.equals(code)) {
                door.resetState();
                System.out.println("door " + door.getId() + " unlocked");
            } else {
                System.out.println("invalid unlock code");
                super.handleCode(code, door);
            }
        } else {
            super.handleCode(code, door);
        }
    }
}