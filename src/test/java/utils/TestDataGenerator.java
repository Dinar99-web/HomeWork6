package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataGenerator {
    private final Faker faker = new Faker(new Locale("en"));

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String getDayOfMonth() {
        return String.valueOf(faker.number().numberBetween(1, 28));
    }

    public String getMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public String getYear() {
        return String.valueOf(faker.number().numberBetween(1900, 2025));
    }

    public String getSubject() {
        return faker.options().option("Maths", "Chemistry", "Computer Science",
                "Commerce", "Economics", "English", "Social Studies");
    }

    public String getHobby() {
        return faker.options().option("Sports", "Reading", "Music");
    }

    public String getPictureName() {
        return "test.jpg";
    }

    public String getAddress() {
        return faker.address().fullAddress();
    }

    public String getState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getCity(String state) {
        switch (state) {
            case "NCR": return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh": return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana": return faker.options().option("Karnal", "Panipat");
            case "Rajasthan": return faker.options().option("Jaipur", "Jaiselmer");
            default: return "";
        }
    }
}
