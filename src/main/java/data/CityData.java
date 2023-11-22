package data;

public enum CityData implements IData {
    ASTRAKHAN("Астрахань");
    private final String name;

    CityData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
