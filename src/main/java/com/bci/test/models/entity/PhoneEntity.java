package com.bci.test.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PhoneEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;
    @Column(name = "number", nullable = false, length = 20)
    private String number;
    @Column(name = "citycode", nullable = false, length = 10)
    private String citycode;
    @Column(name = "contrycode", nullable = false, length = 10)
    private String contrycode;
    @ManyToOne
    @JoinColumn(name="idUsuario", nullable = false, insertable = false, updatable = false)
    private UserEntity user;
}
