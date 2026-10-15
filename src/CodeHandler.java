public abstract class CodeHandler {
    protected CodeHandler nextHandler;
    public CodeHandler(CodeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    public void handleCode(String code, Door door) {
        if (nextHandler != null) {
            nextHandler.handleCode(code, door);
        } else {
            System.out.println("reached end of chain");
        }
    }
}