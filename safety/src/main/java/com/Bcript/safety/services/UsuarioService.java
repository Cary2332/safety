package com.Bcript.safety.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Bcript.safety.dto.UsuarioDTO;
import com.Bcript.safety.entities.Usuario;
import com.Bcript.safety.repositories.UsuarioRepository;

@Service
public class UsuarioService {
 
	
	   @Autowired
	   private UsuarioRepository usuarioRepository;
	
	    @Autowired
    	private PasswordEncoder passwordEncoder;
	
	
	
	    public UsuarioDTO salvarUsuario(UsuarioDTO dto) {
		Usuario user = new Usuario();
		user.setEmail(dto.getEmail());
		user.setSenha( passwordEncoder.encode(dto.getSenha()));
		
		user = usuarioRepository.save(user);
		return new UsuarioDTO(user);
		
		
	}
	
		public boolean autenticarUsuario(UsuarioDTO dto) {
			Usuario usuario =  usuarioRepository.findByEmail(dto.getEmail());//verifica se o email esta correto
			
			if(usuario == null) {
				return false;
				
			}
			
			return passwordEncoder.matches(dto.getSenha(), usuario.getSenha());	//compara o que to recebendo e o que esta no banco		
			
			
		}
		
		
}
