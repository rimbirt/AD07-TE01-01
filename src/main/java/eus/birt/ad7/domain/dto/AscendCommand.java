package eus.birt.ad7.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import eus.birt.ad7.domain.Ascend;
import eus.birt.ad7.domain.AscendKey;
import eus.birt.ad7.domain.Climber;
import eus.birt.ad7.domain.Grade;
import eus.birt.ad7.domain.Route;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AscendCommand {

    @NotNull
    private Route route;
    @NotNull
    private Climber climber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Grade grade = Grade.FIVE;

    public Ascend toAscend() {
        return Ascend.builder()
            .key(AscendKey.builder()
                .climber(climber)
                .route(route)
                .build())
            .grade(grade)
            .build();
    }

    public AscendKey getId() {
        return AscendKey.builder()
            .route(route)
            .climber(climber)
            .build();
    }
}
