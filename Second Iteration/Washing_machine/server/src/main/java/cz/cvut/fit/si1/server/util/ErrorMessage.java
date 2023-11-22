package cz.cvut.fit.si1.server.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ErrorMessage {
    STUDENT_NOT_FOUND("Student not found", CodeLevel.NOT_FOUND),
    WM_NOT_FOUND("Washing machine not found", CodeLevel.NOT_FOUND),
    KEY_NOT_FOUND("Key not found", CodeLevel.NOT_FOUND),
    SR_NOT_FOUND("Swap request not found", CodeLevel.NOT_FOUND),
    RESERVATION_NOT_FOUND("Reservation not found", CodeLevel.NOT_FOUND),
    BUILDING_NOT_FOUND("Building not found", CodeLevel.NOT_FOUND),
    ROOM_NOT_FOUND("Room not found", CodeLevel.NOT_FOUND),
    NOT_NULL_VARIABLES("There are some null variables", CodeLevel.NOT_NULL),
    NOT_LIVE_IN_DORM("Student is not living in dorm", CodeLevel.NOT_FOUND),
    INVALID_NUMBER_OF_ROOMS("Student has more than one open room or not have room", CodeLevel.ERROR),
    UNKNOWN_ERROR("Unknown error", CodeLevel.ERROR),
    KEY_IN_STUDENT("Key is held by another student", CodeLevel.CONFLICT);

    private final String message;
    private final CodeLevel codeLevel;

    ErrorMessage(String message, CodeLevel codeLevel) {
        this.message = message;
        this.codeLevel = codeLevel;
    }

    public String getMessage() {
        return message;
    }

    public CodeLevel getCodeLevel() {
        return codeLevel;
    }

    public static List<String> getErrorMessageList() {
        return Stream.of(ErrorMessage.values()).map(ErrorMessage::getMessage).collect(Collectors.toList());
    }
}
