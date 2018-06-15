package com.rupak.sample.platform.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rupak
 */
public enum UserType {
    Admin("A"),
    Reporter("R");

    private UserType(String code) {
        this.code = code;
    }

    private final String code;

    public String getCode() {
        return code;
    }

    public static UserType getByCode(String code) {
        for (UserType userType : UserType.values()) {
            if (userType.getCode().equals(code)) {
                return userType;
            }
        }
        return null;
    }

    public static List<UserType> getByCode(String[] types) {
        List<UserType> userTypes = new ArrayList<>();
        UserType type = null;
        for (String typ : types) {
            type = getByCode(typ);
            if (type != null) {
                userTypes.add(type);
            }
        }
        return userTypes;
    }

    public static List<UserType> getUserTypes() {
        return Arrays.asList(UserType.values());
    }
}

