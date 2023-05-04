package com.github.cuidok.intellij.platform.plugin.apipanel.services;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.util.List;

public class AnnotationSearcher {

    private final Project project;

    public AnnotationSearcher(Project project) {
        this.project = project;
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
        List<PsiFile> psiFiles = new java.util.ArrayList<>();

        for (VirtualFile virtualFile : sourceRoots) {
            PsiDirectory psiDir = psiManager.findDirectory(virtualFile);
            if (psiDir != null) {
                addPsiFiles(psiDir, psiFiles);
            }
        }

        for (PsiFile psiFile : psiFiles) {
            System.out.println(psiFile.getName());
        }
    }

    /**
     * Scan all Java files in the project
     * <p>
     * Add @RestController annotation to the List
     * Add @RequestMapping annotation to the List
     * Add @GetMapping annotation to the List
     * Add @PostMapping annotation to the List
     * Add @PutMapping annotation to the List
     * Add @DeleteMapping annotation to the List
     * Add @PatchMapping annotation to the List
     *
     * @param psiDir project directory
     * @param psiFiles list of Java files
     */
    private void addPsiFiles(PsiDirectory psiDir, List<PsiFile> psiFiles) {
        for (PsiFile psiFile : psiDir.getFiles()) {
            if (psiFile.getName().endsWith(".java")) {
                psiFiles.add(psiFile);
            }
        }
        for (PsiDirectory subDir : psiDir.getSubdirectories()) {
            addPsiFiles(subDir, psiFiles);
        }
    }

}
