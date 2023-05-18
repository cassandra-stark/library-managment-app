package dev.bookstore.library.cityTest;

import dev.bookstore.library.city.City;
import dev.bookstore.library.city.CityRepository;
import dev.bookstore.library.cityCode.CityCode;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    private City city;
    private CityCode cityCode;

    @BeforeEach
    public void setUp() {
        city = new City();
        city.setName("Test city");
        cityRepository.save(city);

        cityCode = new CityCode();
        cityCode.setCityCode("12-345");
        cityCode.setCity(city);
        city.addCityCode(cityCode);

        cityRepository.save(city);
    }

    @Test
    public void shouldFindCityByName() {
        City foundCity = cityRepository.findByName(city.getName());

        assertNotNull(foundCity);
        assertThat(foundCity.getName(), equalTo(city.getName()));
    }

}
