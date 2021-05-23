package com.william.hubfintech.resource;

import com.william.hubfintech.domain.dto.TransferenciaDTO;
import com.william.hubfintech.service.TransferenciaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/public/transferencias", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferenciaResource {

    private TransferenciaService TransferenciaService;

    public TransferenciaResource(TransferenciaService TransferenciaService) {
        this.TransferenciaService = TransferenciaService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
    })
    @GetMapping()
    public ResponseEntity<List<TransferenciaDTO>> buscaTodos() {
        return ResponseEntity.ok().body(TransferenciaService.selectAll());
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not found")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferenciaDTO> buscaPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(TransferenciaService.findById(id));
    }
}
