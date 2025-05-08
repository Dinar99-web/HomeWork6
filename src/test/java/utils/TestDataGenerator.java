package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataGenerator {
    private static final Faker faker = new Faker(new Locale("en"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public static String getDayOfMonth() {
        return String.valueOf(faker.number().numberBetween(1, 28));
    }

    public static String getMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public static String getYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2025));
    }

    public static String getSubject() {
        return faker.options().option("Maths", "Physics", "Chemistry", "English", "Computer Science");
    }

    public static String getHobby() {
        return faker.options().option("Reading", "Sports", "Music");
    }

    public static String getPictureName() {
        return "test.jpg";
    }

    public static String getAddress() {
        return faker.address().fullAddress();
    }

    public static String getState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String getCity(String state) {
        switch (state) {
            case "NCR": return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh": return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana": return faker.options().option("Karnal", "Panipat");
            case "Rajasthan": return faker.options().option("Jaipur", "Jaiselmer");
            default: return "";
        }
    }
}
