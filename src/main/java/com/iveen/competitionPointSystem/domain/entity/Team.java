package com.iveen.competitionPointSystem.domain.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 18:38
 * @project competitionPointSystem
 */

@Entity
@Getter
@Setter
@Table(name = "teams", uniqueConstraints={
        @UniqueConstraint(columnNames={"name"})
})
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "participant_team",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<Participant> participants;

    public Double getTotalPoints() {
        Double totalPoints = participants.stream().map(x -> { return x.getTotalPoints(); }).mapToDouble(Double::valueOf).sum();

        if (totalPoints > 0) {
            return totalPoints / participants.size();
        }
        return 0.0;
    }
}
