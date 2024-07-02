package br.com.up_down_list.controller;

import br.com.up_down_list.service.ArquivoServise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/arquivos")
public class ArquivoController {

    private final ArquivoServise arquivoServise;

    public ArquivoController(ArquivoServise arquivoServise) {
        this.arquivoServise = arquivoServise;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> salvar(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            arquivoServise.salvarArquivo(file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo salvo com sucesso ! ");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao subir o arquivo.");
        }
    }
}
