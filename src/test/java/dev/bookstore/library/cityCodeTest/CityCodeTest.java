package dev.bookstore.library.cityCodeTest;

import dev.bookstore.library.cityCode.CityCode;
import dev.bookstore.library.cityCode.CityCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CityCodeTest {

    @Autowired
    private CityCodeRepository cityRepository;

    private CityCode cityCode;

    @BeforeEach
    public void setUp() {
        cityCode = new CityCode("00-000");
        cityRepository.save(cityCode);
    }

    @Test
    public void cityCodeCanBeAdded() {
        CityCode savedCityCode = cityRepository.findById(cityCode.getId()).get();
        assertNotNull(savedCityCode);
    }

    @Test
    public void cityCodeCanBeUpdated() {
        CityCode savedCityCode = cityRepository.findById(cityCode.getId()).get();
        cityCode.setCityCode("11-111");
        cityRepository.save(cityCode);

        CityCode updatedCityCode = cityRepository.findById(cityCode.getId()).get();
        assertNotNull(updatedCityCode);
        assertThat(savedCityCode.getId(), equalTo(updatedCityCode.getId()));
    }

    @Test
    public void cityCodeCanBeDeleted() {
        cityRepository.delete(cityCode);
    }

}
