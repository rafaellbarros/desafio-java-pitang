package br.com.rafaellbarros.core.model.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 4883719057030812437L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private String login;

    @ToString.Exclude
    @Column(nullable = false)
    private String password;

    @Column
    private String phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Car> cars;


    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    public User(@NotNull User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
    }

    public List<Car> getCars() {
        if (this.cars == null) {
            this.cars = new ArrayList<>();
        }
        return this.cars;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
