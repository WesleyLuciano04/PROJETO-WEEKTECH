package com.faculdade.techweek.web.Admin.Controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/curriculos")
public class FileController {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @GetMapping("/{nomeArquivo}")
    public ResponseEntity<Resource> baixarCurriculo(@PathVariable String nomeArquivo) {
        try {
            Path caminho = Paths.get(uploadDir).resolve(nomeArquivo).normalize();
            Resource resource = new UrlResource(caminho.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + nomeArquivo + "\"")
                    .body(resource);

        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}