package no.netcompany.sommerjobb2017.secret.models;

public class Secret {
    private final String message;

    public Secret(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
