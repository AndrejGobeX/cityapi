package com.cities.citytempapi.utils;

import com.cities.citytempapi.models.User;
import com.cities.citytempapi.services.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.security.MessageDigest;

public class Authorization {
    private static class Tag {
        private final String username;
        private final long timestamp;

        public Tag(String username, long timestamp) {
            this.username = username;
            this.timestamp = timestamp;
        }

        public String getUsername() {
            return username;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
    private static final long expiration = 1000*60*30; //30min
    private static final String MESSAGE_WRONG_CRED = "Wrong username or password.";
    public static final String MESSAGE_INVALID_TOKEN = "Invalid token.";
    private static final Map<String, Tag> registeredTokens = new HashMap<>();

    public static void purge(){
        registeredTokens.entrySet().removeIf(entry ->
                System.currentTimeMillis() - entry.getValue().getTimestamp() > expiration);
    }

    private static String toHexString(byte[] bytes) {
        Formatter result = new Formatter();
        try (result) {
            for (var b : bytes) {
                result.format("%02x", b & 0xff);
            }
            return result.toString();
        }
    }

    public static String login(User user, UserService service){
        purge();
        User existingUser = service.getUser(user.getUsername());

        if(existingUser == null ||
                !existingUser.getPassword().equals(user.getPassword())){
            return MESSAGE_WRONG_CRED;
        }

        String token = String.valueOf(System.currentTimeMillis());

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(token.getBytes());
            token = toHexString(digest.digest());
            registeredTokens.put(token, new Tag(user.getUsername(), System.currentTimeMillis()));
            return token;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return MESSAGE_WRONG_CRED;
    }

    public static boolean authenticate(String token){
        purge();
        try {
            return registeredTokens.containsKey(token);
        }
        catch (NullPointerException e){
            return false;
        }
    }
}
