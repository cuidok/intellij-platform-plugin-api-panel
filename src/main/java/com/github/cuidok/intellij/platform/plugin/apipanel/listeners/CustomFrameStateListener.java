package com.github.cuidok.intellij.platform.plugin.apipanel.listeners;

import com.intellij.ide.FrameStateListener;
import com.intellij.openapi.diagnostic.Logger;

public class CustomFrameStateListener implements FrameStateListener {
    @Override
    public void onFrameActivated() {
        Logger.getInstance(CustomFrameStateListener.class)
                .warn("Java API Panel: onFrameActivated");
    }
}
