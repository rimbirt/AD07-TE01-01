package eus.birt.ad7.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ascend {

    @EmbeddedId
    @JsonUnwrapped
    @Valid
    private AscendKey key = new AscendKey();

    @Enumerated(EnumType.STRING)
    private Grade grade = Grade.FIVE;

    public void setClimber(Climber climber) {
       key.setClimber(climber);
    }

    @JsonIgnore
    public Climber getClimber() {
        return key.getClimber();
    }

    public void setRoute(Route route) {
        key.setRoute(route);
    }

    @JsonIgnore
    public Route getRoute() {
        return key.getRoute();
    }

}
