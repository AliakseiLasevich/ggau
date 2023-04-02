package app.common;

import app.model.entity.Building;
import app.model.entity.Cabinet;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Random;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object obj) {
        String prefix = ""; // default prefix
        if (obj instanceof Building) {
            prefix = "BLD";
        } else if (obj instanceof Cabinet) {
            prefix = "CBN";
        } // add more cases for other entities

        String id;
        boolean unique = false;
        while (!unique) {
            id = generateRandomId(prefix);
            unique = isIdUnique(session, obj.getClass().getName(), id);
            if (unique) {
                return id;
            }
        }
        return null;
    }

    private String generateRandomId(String prefix) {
        int length = 20 - prefix.length();
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
