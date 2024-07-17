package com.devsuperior.crud_of_costumers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.crud_of_costumers.dto.ClientDTO;
import com.devsuperior.crud_of_costumers.entities.Client;
import com.devsuperior.crud_of_costumers.repositories.ClientRepository;
import com.devsuperior.crud_of_costumers.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }
    
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = repository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("ID não encontrado."));
        return new ClientDTO(client);
    }
    
}