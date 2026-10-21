public class FireAlarm extends CodeHandler {
    private final String codeFireAlarm;

    public FireAlarm(String code, CodeHandler nextHandler) {
        super(nextHandler);
        this.codeFireAlarm = code;
    }

    @Override
    public void handleCode(String code, Door door) {
        System.out.println("handleFireAlarm");
        if (!door.isLocked()) {
            if (codeFireAlarm.equals(code)) {
                door.resetState();
                System.out.println("ALARM FIRED!");
                door.open();
            } else {
                // It should not increment the number of trials
                //door.incrementNumTrials();
                System.out.println(door.getNumTrials() + " trials");
                super.handleCode(code, door);
            }
        } else {
            super.handleCode(code, door);
        }
    }
}