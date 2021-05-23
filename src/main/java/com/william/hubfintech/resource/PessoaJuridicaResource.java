package com.william.hubfintech.resource;

import com.william.hubfintech.domain.dto.PessoaJuridicaDTO;
import com.william.hubfintech.exceptions.BeanValidationException;
import com.william.hubfintech.service.PessoaJuridicaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/public/pessoas-juridicas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaJuridicaResource {

    private PessoaJuridicaService PessoaJuridicaService;

    public PessoaJuridicaResource(PessoaJuridicaService PessoaJuridicaService) {
        this.PessoaJuridicaService = PessoaJuridicaService;
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
    })
    @PostMapping(path = "/criar")
    public ResponseEntity<PessoaJuridicaDTO> criar(@Valid @RequestBody PessoaJuridicaDTO pessoaJuridicaDTO, BindingResult results) {

        if (results.hasErrors())
            throw new BeanValidationException(results.getFieldError().getDefaultMessage());

        return ResponseEntity.ok().body(PessoaJuridicaService.create(pessoaJuridicaDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    @GetMapping()
    public ResponseEntity<List<PessoaJuridicaDTO>> buscaTodos() {
        return ResponseEntity.ok().body(PessoaJuridicaService.selectAll());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "/{cnpj}")
    public ResponseEntity<PessoaJuridicaDTO> buscaPorCnpj(@PathVariable String cnpj) {
        return ResponseEntity.ok().body(PessoaJuridicaService.findByCpf(cnpj));
    }
}
