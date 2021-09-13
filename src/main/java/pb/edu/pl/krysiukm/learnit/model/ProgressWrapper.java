package pb.edu.pl.krysiukm.learnit.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProgressWrapper<T> {
    private int actual;
    private int total;
    private T entry;
}
