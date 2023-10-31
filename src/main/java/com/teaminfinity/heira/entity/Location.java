package com.teaminfinity.heira.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tax_locations")
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_location")
    private Integer id;
    @Column(name = "val_lat")
    private String latitude;
    @Column(name = "val_long")
    private String longitude;

    @JsonIgnore
    @OneToOne(mappedBy = "start", cascade = CascadeType.MERGE)
    private Route startRoute;

    @JsonIgnore
    @OneToOne(mappedBy = "end", cascade = CascadeType.MERGE)
    private Route endRoute;
}
