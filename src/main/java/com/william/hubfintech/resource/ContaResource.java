package com.william.hubfintech.resource;

import com.william.hubfintech.domain.dto.ContaDTO;
import com.william.hubfintech.domain.dto.TransferenciaDTO;
import com.william.hubfintech.exceptions.BeanValidationException;
import com.william.hubfintech.service.ContaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/public/contas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContaResource {

    private ContaService ContaService;

    public ContaResource(ContaService ContaService) {
        this.ContaService = ContaService;
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
    })
    @PostMapping(path = "/criar")
    public ResponseEntity<ContaDTO> criar(@Valid @RequestBody ContaDTO contaDTO, BindingResult results) {

        if (results.hasErrors())
            throw new BeanValidationException(results.getFieldError().getDefaultMessage());

        return ResponseEntity.ok().body(ContaService.create(contaDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    @PutMapping(path = "/{id}/inserir-credito/{credit}")
    public ResponseEntity<ContaDTO> inserirCredito(@PathVariable Long id, @RequestBody TransferenciaDTO transferenciaDTO) {
        return ResponseEntity.ok().body(ContaService.insertCredit(id, transferenciaDTO));
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    @GetMapping()
    public ResponseEntity<List<ContaDTO>> buscaTodos() {
        return ResponseEntity.ok().body(ContaService.selectAll());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ContaDTO> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(ContaService.findById(id));
    }
}
