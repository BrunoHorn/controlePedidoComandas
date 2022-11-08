package com.controle.api.service;

import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.api.dto.UsuarioDto;
import com.controle.api.dto.UsuarioInputDto;
import com.controle.api.mapper.UsuarioMapper;
import com.controle.api.model.Usuario;
import com.controle.api.repositorie.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioDto save(@Valid UsuarioInputDto usuarioInputDto, Long id) {
		var usuario = usuarioMapper.toUsuario(usuarioInputDto); 
		
        if (Objects.nonNull(id)) {
        	usuario.setId(id);
        }		
        usuario = usuarioRepository.save(usuario);		
		UsuarioDto usuarioDto = usuarioMapper.toUsuarioDto(usuario);		
	return usuarioDto;
	}

	public List<UsuarioDto> findAll() {
		return usuarioMapper.toUsuarioListDto(usuarioRepository.findAll());
	}

	public Usuario findById(Long id) {
		var usuarioOptional = usuarioRepository.findById(id);
		if(usuarioOptional.isEmpty()) {
			throw new RuntimeException("Não a usuarios cadastrados com esse ID");
		}
		return usuarioOptional.get();
	}
	


}
