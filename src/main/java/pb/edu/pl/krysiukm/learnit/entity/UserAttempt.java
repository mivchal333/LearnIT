package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.Instant;
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Technology technology;

    @Column(nullable = false)
    private Instant startDate;

    @Nullable
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    @OneToOne(cascade = CascadeType.REMOVE)
    private ShowedQuestion showedQuestion;

    public UserAttempt(UserAccount userAccount, Technology technology, GameType gameType) {
        this.userAccount = userAccount;
        this.technology = technology;
        this.gameType = gameType;
    }

    public enum GameType {
        QUIZ,
        CARDS
    }
}
