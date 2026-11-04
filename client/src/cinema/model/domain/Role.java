package cinema.model.domain;

public enum Role {
    AMMINISTRATORE(1),
    PROPRIETARIO(2),
    CLIENTE(10);

    private final int id;

    private Role(int id) {
        this.id = id;
    }

    public static Role fromInt(int id) {
        for (Role type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
