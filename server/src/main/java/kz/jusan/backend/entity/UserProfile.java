package kz.jusan.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profile")
@Builder
public class UserProfile {
    @Id
    @Column(
            name = "iin",
            unique = true,
            nullable = false
    )
    private String iin;
    private String fio;
    private String mobilePhone;
    private String email;
    private String factualCity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "anketa",
            referencedColumnName = "iin"
    )
    @JsonIgnore
    private AnketaEntity anketa;

    @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity userEntity;
}