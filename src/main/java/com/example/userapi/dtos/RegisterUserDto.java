package com.example.userapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDto implements UserDetails {

    //@Value("${validation.password}")
    //private String passwordPattern;

    private UUID userId;
    @NotEmpty(message = "Se requiere el nombre.")
    @Size(min = 2, max = 64, message = "El largo del nombre debe estar entre 2 y 64 caracteress.")
    private String name;
    @NotEmpty(message = "Se requiere el email.")
    @Email(message = "El email no es valido.",
            flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;
    @NotEmpty(message = "Se requiere la contrasenia.")
    @Size(min = 8, max = 16, message = "El largo de la contrasenia debe estar entre 8 y 16 caracteres.")
    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE }, message = "The password must have minimum eight characters, at least one letter and one number.")
    private String password;

    @Valid
    @JsonIgnoreProperties("registerUserDto")
    @NotEmpty(message = "Se requiere al menos un telefono.")
    private List<PhoneDto> phones;
    private String token;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        //return UserDetails.super.isEnabled();
        return isActive;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "Se requiere el nombre.") @Size(min = 2, max = 64, message = "The length of name must be between 2 and 64 characters.") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Se requiere el nombre.") @Size(min = 2, max = 64, message = "The length of name must be between 2 and 64 characters.") String name) {
        this.name = name;
    }

    public @NotEmpty(message = "Se requiere el email.") @Email(message = "El email no es validod.",
            flags = {Pattern.Flag.CASE_INSENSITIVE}) String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Se requiere el email.") @Email(message = "El email no es valido.",
            flags = {Pattern.Flag.CASE_INSENSITIVE}) String email) {
        this.email = email;
    }

    @Override
    public @NotEmpty(message = "Se requiere la contrasenia.") @Size(min = 8, max = 16, message = "El largo de la contrasenia debe ser entre 8 y 16 caracteres.") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Se requiere la contrasenia.") @Size(min = 8, max = 16, message = "El largo de la contrasenia debe ser entre 8 y 16 caracteres.") String password) {
        this.password = password;
    }

    public @NotEmpty(message = "Se requiere al menos un telefono.") List<PhoneDto> getPhones() {
        return phones;
    }

    public void setPhones(@NotEmpty(message = "Se requiere al menos un telefono.") List<PhoneDto> phones) {
        this.phones = phones;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        isActive = isActive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phones=" + phones +
                ", token='" + token + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", lastLogin=" + lastLogin +
                ", isActive=" + isActive +
                '}';
    }
}
