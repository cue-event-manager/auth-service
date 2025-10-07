package cue.edu.co.seeder.seeders;

import cue.edu.co.model.role.Role;
import cue.edu.co.model.role.constants.RoleConstant;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.seeder.constant.RoleSeederLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        log.info(RoleSeederLog.STARTING.getMessage());

        for (RoleConstant roleConstant : RoleConstant.values()) {
            roleRepository.findByName(roleConstant.getName())
                    .ifPresentOrElse(
                            role -> log.info(RoleSeederLog.ROLE_FOUND.getMessage(), roleConstant.getName()),
                            () -> {
                                Role newRole = Role.builder()
                                        .name(roleConstant.getName())
                                        .description(roleConstant.getDescription())
                                        .build();

                                roleRepository.save(newRole);
                                log.info(RoleSeederLog.ROLE_CREATED.getMessage(), roleConstant.getName());
                            }
                    );
        }

        log.info(RoleSeederLog.FINISHED.getMessage());
    }
}