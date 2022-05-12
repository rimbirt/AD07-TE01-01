package eus.birt.ad7.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eus.birt.ad7.repository.AscendRepository;
import eus.birt.ad7.repository.RouteRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Slf4j
@DataJpaTest
class AscendKeyTest {

    @Autowired
    private AscendRepository ascendRepository;

    @Test
    void testSerializationOther() throws JsonProcessingException {
        Ascend ascend = ascendRepository.findAll().stream().findFirst().
            orElse(new Ascend());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(ascend);
        Ascend ascend1 = mapper.readValue(json, Ascend.class);
        log.info(json);
    }

    @Test
    void testValidation() {
        Ascend ascend = Ascend.builder().key(AscendKey.builder().route(Route.builder().id(2L).build()).build()).build();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Ascend>> violations = validator.validate(ascend);
        assertThat(violations).hasSize(1);
    }

}
