package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if (pessoa.isEmpty() || pessoa.get().isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
        validarPessoa(lancamento);

        lancamentoSalvo.setDescricao(lancamento.getDescricao());
        lancamentoSalvo.setDataVencimento(lancamento.getDataVencimento());
        lancamentoSalvo.setDataPagamento(lancamento.getDataPagamento());
        lancamentoSalvo.setValor(lancamento.getValor());
        lancamentoSalvo.setTipo(lancamento.getTipo());
        lancamentoSalvo.setCategoria(lancamento.getCategoria());
        lancamentoSalvo.setPessoa(lancamento.getPessoa());

        return lancamentoRepository.save(lancamentoSalvo);
    }

    public void remover(Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }

    private void validarPessoa(Lancamento lancamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if (pessoa.isEmpty() || pessoa.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }

    private Lancamento buscarLancamentoExistente(Long codigo) {
        return lancamentoRepository.findById(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Lançamento não encontrado"));
    }
}
