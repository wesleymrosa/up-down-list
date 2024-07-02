package br.com.up_down_list.controller;

import br.com.up_down_list.entity.ArquivoEntity;
import br.com.up_down_list.service.ArquivoServise;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/arquivos")
public class ArquivoController {

    private final ArquivoServise arquivoServise;

    public ArquivoController(ArquivoServise arquivoServise) {
        this.arquivoServise = arquivoServise;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            arquivoServise.salvarArquivo(file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo salvo com sucesso ! ");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao subir o arquivo.");
        }
    }

    @GetMapping
    public ResponseEntity<List<ArquivoEntity>> listar() {
        List<ArquivoEntity> list = arquivoServise.listarArquivos();
        return ResponseEntity.ok().body(list);
    }
}
