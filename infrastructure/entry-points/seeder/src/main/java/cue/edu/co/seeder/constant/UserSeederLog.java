package cue.edu.co.seeder.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserSeederLog {
    STARTING("Starting user seeder..."),
    ADMIN_FOUND("Admin user already exists: {}"),
    ADMIN_CREATED("Admin user created successfully: {}"),
    ROLE_NOT_FOUND("Role ADMIN not found. Please run RoleSeeder first."),
    ERROR("Error while running UserSeeder: {}"),
    FINISHED("User seeder finished.");

    private final String message;
}