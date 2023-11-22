package data;

public enum WorkData implements IData{
    COMPANY("company"),
    JOB("work");

    private final String name;


    WorkData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
