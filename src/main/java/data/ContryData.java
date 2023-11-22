package data;

public enum ContryData implements IData {
    RUSSIA("Россия");
    private final String name;

    ContryData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
