package app.model.mapper;

public interface AbstractMapper<T, I, J> {

    T requestToEntity(I request);

    J entityToResponse(T entity);


}
