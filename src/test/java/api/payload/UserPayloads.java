package api.payload;

import java.util.concurrent.ThreadLocalRandom;
import com.github.javafaker.Faker;

/**
 * Static helpers to quickly create User payloads for tests.
 * Provides "method functions" to generate common payloads
 * instead of manually setting fields every time.
 */
public final class UserPayloads {

    private static final Faker FAKER = new Faker();

    private UserPayloads() {}

    /** Fully populated valid user with random username/email */
    public static User newValidUser() {
        String suffix = String.valueOf(System.currentTimeMillis() % 1_000_000);
        return newValidUser(suffix);
    }

    /** Fully populated valid user with controlled suffix */
    public static User newValidUser(String suffix) {
        String base = "user" + suffix;

        long id = ThreadLocalRandom.current().nextLong(1000, 9999999);

        return User.builder()
                .id(id)
                .username(base)
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .email(base + "@example.com")
                .password("Pass@123")
                .phone(String.valueOf(FAKER.number().numberBetween(9000000000L, 9999999999L)))
                .userStatus(1)
                .build();
    }

    /** Minimal user with only username */
    public static User minimalUser(String username) {
        return User.minimal(username);
    }

    /** Copy and update email */
    public static User updateEmail(User existing, String newEmail) {
        User copy = User.copyOf(existing);
        copy.setEmail(newEmail);
        return copy;
    }

    /** Copy and update first + last name */
    public static User updateName(User existing, String newFirst, String newLast) {
        User copy = User.copyOf(existing);
        copy.setFirstName(newFirst);
        copy.setLastName(newLast);
        return copy;
    }
}
