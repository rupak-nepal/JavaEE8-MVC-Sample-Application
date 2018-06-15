package com.rupak.sample.platform.exceptions;

/**
 *
 * @author rupak
 */
public class SampleException extends RuntimeException {

    private ExceptionType exceptionType;

    public SampleException(ExceptionType type) {
        this.exceptionType = type;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public enum ExceptionType {

        //User
        USER_ALREADY_EXISTS(400, "User already exists."),
        USER_NOT_FOUND(401, "User does not exist."),
        USER_ROLE_NOT_FOUND(402, "User role does not exist."),
        USER_CREDENTIAL_NOT_MATCH(403, "User credential do not match."),
        USER_NEW_PASSWORD_DOES_NOT_MATCHED(440, "User new password not matched."),
        USER_NEW_PASSWORD_MATCHED_OLD_PASSWORD(441, "User new password matched old password.");
       
        private long code;
        private String message;

        ExceptionType(long code, String message) {
            this.code = code;
            this.message = message;
        }

        public long getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

}

