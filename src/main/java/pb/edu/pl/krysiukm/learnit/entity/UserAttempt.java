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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HistoryEntry> historyEntries;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private TechnologyEntity technologyEntity;

    @Column(nullable = false)
    private Instant startDate;

    @Nullable
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    public UserAttempt(User user, TechnologyEntity technologyEntity, GameType gameType) {
        this.user = user;
        this.technologyEntity = technologyEntity;
        this.gameType = gameType;
    }

    public enum GameType {
        QUIZ,
        CARDS
    }
}
