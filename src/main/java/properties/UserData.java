package properties;

import helper.FakerData;

import java.time.LocalDate;

import static java.time.Month.OCTOBER;

public abstract class UserData {
    public static String FIRST_NAME = FakerData.genFirstName();
    public static final String LAST_NAME = FakerData.genLastName();
    public static final  String TFIRST_NAME = FakerData.translate(FIRST_NAME);
    public final static String TLAST_NAME = FakerData.translate(LAST_NAME);
    public final static String NICK_NAME = FakerData.genNickName();
    public final static LocalDate DATE = LocalDate.of(1999, OCTOBER,30);
    public final static String CONTACT_VK = "https://vk.com/club3774399";
    public final static String CONTACT_TG = "@Faker";
    public final static String JOB_PLACE = "Otus";
    public final static String POSITION = "student";
}
