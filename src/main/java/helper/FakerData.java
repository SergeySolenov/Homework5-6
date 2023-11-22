package helper;

import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FakerData {
    private static Faker faker;

    public static String genFirstName() {
        faker = new Faker(new Locale("ru"));
        return faker.name().firstName();
    }
    public static String genNickName() {
        faker = new Faker(new Locale("en"));
        return faker.name().firstName();
    }

    public static String genLastName() {
        faker = new Faker(new Locale("ru"));
        return faker.name().lastName();
    }
    public static String translate(String rus) {
        Transliterator toLatinTrans = Transliterator.getInstance("Russian-Latin/BGN");
        return toLatinTrans.transliterate(rus);
    }
    public static String genDate() {
        faker = new Faker();
        DateFormat date = new SimpleDateFormat("dd.MM.yyyy");
        return date.format(faker.date().birthday(18, 60));
    }

}