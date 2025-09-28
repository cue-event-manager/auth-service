package cue.edu.co.seeder.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleSeederLog {
    STARTING("Starting role seeder..."),
    ROLE_FOUND("Role already exists: {}"),
    ROLE_CREATED("Role created: {}"),
    FINISHED("Role seeder finished.");

    private final String message;
}