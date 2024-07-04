package br.com.up_down_list.controller;

import br.com.up_down_list.dto.ArquivoRetornoDTO;
import br.com.up_down_list.entity.ArquivoEntity;
import br.com.up_down_list.service.ArquivoServise;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j

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
            System.out.println("O arquivo:       " + file.getOriginalFilename() + "       foi salvo com sucesso !");
            log.info("O arquivo:       " + file.getOriginalFilename() + "       foi salvo com sucesso !");
            return ResponseEntity.status(HttpStatus.CREATED).body("O arquivo: [" + file.getOriginalFilename() + "] foi salvo com sucesso !");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao subir o arquivo.");

        }
    }

    @GetMapping
    public ResponseEntity<List<ArquivoEntity>> listar() {
        List<ArquivoEntity> list = arquivoServise.listarArquivos();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        try {
            ArquivoRetornoDTO arquivoRetornoDTO = arquivoServise.downloadArquivo(id);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + arquivoRetornoDTO.getNome().concat(".pdf") + "\"")
                    .body(arquivoRetornoDTO.getFile());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}