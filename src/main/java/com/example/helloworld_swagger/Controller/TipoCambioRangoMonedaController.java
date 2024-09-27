package com.example.helloworld_swagger.Controller;


import com.example.helloworld_swagger.Service.TipoCambioRangoMonedaService;
import com.example.helloworld_swagger.DTO.TipoCambioRangoMonedaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@Tag(name = "Tipo de Cambio", description = "API Tipo Cambio Rango Moneda")

public class TipoCambioRangoMonedaController {
    private TipoCambioService tipoCambioService;

    @Autowired
    public void setTipoCambioService(TipoCambioService tipoCambioService) {
        this.tipoCambioService = tipoCambioService;
    }

    @Operation(summary = "Consultar tipo de cambio fecha inicial y por moneda")
    @GetMapping("/tipoCambioPorFechaMoneda")
    public List<TipoCambioDTO> obtenerTipoCambioPorFechaMoneda(
            @Parameter(description = "Fecha inicio en formato dd/MM/yyyy")
            @RequestParam(value = "fecha_ini", required = true) String fechaInicio,

            @Parameter(description = "Código de la moneda")
            @RequestParam(value = "moneda", required = true) Integer moneda) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicio;

        try {
            inicio = LocalDate.parse(fechaInicio, formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha inválido. Use dd/MM/yyyy.");
        }


        return tipoCambioService.consultarTipoCambioPorFechaMoneda(fechaInicio, moneda);
    }
}
