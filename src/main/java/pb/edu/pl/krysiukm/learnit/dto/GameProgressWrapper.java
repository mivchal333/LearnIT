package pb.edu.pl.krysiukm.learnit.dto;

import lombok.Getter;
import pb.edu.pl.krysiukm.learnit.model.ProgressWrapper;

@Getter
public final class GameProgressWrapper<T> {
    private final int total;
    private final int actual;
    private final T entry;

    public GameProgressWrapper(ProgressWrapper<T> progressWrapper) {
        this.entry = progressWrapper.getEntry();
        this.actual = progressWrapper.getActual();
        this.total = progressWrapper.getTotal();
    }

    public GameProgressWrapper(ProgressWrapper<?> progress, T entry) {
        this.entry = entry;
        this.actual = progress.getActual();
        this.total = progress.getTotal();
    }
}
