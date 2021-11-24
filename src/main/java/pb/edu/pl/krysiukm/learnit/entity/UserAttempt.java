package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserAttempt {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @JoinColumn(name = "USER_ATTEMPT_ID")
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("date asc")
    private Set<HistoryEntry> historyEntries;

    @JsonIgnore
    @ManyToOne
    private UserAccount userAccount;

    @JsonIgnore
    @ManyToMany(mappedBy = "userAttempts", cascade = CascadeType.ALL)
    private List<Technology> technologies;

    @Column(nullable = false)
    private Instant startDate;

    @Nullable
    private Instant endDate;

    @OneToOne(cascade = CascadeType.REMOVE)
    private ShowedQuestion showedQuestion;

    public UserAttempt(UserAccount userAccount, List<Technology> technologies) {
        this.userAccount = userAccount;
        this.technologies = technologies;
    }
}
