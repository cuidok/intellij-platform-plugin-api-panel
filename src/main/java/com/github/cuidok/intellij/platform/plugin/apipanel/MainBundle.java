package com.github.cuidok.intellij.platform.plugin.apipanel;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.PropertyKey;

public class MainBundle extends DynamicBundle {

    private static final String BUNDLE = "messages.MyBundle";
    public static final MainBundle INSTANCE = new MainBundle();

    private MainBundle() {
        super(BUNDLE);
    }

    public String message(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return getMessage(key, params);
    }

    public String messagePointer(@PropertyKey(resourceBundle = BUNDLE) String key, Object... params) {
        return getLazyMessage(key, params).get();
    }
}
