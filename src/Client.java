import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) throws IOException {
        String openCode = "1111";
        String fireAlarmCode = "2222";
        String unlockCode = "3333";

        // Order of the chain: Unlock -> FireAlarm -> Open -> Lock
        CodeHandler chain1 =
                new Log(new Unlock(unlockCode,
                        new FireAlarm(fireAlarmCode,
                                new Open(openCode,
                                        new Lock(null)))));
        // Order of the chain: Log -> Open
        CodeHandler chain2 = new Log(new Open(openCode, null));
        // Order of the chain: Log -> FireAlarm -> Open
        CodeHandler chain3 =
                new Log(
                        new FireAlarm(fireAlarmCode,
                                new Open(openCode, null)));

        // Create doors with different chains
        Door d1 = new Door("d1", chain1);
        Door d2 = new Door("d2", chain2);
        Door d3 = new Door("d3", chain3);

        System.out.println("--- TESTING d1 (chain1) ---");
        d1.processCode("1111"); // opens
        d1.processCode("2222"); // opens and fires alarm
        d1.processCode("1234"); // first trial
        d1.processCode("4321"); // second trial
        d1.processCode("5555"); // third trial, gets locked
        d1.processCode("6666"); // invalid unlock code
        d1.processCode("7777"); // invalid unlock code
        d1.processCode("1111"); // invalid unlock code
        d1.processCode("3333"); // valid unlock code, now can be opened or fire alarm
        d1.processCode("2222"); // opens and fires alarm

        // Tests for chain2 and chain3
        System.out.println("\n--- TESTING d2 (chain2: Only Open/Log) ---");
        d2.processCode("1111"); // opens
        d2.processCode("9999"); // failed trial
        d2.processCode("8888"); // failed trial
        d2.processCode("7777"); // failed trial (does not lock because it has no Lock handler)

        System.out.println("\n--- TESTING d3 (chain3: Open/FireAlarm/Log) ---");
        d3.processCode("2222"); // opens and fires alarm
        d3.processCode("1111"); // opens normally

        // Change behaviour of door d1 at runtime
        System.out.println("\n--- INTERACTIVE MODE (for d1) ---");
        System.out.println("Changing d1 behavior to chain2 at runtime.");
        d1.setCodeHandler(chain2);
        d1.resetState();

        System.out.println("Commands: 'exit' to quit, 'set chain1', 'set chain2', 'set chain3'.");

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Input code: ");
            String inputCode = stdin.readLine();

            if (inputCode.equalsIgnoreCase("exit")) {
                break;
            }
            else if (inputCode.equalsIgnoreCase("set chain1")) {
                d1.setCodeHandler(chain1);
                d1.resetState();
                System.out.println(">>> Behavior changed to chain1");
            } else if (inputCode.equalsIgnoreCase("set chain2")) {
                d1.setCodeHandler(chain2);
                d1.resetState();
                System.out.println(">>> Behavior changed to chain2");
            } else if (inputCode.equalsIgnoreCase("set chain3")) {
                d1.setCodeHandler(chain3);
                d1.resetState();
                System.out.println(">>> Behavior changed to chain3");
            } else {
                d1.processCode(inputCode);
            }
        }
        System.out.println("Program finished.");
    }
}