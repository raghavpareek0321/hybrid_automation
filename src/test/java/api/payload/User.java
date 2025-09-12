package api.payload;

import java.util.Objects;

/**
 * User POJO for PetStore APIs.
 * - Has getters/setters for Jackson serialization
 * - Provides fluent "withX" methods for chaining
 * - Has a static Builder for building test payloads
 * - Factory helpers: minimal(), copyOf(), builder()
 */
public class User {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    // --- Constructors ---
    public User() {}

    public User(Long id, String username, String firstName, String lastName,
                String email, String password, String phone, Integer userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    // --- Builder factory ---
    public static Builder builder() {
        return new Builder();
    }

    // Minimal factory
    public static User minimal(String username) {
        User u = new User();
        u.setUsername(username);
        return u;
    }

    // Copy factory
    public static User copyOf(User other) {
        if (other == null) return null;
        return new User(
            other.id,
            other.username,
            other.firstName,
            other.lastName,
            other.email,
            other.password,
            other.phone,
            other.userStatus
        );
    }

    // --- Getters/Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getUserStatus() { return userStatus; }
    public void setUserStatus(Integer userStatus) { this.userStatus = userStatus; }

    // --- Fluent "withX" helpers ---
    public User withId(Long id) { this.id = id; return this; }
    public User withUsername(String username) { this.username = username; return this; }
    public User withFirstName(String firstName) { this.firstName = firstName; return this; }
    public User withLastName(String lastName) { this.lastName = lastName; return this; }
    public User withEmail(String email) { this.email = email; return this; }
    public User withPassword(String password) { this.password = password; return this; }
    public User withPhone(String phone) { this.phone = phone; return this; }
    public User withUserStatus(Integer userStatus) { this.userStatus = userStatus; return this; }

    // --- Boilerplate ---
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User that = (User) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(username, that.username) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(email, that.email) &&
               Objects.equals(password, that.password) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(userStatus, that.userStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, email, password, phone, userStatus);
    }

    // --- Inner Builder ---
    public static class Builder {
        private final User u = new User();

        public Builder id(Long id) { u.setId(id); return this; }
        public Builder username(String username) { u.setUsername(username); return this; }
        public Builder firstName(String firstName) { u.setFirstName(firstName); return this; }
        public Builder lastName(String lastName) { u.setLastName(lastName); return this; }
        public Builder email(String email) { u.setEmail(email); return this; }
        public Builder password(String password) { u.setPassword(password); return this; }
        public Builder phone(String phone) { u.setPhone(phone); return this; }
        public Builder userStatus(Integer userStatus) { u.setUserStatus(userStatus); return this; }

        public User build() { return u; }
    }
}
