package com.william.hubfintech.resource;

import com.william.hubfintech.domain.dto.PessoaFisicaDTO;
import com.william.hubfintech.exceptions.BeanValidationException;
import com.william.hubfintech.service.PessoaFisicaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/public/pessoas-fisicas", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaFisicaResource {

    private PessoaFisicaService PessoaFisicaService;

    public PessoaFisicaResource(PessoaFisicaService PessoaFisicaService) {
        this.PessoaFisicaService = PessoaFisicaService;
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
    })
    @PostMapping(path = "/criar")
    public ResponseEntity<PessoaFisicaDTO> criar(@Valid @RequestBody PessoaFisicaDTO pessoaFisicaDTO, BindingResult results) {

        if (results.hasErrors())
            throw new BeanValidationException(results.getFieldError().getDefaultMessage());

        return ResponseEntity.ok().body(PessoaFisicaService.create(pessoaFisicaDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    @GetMapping()
    public ResponseEntity<List<PessoaFisicaDTO>> buscaTodos() {
        return ResponseEntity.ok().body(PessoaFisicaService.selectAll());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "/{cpf}")
    public ResponseEntity<PessoaFisicaDTO> buscaPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok().body(PessoaFisicaService.findByCpf(cpf));
    }
}
