package com.cristian.project.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "cases")
@AllArgsConstructor()
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-dd-mm")
    private String date;
    private Long total;
    @Column(name = "new")
    private Long news;

    @JoinColumn(name = "country_id")
    private Long countryId;


}
