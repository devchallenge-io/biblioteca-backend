package com.biblioteca.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResponse {

    private String title;
    private int status;
    private String detail;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;


    public static final class MessageResponseBuilder {
        private String title;
        private int status;
        private String detail;
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        private LocalDateTime timestamp;
        private String message;

        private MessageResponseBuilder() {
        }

        public static MessageResponseBuilder newBuilder() {
            return new MessageResponseBuilder();
        }

        public MessageResponseBuilder title(String title) {
            this.title = title;
            return this;
        }

        public MessageResponseBuilder status(int status) {
            this.status = status;
            return this;
        }

        public MessageResponseBuilder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public MessageResponseBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public MessageResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public MessageResponse build() {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.status = this.status;
            messageResponse.message = this.message;
            messageResponse.title = this.title;
            messageResponse.detail = this.detail;
            messageResponse.timestamp = this.timestamp;
            return messageResponse;
        }
    }
}
