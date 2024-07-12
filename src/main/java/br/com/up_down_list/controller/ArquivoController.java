package br.com.up_down_list.controller;

import br.com.up_down_list.dto.ArquivoRetornoDTO;
import br.com.up_down_list.entity.ArquivoEntity;
import br.com.up_down_list.service.ArquivoServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/arquivos")
@RequiredArgsConstructor
public class ArquivoController {

    private final ArquivoServise arquivoServise;

    @PostMapping
    public ResponseEntity<String> salvar(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            try {
                arquivoServise.salvarArquivo(file);
                //System.out.println("Arquivo .pdf salvo com sucesso ! ");
                System.out.println("O arquivo:       " + file.getOriginalFilename() + "       foi salvo com sucesso !");
                log.info("O arquivo:       " + file.getOriginalFilename() + "       foi salvo com sucesso !");
                return ResponseEntity.status(HttpStatus.CREATED).body("Arquivo do tipo .pdf salvo com sucesso ! \nO arquivo: [" + file.getOriginalFilename() + "] foi salvo com sucesso !");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao subir o arquivo.");
            }
        } else {
            return ResponseEntity.badRequest().body("Esse arquivo não é foi salvo ! \nSomente arquivos do tipo .pdf são permitidos neste sistema !");
        }
    }

//    @PostMapping(value = "/salvarmaisdeum")
//    public ResponseEntity<String> salvarmaisdeum(@RequestParam("file") MultipartFile[] files) {
//        try {
//            for (MultipartFile file : files) {
//                if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
//                    return ResponseEntity.badRequest().body("Esse arquivo não foi salvo! Somente arquivos do tipo .pdf são permitidos neste sistema.");
//                }
//                arquivoServise.salvarArquivo(file);
//                System.out.println("O arquivo: " + file.getOriginalFilename() + " foi salvo com sucesso!");
//                log.info("O arquivo: " + file.getOriginalFilename() + " foi salvo com sucesso!");
//            }
//            return ResponseEntity.status(HttpStatus.CREATED).body("Arquivos salvos com sucesso!");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao subir o arquivo.");
//        }
//    }

    @PostMapping(value = "/salvarmaisdeum")
    public ResponseEntity<String> salvarmaisdeum(@RequestParam("file") MultipartFile[] files) {
        List<String> arquivosSalvos = new ArrayList<>();
        List<String> arquivosNaoSalvos = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
                    arquivosNaoSalvos.add(file.getOriginalFilename());
                    log.info("Os arquivos: " + arquivosNaoSalvos + " não foram salvos com sucesso !");
                } else {
                    arquivoServise.salvarArquivo(file);
                    arquivosSalvos.add(file.getOriginalFilename());
                    System.out.println("O arquivo: " + file.getOriginalFilename() + " foi salvo com sucesso!");
                    log.info("O arquivo: " + file.getOriginalFilename() + " foi salvo com sucesso!");
                    log.info("Os arquivos: " + arquivosSalvos + " foram salvos com sucesso !");
                }
            }

            if (!arquivosSalvos.isEmpty()) {
                String msg = "Arquivos salvos com sucesso: " + arquivosSalvos +  String.join(", ", arquivosSalvos);
                log.info("Os arquivos: " + arquivosSalvos + " foram salvos com sucesso (de baixo)!");
                return ResponseEntity.status(HttpStatus.CREATED).body(msg);
            } else {
                String msg = "Nenhum arquivo do tipo .pdf foi salvo.";
                if (!arquivosNaoSalvos.isEmpty()) {
                    msg += " Arquivos não salvos: " + String.join(", ", arquivosNaoSalvos);
                    log.info("Os arquivos: " + arquivosNaoSalvos + " não foram salvos com sucesso (de baixo) !");
                }
                return ResponseEntity.badRequest().body(msg);
            }
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
            return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"" + arquivoRetornoDTO.getNome().concat(".pdf") + "\"").body(arquivoRetornoDTO.getFile());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
