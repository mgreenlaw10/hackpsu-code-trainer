package game;

public final class SyntaxError {
    public final int line;
    public final int column;
    public final String offendingToken;
    public final String message;

    public SyntaxError(int line, int column, String offendingToken, String message) {
        this.line = line;
        this.column = column;
        this.offendingToken = offendingToken;
        this.message = message;
    }

    @Override
    public String toString() {
        String tok = offendingToken == null ? "" : " (token: " + offendingToken + ")";
        return "line " + line + ":" + column + " - " + message + tok;
    }
}