package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class HashSecure {

    // Générer un sel (salt) aléatoire
    public String generateSalt() {
        byte[] salt = new byte[16];  // Longueur du sel, 16 octets ici
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt); // Convertir en chaîne Base64
    }

    // Hacher le mot de passe avec le sel
    public String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes()); // Ajouter le sel au hachage
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error password hashed!", e);
        }
    }

    // Vérifier si le mot de passe correspond après hachage
    public boolean checkPassword(String enteredPassword, String storedPasswordHash, String storedSalt) {
        String enteredPasswordHash = hashPassword(enteredPassword, storedSalt);
        return enteredPasswordHash.equals(storedPasswordHash);
    }
}
