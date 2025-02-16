package org.patcher.utility;

public interface Constants {
    enum STRING_SEPARATOR {
        DOT("."),
        PATH_SEPARATOR_FOWARD_SLASH("/"),
        PATH_SEPARATOR_BACK_SLASH("\\"),
        PATH_SEPARATOR_BACK_SLASH_ESCAPE("\\\\"),
        SPACE(" "),
        DASH("-"),
        COMMA(",");

        private final String value;

        STRING_SEPARATOR(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum PLACE_HOLDER_CONSTANTS {
        VALUE_NOT_FOUND("PLACE_HOLDER_VALUE_NOT_FOUND"),
        PROPERTY_PREFIX("prop.");
        private final String value;
        PLACE_HOLDER_CONSTANTS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum MESSAGES{
        PATCH_NAME_INPUT_MESSAGE("Enter patch name"),
        DEFAULT_PATCH_CREATION_FAILED_MESSAGE("Patch can not be created, check logs"),
        PATCH_VERIFIED_SUCCESS_MESSAGE("Patch is verified successfully"),
        PATCH_VERIFY_FAILED_MESSAGE("Patch not is verified"),

        PATCH_APPLY_SUCCESS_MESSAGE("Patch is applied successfully"),
        PATCH_APPLY_FAIL_MESSAGE("Patch is not applied"),
        PATCH_APPLY_FAIL_EXCEPTION_MESSAGE("Patch can not be applied, check logs"),
        PATCH_VERIFY_FAILED_EXCEPTION_MESSAGE("Patch can not be verified, check logs"),
        DEFAULT_PATCH_CREATED_MESSAGE("Patch is created successfully");
        private final String value;
        MESSAGES(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum FILE_CONSTANTS {
        TTF_FILE_TYPE(".ttf"),
        JAR_FILE_TYPE(".jar"),
        ZIP_FILE_TYPE(".zip");
        private final String value;
        FILE_CONSTANTS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum FONT_EXTENSION_CONSTANTS {
        PROPERTIES_FILE("jasperreports_extension.properties"),
        FONTS_XML("fonts.xml");
        private final String value;
        FONT_EXTENSION_CONSTANTS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
