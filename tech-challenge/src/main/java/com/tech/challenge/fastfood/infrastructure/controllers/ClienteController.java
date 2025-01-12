package com.tech.challenge.fastfood.infrastructure.controllers;

import com.tech.challenge.fastfood.application.usecases.interactors.ClienteInteractor;
import com.tech.challenge.fastfood.infrastructure.persistence.converters.ClienteConverter;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ClienteRequest;
import com.tech.challenge.fastfood.infrastructure.controllers.dtos.ClienteResponseDTO;
import com.tech.challenge.fastfood.domain.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteInteractor clienteServicePort;
    private final ClienteConverter clienteConverter;


    @GetMapping("/cliente")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClienteResponseDTO> indentificarCliente(@RequestParam("cpf") String cpf) {
        Cliente cliente =  clienteServicePort.obterCliente(cpf);
        return ResponseEntity.ok(clienteConverter.toDto(cliente));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente cliente =  clienteServicePort.criarCliente(clienteConverter.toDomain(clienteRequest));
        return ResponseEntity.ok(clienteConverter.toDto(cliente));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClienteResponseDTO> editarCliente(@PathVariable Long id,  @RequestBody ClienteRequest clienteRequest) {
        Cliente cliente =  clienteServicePort.editarCliente(clienteConverter.toDomain(id,clienteRequest));
        return ResponseEntity.ok(clienteConverter.toDto(cliente));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        clienteServicePort.deletarCliente(id);
        return ResponseEntity.ok().build();
    }

}
