package app.converters;

import app.entity.Interfaces.EntityInterface;
import app.entity.Interfaces.RequestInterface;
import app.entity.Interfaces.ResponseInterface;

public interface AbstractMapper<T extends EntityInterface, I extends RequestInterface, J extends ResponseInterface> {

    T requestToEntity(I request);

    J entityToResponse(T entity);


}
