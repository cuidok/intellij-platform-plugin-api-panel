package com.github.cuidok.intellij.platform.plugin.apipanel.model;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiFile;

public class SpringBootControllerClass {

    private PsiFile controller_class;

    private PsiAnnotation rest_controller_annotation;

    public SpringBootControllerClass(PsiFile controller_class, PsiAnnotation rest_controller_annotation) {
        this.controller_class = controller_class;
        this.rest_controller_annotation = rest_controller_annotation;
    }

    public String getRestControllerValue() {
        // if controller class or rest controller annotation is null return empty string
        if (controller_class == null || rest_controller_annotation == null) {
            return "";
        }
        // get value of @RestController annotation, but need to remove ""
        String value = rest_controller_annotation.findAttributeValue("value").getText();
        return value.substring(1, value.length() - 1);
    }

    public PsiFile getControllerClass() {
        return controller_class;
    }

    public void setControllerClass(PsiFile controller_class) {
        this.controller_class = controller_class;
    }

    public PsiAnnotation getRestControllerAnnotation() {
        return rest_controller_annotation;
    }

    public void setRestControllerAnnotation(PsiAnnotation rest_controller_annotation) {
        this.rest_controller_annotation = rest_controller_annotation;
    }
}
