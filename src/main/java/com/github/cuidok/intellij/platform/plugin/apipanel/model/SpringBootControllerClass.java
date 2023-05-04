package com.github.cuidok.intellij.platform.plugin.apipanel.model;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiFile;

public class SpringBootControllerClass {

    private PsiFile controllerClass;

    private PsiAnnotation restControllerAnnotation;

    public SpringBootControllerClass(PsiFile controllerClass, PsiAnnotation restControllerAnnotation) {
        this.controllerClass = controllerClass;
        this.restControllerAnnotation = restControllerAnnotation;
    }

    public PsiFile getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(PsiFile controllerClass) {
        this.controllerClass = controllerClass;
    }

    public PsiAnnotation getRestControllerAnnotation() {
        return restControllerAnnotation;
    }

    public void setRestControllerAnnotation(PsiAnnotation restControllerAnnotation) {
        this.restControllerAnnotation = restControllerAnnotation;
    }
}
