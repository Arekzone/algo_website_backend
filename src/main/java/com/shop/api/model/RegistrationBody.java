package com.shop.api.model;


import jakarta.validation.constraints.*;

public class RegistrationBody {

    /** The username. */

    @NotNull
    @NotBlank

    private String username;
    /** The email. */
    @Email
    @NotNull
    @NotBlank
    private String email;
    /** The password. */
    @NotNull
    @NotBlank

//    @Pattern(regexp ="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    private String password;
    /** The first name. */

    @NotNull
    @NotBlank
    private String firstName;
    /** The last name. */
    @NotNull
    @NotBlank
    private String lastName;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}