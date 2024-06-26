package br.com.rafaellbarros.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_car")
public class Car extends BaseEntity<Long> {

    private static final long serialVersionUID = 5460748267904999209L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The field 'year' is mandatory")
    @Column(nullable = false)
    private Long year;

    @NotNull(message = "The field 'licensePlate' is mandatory")
    @Column(nullable = false)
    private String licensePlate;

    @NotNull(message = "The field 'model' is mandatory")
    @Column(nullable = false)
    private String model;

    @NotNull(message = "The field 'color' is mandatory")
    @Column(nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
}
