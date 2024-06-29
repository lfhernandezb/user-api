package com.example.userapi.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phone")
    private long phoneId;
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;

    @JsonIgnoreProperties("user")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user") //, insertable = false, updatable = false)
    private User user;


    public long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(long phoneId) {
        this.phoneId = phoneId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Phone{" +
                "phoneId=" + phoneId +
                ", number='" + number + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
