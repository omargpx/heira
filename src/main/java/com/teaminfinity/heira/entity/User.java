package com.teaminfinity.heira.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TSG_USER")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;
    @Column(name = "no_username")
    private String username;
    @Column(name = "hira_id")
    private String hiraId;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Like> likes;
}
