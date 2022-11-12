package com.iveen.competitionPointSystem.domain.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 18:39
 * @project competitionPointSystem
 */

@Entity
@Getter
@Setter
@Table(name = "participants", uniqueConstraints={
        @UniqueConstraint(columnNames={"lastName", "firstName"})
})
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String firstName;

    @Column(length = 40, nullable = false)
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "participant_team",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Team team;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participant")
    private List<Point> points;
}
