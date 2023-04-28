package com.github.cuidok.intellij.platform.plugin.apipanel.toolWindows;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import org.jetbrains.annotations.NotNull;

public class MainWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

    }

    class MainWindow {

        private ToolWindow toolWindow;

        public MainWindow(ToolWindow toolWindow) {
            this.toolWindow = toolWindow;
        }

    }
}
