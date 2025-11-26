package com.icaro.capacitacao.service;

import com.icaro.capacitacao.dto.user.UserRequestDTO;
import com.icaro.capacitacao.dto.user.UserResponseDTO;
import com.icaro.capacitacao.exception.EmailInUseException;
import com.icaro.capacitacao.exception.ProductNotFoundException;
import com.icaro.capacitacao.exception.UserNotFoundException;
import com.icaro.capacitacao.mapper.UserMapper;
import com.icaro.capacitacao.model.UserEntity;
import com.icaro.capacitacao.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto){
        if(repository.findByUsername(dto.getUsername()).isPresent()){
            throw new RuntimeException("Usuário já existe");
        }

        UserEntity entity = mapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        UserEntity saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> find(){
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Long id) {
        if(!repository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        repository.deleteById(id);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found " + id));

        if(entity.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())){
            throw new EmailInUseException("Email already in use");
        }
        mapper.updateEntityFromDTO(dto, entity);
        UserEntity updatedEntity = repository.save(entity);
        return mapper.toDTO(updatedEntity);
    }

    @Transactional
    public UserResponseDTO updatePartialUser(Long id, UserRequestDTO dto) {
        UserEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found " + id));

        if(entity.getEmail().equals(dto.getEmail()) && repository.existsByEmail(dto.getEmail())){
            throw new EmailInUseException("Email already in use");
        }
        mapper.updateEntityPatch(dto, entity);
        UserEntity updatedEntity = repository.save(entity);
        return mapper.toDTO(updatedEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
