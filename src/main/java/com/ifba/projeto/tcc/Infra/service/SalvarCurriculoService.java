package com.ifba.projeto.tcc.Infra.service;

import com.ifba.projeto.tcc.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalvarCurriculoService {
    @Value("${curriculos.upload-dir}")
    private String uploadDir;
    
    public String salvarCurriculo(MultipartFile arquivo) {
        try {
            log.info("Salvando currículo: {}", arquivo.getOriginalFilename());
            Path uploadPath = Paths.get(uploadDir);
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.debug("Diretório de upload criado: {}", uploadPath);
            }

            String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename()
                    .replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
            Path filePath = uploadPath.resolve(nomeArquivo);

            arquivo.transferTo(filePath.toFile());
            log.debug("Currículo salvo em: {}", filePath);
            return filePath.toString();

        } catch (IOException e) {
            log.error("Erro ao salvar arquivo: {}", e.getMessage(), e);
            throw new BusinessException("Erro ao salvar arquivo", e);
        }
    }
}
