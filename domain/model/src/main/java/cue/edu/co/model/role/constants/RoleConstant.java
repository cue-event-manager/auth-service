package cue.edu.co.model.role.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleConstant {
    ADMIN("ADMIN", "Administrador del sistema"),
    ORGANIZER("ORGANIZER", "Organizador de eventos"),
    ATTENDEE("ATTENDEE", "Asistente a eventos");

    private final String name;
    private final String description;
}
