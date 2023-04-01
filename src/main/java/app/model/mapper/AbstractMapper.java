package app.model.mapper;

import app.model.Interfaces.EntityInterface;
import app.model.Interfaces.RequestInterface;
import app.model.Interfaces.ResponseInterface;

public interface AbstractMapper<T extends EntityInterface, I extends RequestInterface, J extends ResponseInterface> {

    T requestToEntity(I request);

    J entityToResponse(T entity);


}
