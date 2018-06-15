package com.rupak.sample.platform.common.security;

import com.google.common.hash.Hashing;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author rupak
 */
public class SecurityUtils {

    public static final int KEY_SIZE = 16;
    public static final String DEFAULT_KEY = "1234567890asbcvgstjrjijhKJHSDf";

    private SecurityUtils() {
    }

    public static String encodeString(String plainText) {
        return Hashing.sha256().hashString(plainText, StandardCharsets.UTF_8).toString();
    }

    public static boolean isEqualEncoding(String encodedText, String plainText) {
        if(StringUtils.isBlank(encodedText) || StringUtils.isBlank(plainText))
            return false;
        String encodedTextOfPlanText = encodeString(plainText);
        return encodedText.equals(encodedTextOfPlanText);
    }
    
}
