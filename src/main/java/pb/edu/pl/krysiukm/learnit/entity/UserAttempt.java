package pb.edu.pl.krysiukm.learnit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
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

    public UserAttempt(User user, Technology technology) {
        this.user = user;
        this.historyEntries = new ArrayList<>();
        this.technology = technology;
    }
}
