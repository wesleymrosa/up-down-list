package br.com.up_down_list.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.core.io.Resource;

@Builder
@Getter
public class ArquivoRetornoDTO {
    private String nome;
    private Resource file;

}