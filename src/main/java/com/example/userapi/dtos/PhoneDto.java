package com.example.userapi.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDto {
    @NotEmpty(message = "Se requiere el numero de telefono.")
    @Pattern(regexp = "^\\d{4,16}$", flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE }, message = "El numero de telefomo no es valido.")
    private String number;
    @NotEmpty(message = "Se requiere el codigo de la ciudad.")
    @Pattern(regexp = "^\\d{1,5}$", flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE }, message = "El codigo de la ciudad no es valido.")
    private String cityCode;
    @NotEmpty(message = "Se requiere el codigo del pais.")
    @Pattern(regexp = "^\\d{1,5}$", flags = { Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE }, message = "El codigo del pais no es valido.")
    private String countryCode;

    @JsonIgnoreProperties("phones")
    private RegisterUserDto registerUserDto;

    public @NotEmpty(message = "Se requiere el numero de telefono.") @Pattern(regexp = "^\\d{4,16}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "El numero de telefomo no es valido.") String getNumber() {
        return number;
    }

    public void setNumber(@NotEmpty(message = "Se requiere el numero de telefono.") @Pattern(regexp = "^\\d{4,16}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "El numero de telefomo no es valido.") String number) {
        this.number = number;
    }

    public @NotEmpty(message = "Se requiere el codigo de la ciudad.") @Pattern(regexp = "^\\d{1,5}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "El codigo de la ciudad no es valido.") String getCityCode() {
        return cityCode;
    }

    public void setCityCode(@NotEmpty(message = "Se requiere el codigo de la ciudad.") @Pattern(regexp = "^\\d{1,5}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "El codigo de la ciudad no es valido.") String cityCode) {
        this.cityCode = cityCode;
    }

    public @NotEmpty(message = "Se requiere el codigo del pais.") @Pattern(regexp = "^\\d{1,5}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "El codigo del pais no es valido.") String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(@NotEmpty(message = "Se requiere el codigo del pais.") @Pattern(regexp = "^\\d{1,5}$", flags = {Pattern.Flag.CASE_INSENSITIVE, Pattern.Flag.MULTILINE}, message = "El codigo del pais no es valido.") String countryCode) {
        this.countryCode = countryCode;
    }

    public RegisterUserDto getRegisterUserDto() {
        return registerUserDto;
    }

    public void setRegisterUserDto(RegisterUserDto registerUserDto) {
        this.registerUserDto = registerUserDto;
    }

    @Override
    public String toString() {
        return "PhoneDto{" +
                "number='" + number + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", registerUserDto=" + registerUserDto +
                '}';
    }
}
