package dev.bookstore.library.city;

import dev.bookstore.library.address.Address;
import dev.bookstore.library.cityCode.CityCode;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city_name")
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CityCode> codes = new ArrayList<>();


    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City(String name, List<CityCode> codes, List<Address> addresses) {
        this.name = name;
        this.codes = codes;
        this.addresses = addresses;
    }


    public List<CityCode> getCityCode() {
        return codes;
    }

    public void setCityCode(List<CityCode> cityCodes) {
        this.codes = cityCodes;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    public void removeAccount(String cracow) {
        this.name = cracow;
    }

    public void setName(String cracow) {
        this.name = cracow;
    }

    public void getName(String cracow) {
        this.name = cracow;
    }

    public void addCityCode(CityCode cityCode) {
        codes.add(cityCode);
        cityCode.setCity(this);
    }

    public void removeCityCode(CityCode cityCode) {
        codes.remove(cityCode);
        cityCode.setCity(null);
    }
}
