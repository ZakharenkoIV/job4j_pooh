package ru.job4j.pooh;

public class Req {
    private final String method;
    private final String mode;
    private final String name;
    private final String text;
    private final String clientId;

    private Req(String method, String mode, String name, String text, String clientId) {
        this.method = method;
        this.mode = mode;
        this.name = name;
        this.text = text;
        this.clientId = clientId;
    }

    public static Req of(String content) {
        String[] firstLine = content.split("\\R", 1)[0].split("[\\s]");
        String method = firstLine.length > 0 ? firstLine[0] : "null";
        String[] path = firstLine.length > 1 ? firstLine[1].split("/") : null;
        String mode = (path != null && path.length > 1) ? path[1] : "null";
        String name = (path != null && path.length > 2) ? path[2] : "null";
        String clientId = (path != null && path.length > 3) ? path[3] : "null";
        String[] betweenEmptyLines = content.split("(?m)^$");
        String[] textBody = (betweenEmptyLines.length > 1)
                ? betweenEmptyLines[1].split("\\R", 2)
                : null;
        String text = (textBody != null && textBody.length > 0)
                ? (textBody[1].equals("") ? "null" : textBody[1])
                : "null";
        return new Req(method, mode, name, text, clientId);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String name() {
        return name;
    }

    public String text() {
        return text;
    }

    public String clientId() {
        return clientId;
    }
}