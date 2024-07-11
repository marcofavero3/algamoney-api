package com.example.algamoney.api.resource;


import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.netty.http.server.HttpServerResponse;

import java.net.URI;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {


    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria) { //@valid alteração nova
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{codigo}")
                .buildAndExpand(categoriaSalva.getCodigo())
                .toUri();
        return ResponseEntity.created(uri).body(categoriaSalva);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaRepository.findById(codigo);
        return categoria.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
