package cue.edu.co.seeder.seeders;

import cue.edu.co.model.role.Role;
import cue.edu.co.model.role.constants.RoleConstant;
import cue.edu.co.model.role.gateways.RoleRepository;
import cue.edu.co.model.security.gateways.PasswordEncoder;
import cue.edu.co.model.user.User;
import cue.edu.co.model.user.gateways.UserRepository;
import cue.edu.co.seeder.constant.UserSeederLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seeder.admin.email}")
    private String adminEmail;

    @Value("${app.seeder.admin.password}")
    private String adminPassword;

    @Value("${app.seeder.admin.first-name:Admin}")
    private String adminFirstName;

    @Value("${app.seeder.admin.last-name:System}")
    private String adminLastName;

    @Override
    public void run(String... args) {
        log.info(UserSeederLog.STARTING.getMessage());

        try {
            Optional<User> existingAdmin = userRepository.findByEmail(adminEmail);
            if (existingAdmin.isPresent()) {
                log.info(UserSeederLog.ADMIN_FOUND.getMessage(), adminEmail);
                return;
            }

            Optional<Role> adminRole = roleRepository.findByName(RoleConstant.ADMIN.getName());
            if (adminRole.isEmpty()) {
                log.error(UserSeederLog.ROLE_NOT_FOUND.getMessage());
                return;
            }

            User adminUser = User.builder()
                    .firstName(adminFirstName)
                    .lastName(adminLastName)
                    .email(adminEmail)
                    .password(passwordEncoder.encode(adminPassword))
                    .role(adminRole.get())
                    .build();

            userRepository.save(adminUser);
            log.info(UserSeederLog.ADMIN_CREATED.getMessage(), adminEmail);

        } catch (Exception e) {
            log.error(UserSeederLog.ERROR.getMessage(), e.getMessage());
        }

        log.info(UserSeederLog.FINISHED.getMessage());
    }
}