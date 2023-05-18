

public class Instruction {
    private String op;
    private int issue;
    private int execute;
    private int writeBack;
    private int startExecute;
    private int type;

    public Instruction(String op, int issue, int execute, int writeBack, int startExecute, int type) {
        this.op = op;
        this.issue = issue;
        this.execute = execute;
        this.writeBack = writeBack;
        this.startExecute = startExecute;
        this.type = type;
    }

    // Getters and setters for the properties
    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public int getExecute() {
        return execute;
    }

    public void setExecute(int execute) {
        this.execute = execute;
    }

    public int getWriteBack() {
        return writeBack;
    }

    public void setWriteBack(int writeBack) {
        this.writeBack = writeBack;
    }

    public int getStartExecute() {
        return startExecute;
    }

    public void setStartExecute(int startExecute) {
        this.startExecute = startExecute;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
