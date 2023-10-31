package com.teaminfinity.heira.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teaminfinity.heira.utils.model.RouteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TMA_ROUTES")
public class Route implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_route")
    private Integer id;
    @Column(name = "tag_environment")
    @Enumerated(EnumType.STRING)
    private RouteType tag;
    @Column(name = "val_noise")
    private double noise;
    @Column(name = "val_airquality")
    private double airquality;
    @Column(name = "val_forestation")
    private double forestation;

    @OneToOne
    @JoinColumn(name = "pt_start")
    private Location start;

    @OneToOne
    @JoinColumn(name = "pt_end")
    private Location end;

    @JsonIgnore
    @OneToMany(mappedBy = "route")
    private List<Like> likeList;

    @Transient
    private int likes;
}
