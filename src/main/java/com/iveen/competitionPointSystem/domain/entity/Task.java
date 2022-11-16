package com.iveen.competitionPointSystem.domain.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

/**
 * @author Polyakov Anton
 * @created 08.11.2022 18:40
 * @project competitionPointSystem
 */

@Entity
@Getter
@Setter
@Table(name = "tasks", uniqueConstraints={
        @UniqueConstraint(columnNames={"title"})
})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String title;

    private Long maxPoints;
}
