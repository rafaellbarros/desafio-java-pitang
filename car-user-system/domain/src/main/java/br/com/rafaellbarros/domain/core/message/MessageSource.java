package br.com.rafaellbarros.domain.core.message;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public final class MessageSource {

    private static final String[] PROPERTIES_MESSAGES = new String[]{"properties/messages", "api/messages"};

    private static final ResourceBundleMessageSource MESSAGE_SOURCE;

    static {
        MESSAGE_SOURCE = new ResourceBundleMessageSource();
        MESSAGE_SOURCE.setBasenames(PROPERTIES_MESSAGES);
        MESSAGE_SOURCE.setDefaultEncoding("UTF-8");
    }

    private static class MessageSourceInit {
        public static final MessageSource INSTANCE = new MessageSource();
    }

    public static MessageSource get() {
        return MessageSourceInit.INSTANCE;
    }

    public String message(final String key) {
        return MESSAGE_SOURCE.getMessage(key, null, Locale.getDefault());
    }

    public String message(final String key, final String... args) {
        return MESSAGE_SOURCE.getMessage(key, args, Locale.getDefault());
    }
}

