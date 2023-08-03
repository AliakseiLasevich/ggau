package app.common;

import app.model.entity.interfaces.GeneratedId;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Random;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        validateObjHasPrefix(obj);
        String prefix = ((GeneratedId) obj).getPrefix();
        return generateUniqueId(session, obj.getClass().getName(), prefix);
    }

    private void validateObjHasPrefix(Object obj) {
        if (!(obj instanceof GeneratedId)) {
            throw new IllegalStateException("All entities must have id prefix");
        }
    }

    private String generateUniqueId(SharedSessionContractImplementor session, String className, String prefix) {
        String id = generateRandomId(prefix);
        while (!isIdUnique(session, className, id)) {
            id = generateRandomId(prefix);
        }
        return id;
    }

    private String generateRandomId(String prefix) {
        int length = 20 - prefix.length();
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append(prefix);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            builder.append(characters.charAt(index));
        }
        return builder.toString();
    }

    private boolean isIdUnique(SharedSessionContractImplementor session, String entityName, String id) {
        Query<?> query = session.createQuery("SELECT COUNT(*) FROM " + entityName + " WHERE id = :id", Query.class);
        query.setParameter("id", id);
        Long count = (Long) query.uniqueResult();
        return count == 0;
    }

}
