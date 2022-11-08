package com.controle.api.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.controle.api.dto.UsuarioDto;
import com.controle.api.dto.UsuarioInputDto;
import com.controle.api.model.Usuario;

@Component
public class UsuarioMapper {

	public Usuario toUsuario(UsuarioInputDto usuariolDto) {
		var usuario = new Usuario();
		 BeanUtils.copyProperties(usuariolDto, usuario); 
		 return usuario;
	}
	
	public UsuarioDto toUsuarioDto(Usuario usuario) {
		var usuarioDto = new UsuarioDto();
		 BeanUtils.copyProperties(usuario, usuarioDto); 
		 return usuarioDto;
	}
	
	public List<UsuarioDto> toUsuarioListDto(List<Usuario> usuario) {
		List<UsuarioDto> usuarioListDto = new ArrayList<>();		
		for (Usuario us : usuario) {
			var usuarioDto = new UsuarioDto();
			usuarioDto.setId(us.getId());
			usuarioDto.setNome(us.getNome());
			usuarioDto.setSenha(us.getSenha());
			usuarioDto.setStatus(us.getStatus());
			usuarioDto.setTipoPermissao(us.getTipoPermissao());
			
			usuarioListDto.add(usuarioDto);
		}				 
		 return usuarioListDto;
	}
	
}
