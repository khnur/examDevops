package kz.jusan.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@Builder
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;
    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEntity> userEntities = new ArrayList<>();

}