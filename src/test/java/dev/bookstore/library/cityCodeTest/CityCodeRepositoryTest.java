package dev.bookstore.library.cityCodeTest;

import dev.bookstore.library.city.City;
import dev.bookstore.library.city.CityRepository;
import dev.bookstore.library.cityCode.CityCode;
import dev.bookstore.library.cityCode.CityCodeRepository;
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
public class CityCodeRepositoryTest {

    @Autowired
    private CityCodeRepository cityCodeRepository;

    @Autowired
    private CityRepository cityRepository;

    private CityCode cityCode;
    private City city;

    @BeforeEach
    public void setUp() {
        city = new City();
        city.setName("Test city");
        cityRepository.save(city);

        cityCode = new CityCode();
        cityCode.setCityCode("12-345");
        cityCode.setCity(city);
        city.addCityCode(cityCode);

        cityCodeRepository.save(cityCode);
        cityRepository.save(city);
    }

    @Test
    public void shouldFindCityCodeByCityCode() {
        CityCode foundCityCode = cityCodeRepository.findByCityCode(cityCode.getCityCode());

        assertNotNull(foundCityCode);
        assertThat(foundCityCode.getCityCode(), equalTo(cityCode.getCityCode()));
    }

    @Test
    public void shouldFindCityCodeByName() {
        CityCode foundCityCode = cityCodeRepository.findByCityName(city.getName());

        assertNotNull(foundCityCode);
        assertThat(foundCityCode.getCityCode(), equalTo(cityCode.getCityCode()));
    }

}
