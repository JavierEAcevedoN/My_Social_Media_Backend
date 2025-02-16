package c3.msmb.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "56fdg435fsd32";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Contraseña encriptada: " + encodedPassword);
    }
}