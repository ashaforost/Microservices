package com.thingscloud.users.repo.model;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
        )
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @Column (name = "user_id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column (name = "last_name")
    private String lastName;

    @Column (name= "email")
    private String email;

    @Column (name = "created_time")
    private long createdTime;

    @JsonProperty( value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String role;

    private int deviceLimit;

    public User( String firstName, String lastName, String email, long currentTimeMillis, String password, String user_role, int device_limit) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        this.id = UUID.randomUUID();
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = passwordEncoder.encode(password);
        this.createdTime = currentTimeMillis;
        this.role = user_role;
        this.deviceLimit = device_limit;
    }

    public int getDeviceLimit() {
        return deviceLimit;
    }

    public void setDeviceLimit(int deviceLimit) {
        this.deviceLimit = deviceLimit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {this.password = password;}

    public long getCreatedTime() {
        return createdTime;
    }

    public UUID getId(){
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }

}