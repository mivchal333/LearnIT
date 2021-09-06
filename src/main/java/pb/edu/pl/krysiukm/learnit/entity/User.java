package pb.edu.pl.krysiukm.learnit.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AbstractEntity {
    private String username;
    private String password;
    private String mail;
    @OneToMany
    @JoinColumn
    private Set<Role> roles;
}
