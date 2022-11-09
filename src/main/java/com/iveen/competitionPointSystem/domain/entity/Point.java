package com.iveen.competitionPointSystem.domain.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 19:06
 * @project competitionPointSystem
 */

@Entity
@Getter
@Setter
@Table(name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double coefficient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_id")
    private Participant participant;
}
