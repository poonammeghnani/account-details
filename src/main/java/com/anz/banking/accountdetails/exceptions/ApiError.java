package com.anz.banking.accountdetails.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Error object to be returned when APIException occurring
 */
public class ApiError {

        private final String apiVersion;
        private final ErrorBlock error;

        public ApiError(final String apiVersion, final String code, final String message, final String timestamp, final String path) {
            this.apiVersion = apiVersion;
            this.error = new ErrorBlock(code, message, timestamp, path);
        }

        public static ApiError fromDefaultAttributeMap(final String apiVersion,
                                                                final Map<String, Object> defaultErrorAttributes
                                                       ) {
            return new ApiError(
                    apiVersion,
                    ((Integer) defaultErrorAttributes.get("status")).toString(),
                    (String) defaultErrorAttributes.getOrDefault("message", "no message available"),
                    (String) defaultErrorAttributes.getOrDefault("timestamp", new Date().toString()),
                    (String) defaultErrorAttributes.getOrDefault("path", "no path available")
            );
        }

        public Map<String, Object> toAttributeMap() {
            Map map = new HashMap();
            map.put("apiVersion", apiVersion);
            map.put("error", error);
            return map;
        }

        public String getApiVersion() {
            return apiVersion;
        }

        public ErrorBlock getError() {
            return error;
        }

        private static final class ErrorBlock {

            @JsonIgnore
            private final UUID uniqueId;
            private final String code;
            private final String message;
            private final String timestamp;
            private final String path;

            public ErrorBlock(final String code, final String message, final String timestamp, final String path) {
                this.code = code;
                this.message = message;
                this.uniqueId = UUID.randomUUID();
                this.timestamp = timestamp;
                this.path = path;
            }

            private ErrorBlock(final UUID uniqueId, final String code, final String message, final String timestamp, final String path) {
                this.uniqueId = uniqueId;
                this.code = code;
                this.message = message;
                this.timestamp = timestamp;
                this.path = path;
            }

            public UUID getUniqueId() {
                return uniqueId;
            }

            public String getCode() {
                return code;
            }

            public String getMessage() {
                return message;
            }

            public String getTimestamp() { return timestamp; }

            public String getPath() { return path; }
        }

    }