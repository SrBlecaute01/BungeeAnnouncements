package br.com.blecaute.announcement.exception;

public class InvalidAnnouncementException extends RuntimeException {

    public InvalidAnnouncementException() {
        super();
    }

    public InvalidAnnouncementException(String message) {
        super(message);
    }

    public InvalidAnnouncementException(String message, Throwable cause) {
        super(message, cause);
    }


}
