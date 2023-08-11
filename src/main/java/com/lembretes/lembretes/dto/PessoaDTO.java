package com.lembretes.lembretes.dto;

import com.lembretes.lembretes.entity.Lembretes;
import lombok.Data;


import java.util.List;
@Data
public class PessoaDTO {

    private String name;

    private List<Lembretes> lembreteporPessoa;




}
