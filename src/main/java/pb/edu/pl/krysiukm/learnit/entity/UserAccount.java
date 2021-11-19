package pb.edu.pl.krysiukm.learnit.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "USER_ACCOUNT")
@Getter
@Setter
public class UserAccount {

    @Id
    private String email;

    private String firstName;
    private String lastName;

    @Column(length = 60)
    private String password;

    @ColumnDefault("0")
    private long points;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES", joinColumns = @JoinColumn(name = "USER_EMAIL", referencedColumnName = "EMAIL"), inverseJoinColumns = @JoinColumn(name = "ROLE_NAME", referencedColumnName = "NAME"))
    private Collection<Role> roles;

}