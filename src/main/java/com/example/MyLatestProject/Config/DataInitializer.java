package com.example.MyLatestProject.Config;

import com.example.MyLatestProject.entity.AppUser;
import com.example.MyLatestProject.repo.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Runs once on startup and creates the two login users.
 * Uses Spring's PasswordEncoder to hash passwords at runtime —
 * no hard-coded BCrypt strings needed.
 *
 *   admin / admin123  →  ROLE_ADMIN  (all endpoints)
 *   agent / agent123  →  ROLE_AGENT  (GET + register only)
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepo;
    private final PasswordEncoder   passwordEncoder;

    public DataInitializer(AppUserRepository appUserRepo,
                           PasswordEncoder passwordEncoder) {
        this.appUserRepo     = appUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (appUserRepo.count() == 0) {
            appUserRepo.save(new AppUser("admin", passwordEncoder.encode("admin123"), "ADMIN"));
            appUserRepo.save(new AppUser("agent", passwordEncoder.encode("agent123"), "AGENT"));
            System.out.println("========================================");
            System.out.println("  Users seeded:");
            System.out.println("  admin / admin123  (ADMIN - full access)");
            System.out.println("  agent / agent123  (AGENT - read only)");
            System.out.println("========================================");
        }
    }
}
