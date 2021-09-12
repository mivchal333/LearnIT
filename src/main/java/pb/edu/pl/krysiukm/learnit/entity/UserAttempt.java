package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class UserAttempt {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HistoryEntry> historyEntries;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToOne
    private Technology technology;

    private Instant startDate;

    private Instant endDate;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    public UserAttempt(User user, Technology technology, GameType gameType) {
        this.user = user;
        this.technology = technology;
        this.gameType = gameType;
    }

    public enum GameType {
        QUIZ,
        CARDS
    }
}
