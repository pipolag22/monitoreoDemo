package com.example.demo.Controller;

import com.example.demo.persistence.Dto.PlantaRequestDto;
import com.example.demo.persistence.Dto.PlantaResponseDto;
import com.example.demo.persistence.entities.ApiResponse;
import com.example.demo.persistence.entities.Planta;
import com.example.demo.services.PaisesServices;
import com.example.demo.services.PlantaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/plantas")
@CrossOrigin(origins = "*")
public class PlantaController {

    @Autowired
    private PlantaService plantaService;

    @Autowired
    private PaisesServices paisesServices;

    // Obtener todas las plantas
    @GetMapping
    public List<PlantaResponseDto> getAllPlantas() {
        return plantaService.getAllPlantas().stream()
                .map(planta -> {
                    PlantaResponseDto responseDto = new PlantaResponseDto();
                    responseDto.setId(planta.getId());
                    responseDto.setName(planta.getName());
                    responseDto.setPais(planta.getPais());
                    responseDto.setLecturas(planta.getLecturas());
                    return responseDto;
                })
                .collect(Collectors.toList());
    }

    // Crear una planta
    @PostMapping
    public ResponseEntity<ApiResponse<PlantaResponseDto>> createPlanta(@RequestBody PlantaRequestDto plantaDto) {

        if (plantaDto.getName() == null || plantaDto.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
                    false,
                    "El nombre de la planta es obligatorio",
                    null
            ));
        }

        // Validar que el pais sea valido
        List<Map<String, Object>> countries = paisesServices.getCountries();
        boolean validCountry = countries.stream()
                .anyMatch(country -> country.get("name").equals(plantaDto.getPais()));

        if (!validCountry) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(
                    false,
                    "El pais no es valido",
                    null
            ));
        }

        // Crear entidad Planta
        Planta planta = new Planta();
        planta.setName(plantaDto.getName());
        planta.setPais(plantaDto.getPais());
        planta.setLecturas(plantaDto.getLecturas());

        Planta createdPlanta = plantaService.createPlanta(planta);

        PlantaResponseDto responseDto = new PlantaResponseDto();
        responseDto.setId(createdPlanta.getId());
        responseDto.setName(createdPlanta.getName());
        responseDto.setPais(createdPlanta.getPais());
        responseDto.setLecturas(createdPlanta.getLecturas());

        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Planta creada exitosamente",
                responseDto
        ));
    }

    // Actualizar una planta
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PlantaResponseDto>> updatePlanta(@PathVariable Long id, @RequestBody PlantaRequestDto plantaDto) {
        return plantaService.updatePlanta(id, plantaDto)
                .map(planta -> {
                    PlantaResponseDto responseDto = new PlantaResponseDto();
                    responseDto.setId(planta.getId());
                    responseDto.setName(planta.getName());
                    responseDto.setPais(planta.getPais());
                    responseDto.setLecturas(planta.getLecturas());

                    return ResponseEntity.ok(new ApiResponse<>(
                            true,
                            "Planta actualizada exitosamente",
                            responseDto
                    ));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(
                        false,
                        "Planta no encontrada",
                        null
                )));
    }
}
