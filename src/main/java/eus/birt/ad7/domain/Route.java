package eus.birt.ad7.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "key.route", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Ascend> ascends = new HashSet<>();

    @ManyToOne
    @JsonIncludeProperties({"id", "name"})
    private Crag crag;

    @Enumerated(EnumType.STRING)
    private Grade baseGrade;

    @Formula("SELECT COUNT(a.climber_id) FROM ascend a WHERE a.route_id=id")
    private int numberOfAscends;

    /**
     * Gets the average consensus grade for the climb
     * @return the consensus grade or null if no ascends
     */
    public Grade getConsensusGrade() {
        if (ascends.isEmpty()) {
            return baseGrade;
        }
        int gradeAverage = ascends.stream().map(Ascend::getGrade).mapToInt(Enum::ordinal).sum() / ascends.size();
        return Grade.fromValue(gradeAverage);
    }
}
