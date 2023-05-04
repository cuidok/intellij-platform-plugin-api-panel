package com.github.cuidok.intellij.platform.plugin.apipanel.services;

import com.github.cuidok.intellij.platform.plugin.apipanel.model.SpringBootControllerClass;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

import java.util.List;

public class AnnotationSearcher {

    private final Project project;

    private final List<SpringBootControllerClass> controllerClass;

    public AnnotationSearcher(Project project) {
        this.project = project;
        this.controllerClass = new java.util.ArrayList<>(10);
    }

    /**
     * search all @RestController annotations in project
     * <p>
     * identify all @RequestMapping annotations in @RestController
     * identify all @GetMapping annotations in @RestController
     * identify all @PostMapping annotations in @RestController
     * identify all @PutMapping annotations in @RestController
     * identify all @DeleteMapping annotations in @RestController
     * identify all @PatchMapping annotations in @RestController
     *
     */
    public void search() {
        PsiManager psiManager = PsiManager.getInstance(project);
        VirtualFile[] sourceRoots = ProjectRootManager.getInstance(project).getContentRoots();

        for (VirtualFile virtualFile : sourceRoots) {
            PsiDirectory psiDir = psiManager.findDirectory(virtualFile);
            if (psiDir != null) {
                parseControllerClass(psiDir);
            }
        }

        for (SpringBootControllerClass controllerClass : controllerClass) {
            parseApiInterface(controllerClass);
        }
    }

    /**
     * Scan all controller Java files in the project
     * <p>
     * Add @RestController annotation to the List
     *
     * @param psiDir project directory
     */
    private void parseControllerClass(PsiDirectory psiDir) {
        for (PsiFile psiFile : psiDir.getFiles()) {
            if (psiFile.getName().endsWith(".java")) {
                PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
                for (PsiClass psiClass : psiJavaFile.getClasses()) {
                    for (PsiAnnotation annotation : psiClass.getAnnotations()) {
                        String qualifiedName = annotation.getQualifiedName();
                        if ("org.springframework.web.bind.annotation.RestController".equals(qualifiedName)) {
                            controllerClass.add(new SpringBootControllerClass(psiFile, annotation));
                        }
                    }
                }
            }
        }
        for (PsiDirectory subDir : psiDir.getSubdirectories()) {
            parseControllerClass(subDir);
        }
    }

    /**
     * identify all @RequestMapping annotations in @RestController
     * identify all @GetMapping annotations in @RestController
     * identify all @PostMapping annotations in @RestController
     * identify all @PutMapping annotations in @RestController
     * identify all @DeleteMapping annotations in @RestController
     * identify all @PatchMapping annotations in @RestController
     *
     * @param controllerClass item of controllerClass
     */
    private void parseApiInterface(SpringBootControllerClass controllerClass) {
        String url_root = controllerClass.getRestControllerValue();
        PsiFile psiFile = controllerClass.getControllerClass();
        PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
        for (PsiClass psiClass : psiJavaFile.getClasses()) {
            for (PsiMethod psiMethod : psiClass.getMethods()) {
                for (PsiAnnotation annotation : psiMethod.getAnnotations()) {
                    String qualifiedName = annotation.getQualifiedName();
                    if (qualifiedName != null) {
                        switch (qualifiedName) {
                            case "org.springframework.web.bind.annotation.RequestMapping":
                            case "org.springframework.web.bind.annotation.GetMapping":
                            case "org.springframework.web.bind.annotation.PostMapping":
                            case "org.springframework.web.bind.annotation.PutMapping":
                            case "org.springframework.web.bind.annotation.DeleteMapping":
                            case "org.springframework.web.bind.annotation.PatchMapping":
                                String url = annotation.findAttributeValue("value").getText();
                                url = url.substring(1, url.length() - 1);
                                System.out.println(url_root + url);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

}
