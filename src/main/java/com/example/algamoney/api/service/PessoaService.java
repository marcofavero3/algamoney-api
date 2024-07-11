package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);

        if (!pessoaOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        Pessoa pessoaSalva = pessoaOptional.get();
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);

        if (!pessoaOptional.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }

        Pessoa pessoaSalva = pessoaOptional.get();
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }
}
