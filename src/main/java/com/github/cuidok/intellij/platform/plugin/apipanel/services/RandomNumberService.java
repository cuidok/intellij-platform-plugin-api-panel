package com.github.cuidok.intellij.platform.plugin.apipanel.services;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.github.cuidok.intellij.platform.plugin.apipanel.MyBundle;

@Service(Service.Level.PROJECT)
public final class RandomNumberService {

    private final Logger logger;

    public RandomNumberService(Project project) {
        this.logger = Logger.getInstance(RandomNumberService.class);
        logger.info(MyBundle.message("projectService", project.getName()));
        logger.warn("Java API Panel: RandomNumberService");
    }

    public int getRandomNumber() {
        return 66;
    }
}
