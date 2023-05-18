package dev.bookstore.library.cityCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.bookstore.library.city.City;
import jakarta.persistence.*;

@Entity
@Table(name = "city_code")
public class CityCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_code_id")
    private Long id;

    @Column
    private String cityCode;


    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private City city;

    public CityCode() {
    }

    public CityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        if(cityCode.matches("[0-9]{2}-[0-9]{3}")) {
            this.cityCode = cityCode;
        } else {
            throw new IllegalArgumentException("City code must be in format XX-XXX");
        }
    }

}
