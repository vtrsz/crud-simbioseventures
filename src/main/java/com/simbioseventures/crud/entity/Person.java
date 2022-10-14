package com.simbioseventures.crud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="people")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Person implements Serializable {
    @Serial
    private static final long serialVersionUID = -2602900653737263641L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate birthDate;

}