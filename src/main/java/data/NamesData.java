package data;

public enum NamesData implements IData{
    FNAME("fname"),
    LNAME("lname"),
    FNAME_LATIN("fname_latin"),
    LNAME_LATIN("lname_latin"),
    BLOG_NAME("blog_name"),
    GENDER ("gender");

    private final String name;

    NamesData(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
