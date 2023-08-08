package app.utils;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class PublicIdGenerator {

    public static String generatePublicId() {
        return UUID.randomUUID().toString();
    }
}
