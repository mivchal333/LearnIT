package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Data;

@Data
public abstract class GameProgressWrapper {
    private int total;
    private int actual;
}
