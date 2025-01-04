package net.careerboard.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user_account", schema = "career_board_schema")
@Getter
@Setter
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long userId;
    @Column(unique = true, nullable = false, length = 30, name = "username")
    @NotBlank(message = "Username is required and cannot be empty")
    @Size(min = 4, max = 30)
    String username;

    @Column(nullable = false, name = "password")
    @NotBlank(message = "Password is required and cannot be empty")
    String password;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is required and cannot be empty")
    @Size(max = 30)
    String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is required and cannot be empty")
    @Size(max = 30)
    String lastName;
    @Column(nullable = false, updatable = false, name = "created_at")
    LocalDateTime createdAt;
    @Column(nullable = false, name = "active")
    Boolean active;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Post> posts;
    public User(){
        this.active = true; // default value
        this.createdAt = LocalDateTime.now(); // default value
    }
    public User(String username, String firstName, String lastName) {
        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
