package com.github.cuidok.intellij.platform.plugin.apipanel.toolWindow;

import com.github.cuidok.intellij.platform.plugin.apipanel.MyBundle;
import com.github.cuidok.intellij.platform.plugin.apipanel.services.AnnotationSearcher;
import com.github.cuidok.intellij.platform.plugin.apipanel.services.RandomNumberService;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.JButton;
import javax.swing.JComponent;


public class MainWindowFactory implements ToolWindowFactory {
    private final ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        final MyToolWindow myToolWindow = new MyToolWindow(toolWindow, project);
        final Content content = contentFactory.createContent(myToolWindow.getContent(), null, false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public boolean shouldBeAvailable(Project project) {
        return true;
    }

    static class MyToolWindow {

        private final RandomNumberService service;
        private final JButton shuffleButton;
        private final JBLabel label;

        private Project project;

        public MyToolWindow(final ToolWindow toolWindow, Project project) {
            this.project = project;
            service = ServiceManager.getService(toolWindow.getProject(), RandomNumberService.class);

            Logger.getInstance(this.getClass()).warn("Java API Panel: MyToolWindow");

            label = new JBLabel(MyBundle.message("randomLabel", "?"));
            shuffleButton = new JButton(MyBundle.message("shuffle"));
            shuffleButton.addActionListener(e -> {
                label.setText(MyBundle.message("randomLabel", service.getRandomNumber()));
                new AnnotationSearcher(this.project).search();
            });
        }

        public JComponent getContent() {
            final JBPanel<JBPanel<?>> panel = new JBPanel<>();
            panel.add(label);
            panel.add(shuffleButton);
            return panel;
        }
    }
}
