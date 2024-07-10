package org.studyeasy.SpringBlog.models;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Email(message = "Invalid email")
    @NotEmpty(message = "Email missing")
    private String email;

    @NotEmpty(message = "Password missing")
    private String password;

    @NotEmpty(message = "Firstname missing")
    private String firstname;

    @NotEmpty(message = "Lastname missing")
    private String lastname;

    private String gender;

    @Min(value = 18)
    @Max(value= 99)
    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_of_birth;

    private String photo;

    private String role; 

    @Column(name = "token")
    private String token;

    private LocalDateTime password_reset_token_expiry;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Post> posts;

    @ManyToMany
    @JoinTable(
        name="account_authority",
        joinColumns = {@JoinColumn(name="account_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "Account [id=" + id + ", email=" + email + ", password=" + password + ", firstname=" + firstname
                + ", lastname=" + lastname + ", role=" + role + ", posts=" + posts + ", authorities=" + authorities
                + "]";
    }

    
}
