package data;

public enum EnglishLevelData implements IData{
    BEGINNER("Начальный уровень (Beginner)");
    private final String name;

    EnglishLevelData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
