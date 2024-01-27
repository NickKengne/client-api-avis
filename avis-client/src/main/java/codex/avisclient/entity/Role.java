package codex.avisclient.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private TypeRole role;

    public Role(TypeRole role) {
        this.role = role;
    }




    public enum TypeRole {
        USER,
        ADMIN,
        SUPER_ADMIN
    }

}
