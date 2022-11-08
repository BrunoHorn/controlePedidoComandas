package com.controle.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.controle.api.dto.UsuarioDto;
import com.controle.api.dto.UsuarioInputDto;
import com.controle.api.mapper.UsuarioMapper;
import com.controle.api.model.Usuario;
import com.controle.api.repositorie.UsuarioRepository;
import com.controle.api.service.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario-cadastro")
@Api(value="API cadastro de Usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo Usuario")
    public ResponseEntity<UsuarioDto> saveUsuario(@RequestBody @Valid UsuarioInputDto usuarioInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioInputDto, null));
    }
	
    @GetMapping
    @ApiOperation(value="Busca lista de usuarios cadastrados")
    public ResponseEntity<List<UsuarioDto>> getListUsuarios(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value="Busca usuario cadastrado pelo ID")
    public ResponseEntity<UsuarioDto> getUsuarioid(@PathVariable(value = "id") Long id){
    	Usuario usuario = usuarioService.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(usuarioMapper.toUsuarioDto(usuario));
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza usuario cadastrado pelo ID")
    public ResponseEntity<UsuarioDto> updateUsuario(@PathVariable(value = "id")Long id,@RequestBody @Valid UsuarioInputDto usuarioInputDto){
        Usuario usuarioUpdate = usuarioService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioInputDto, usuarioUpdate.getId()));
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta usuario pelo id")
	public void delete(Usuario usuario) {
    	usuarioRepository.delete(usuario);		
	}

}
