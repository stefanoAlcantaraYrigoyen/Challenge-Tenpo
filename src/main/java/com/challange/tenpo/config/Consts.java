package com.challange.tenpo.config;

public class Consts {

    /* Exception Handler constants */
    public static final String SERVER_ERROR = "Server Error";
    public static final String RECORD_NOT_FOUND = "Record Not Found";
    public static final String USER_ALREADY_EXISTS = "User Already Exists";
    public static final String DATA_VALIDATION_FAILED = "Data Validation Failed";
    public static final String BAD_CREDENTIALS = "Bad credentials";
    public static final String NEGATIVE_PARAM_EXCEPTION = "Negative param";
    public static final String MISSING_PARAMETERS = "Missing parameters";
    public static final String INVALID_PARAMETER_TYPE = "Invalid parameter type";
    public static final String USER_IS_ALREADY_REGISTERED_EXCEPTION = "User is already registered: ";
    public static final String EMAIL_IS_ALREADY_REGISTERED_EXCEPTION = "Email user is already registered: ";
    public static final String API_PORCENTAGE_NOT_FOUND = "The procentage given by the external api does not exist";
    
    /* Auth constants */
    public static final long ACCESS_TOKEN_EXPIRATION = 1000000;
    public static final String ROLES = "roles";
    public static final String SECRET_KEY = "jwt.secret.key";
    public static final String TOKEN = "Token: ";
    public static final String ENTRY_POINT = "entry-point";
    public static final String USER_IS_NOT_ACTIVE_EXCEPTION = "User is not active";
    public static final String CREDENTIALS_ARE_NOT_VALID_EXCEPTION = "Credentials are not valid";
    public static final String MESSAGE_UNAUTHORIZED_REQUEST = "{ \"message\": \"Unauthorized request\","
            + "\"details\": [\"You need to login first.\"]}";

    /* User data validate messages constants */
    public static final String USERNAME_NOT_NULL = "Username may not be null";
    public static final String USERNAME_NOT_EMPTY = "Username may not be empty";
    public static final String USERNAME_INVALID_SIZE = "Invalid username size";
    public static final String PASSWORD_NOT_NULL = "Password may not be null";
    public static final String PASSWORD_NOT_EMPTY = "Password may not be empty";
    public static final String PASSWORD_INVALID_SIZE = "Invalid password size";
    public static final String EMAIL_INVALID = "Invalid mail";
    public static final String EMAIL_NOT_NULL = "Email may not be null";
    public static final String EMAIL_NOT_EMPTY = "Email may not be empty";

    /* Swagger config constants */
    public static final String BASE_PACKAGE = "com.challange.tenpo.controllers";
    public static final String TITLE = "Challange Tenpo API";
    public static final String DESCRIPTION = "Api Rest en SpringBoot";
    public static final String VERSION = "1.0";
    public static final String TERMS_OF_SERVICE_URL = "";
    public static final String CONTACT_NAME = "Stefano Alcantara";
    public static final String CONTACT_URL = "";
    public static final String CONTACT_MAIL = "stefanoyrigoyen@gmail.com";
    public static final String LICENSE_URL = "https://docs.spring.io/spring-boot/docs/1.4.1.RELEASE/maven-plugin/license.html";
    public static final String LICENSE = "LICENSE";

}
