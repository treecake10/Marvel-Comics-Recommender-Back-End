
package com.treecake10.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.treecake10.dto.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @ElementCollection
    @Column(name = "user_liked_creators")
    private List<Category> likedCreators = new ArrayList<>();

    @ElementCollection
    @Column(name = "user_liked_events")
    private List<Category> likedEvents = new ArrayList<>();

    @ElementCollection
    @Column(name = "user_liked_characters")
    private List<Category> likedCharacters = new ArrayList<>();

    @ElementCollection
    @Column(name = "user_liked_series")
    private List<Category> likedSeries = new ArrayList<>();

    @ElementCollection
    @Column(name = "user_liked_comics")
    private List<Category> likedComics = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}