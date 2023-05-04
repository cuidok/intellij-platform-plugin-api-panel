package com.github.cuidok.intellij.platform.plugin.apipanel.services;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;

import java.util.List;

public class AnnotationSearcher {

    private final Logger logger;

    private final Project project;

    private final List<PsiFile> controllerClass;

    public AnnotationSearcher(Project project) {
        this.logger = Logger.getInstance(AnnotationSearcher.class);
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
                            controllerClass.add(psiFile);
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
     * @param psiJavaFile item of controllerClass
     */
    private void parseApiInterface(PsiJavaFile psiJavaFile) {

    }

}
