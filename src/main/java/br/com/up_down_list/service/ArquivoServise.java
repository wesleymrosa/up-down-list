package br.com.up_down_list.service;

import br.com.up_down_list.dto.ArquivoRetornoDTO;
import br.com.up_down_list.entity.ArquivoEntity;
import br.com.up_down_list.repository.ArquivoRepository;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArquivoServise {

    private final ArquivoRepository arquivoRepository;

    public ArquivoServise(ArquivoRepository arquivoRepository) {
        this.arquivoRepository = arquivoRepository;
    }

    public ArquivoEntity salvarArquivo(MultipartFile file) throws IOException {
        ArquivoEntity arquivo = new ArquivoEntity();
        arquivo.setNome(file.getOriginalFilename());
        arquivo.setContentType(file.getContentType());
        arquivo.setConteudo(file.getBytes());
        return arquivoRepository.save(arquivo);
    }

    public List<ArquivoEntity> listarArquivos() {
        return arquivoRepository.findAll();
    }

    public ArquivoRetornoDTO downloadArquivo(Long id) {
        return arquivoRepository.findById(id)
                .map(arq -> ArquivoRetornoDTO.builder()
                        .nome(arq.getNome())
                        .file(new ByteArrayResource(arq.getConteudo()))
                        .build())
                .orElse(null);

    }
}
