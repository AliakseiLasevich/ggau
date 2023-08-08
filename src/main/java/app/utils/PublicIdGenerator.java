package app.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class PublicIdGenerator {

    public static final String generatePublicId(){
        return UUID.randomUUID().toString();
    }
}
