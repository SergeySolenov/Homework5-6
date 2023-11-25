package data;

public enum GenderData implements IData{
    MALE("m"),
    FEMALE("f");
    private final String name;

    GenderData(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
