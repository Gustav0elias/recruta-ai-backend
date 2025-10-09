package com.ifba.projeto.tcc.Infra.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SalvarCurriculoService {
    @Value("${curriculos.upload-dir}")
    private String uploadDir;
    public String salvarCurriculo(MultipartFile arquivo) {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename()
                    .replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            Path filePath = uploadPath.resolve(nomeArquivo);

            arquivo.transferTo(filePath.toFile());
            return filePath.toString();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo: " + e.getMessage(), e);
        }
    }

}
