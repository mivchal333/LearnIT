package pb.edu.pl.krysiukm.learnit.dto;

public interface Mapper<E, D> {
    D mapToDto(E entity);

    E mapToEntity(D dto);
}
