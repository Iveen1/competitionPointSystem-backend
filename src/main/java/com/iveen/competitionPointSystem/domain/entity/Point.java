package com.iveen.competitionPointSystem.domain.entity;
import lombok.*;

import javax.persistence.*;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:06
 * @project competitionPointSystem
 */

@Entity
@Getter
@Setter
@Table(name = "points", uniqueConstraints={
        @UniqueConstraint(columnNames={"task_id", "participant_id"})
})
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double coefficient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "participant_id")
    private Participant participant;
}
