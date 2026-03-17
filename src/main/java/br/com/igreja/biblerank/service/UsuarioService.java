package br.com.igreja.biblerank.service;

import br.com.igreja.biblerank.model.Usuario;
import br.com.igreja.biblerank.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(encoder.encode(usuario.getSenha())); // Transforma "123" em "$2a$10$..."
        return repository.save(usuario);
    }

    public List<Usuario> obterRanking(){
        return repository.findAllByOrderByTotalCapitulosLidosDesc();
    }

    public void registrarLeitura(Long usuarioId, int capitulos){
            Usuario user = repository.findById(usuarioId).orElseThrow();
            user.setTotalCapitulosLidos(user.getTotalCapitulosLidos() + capitulos);
            repository.save(user);
        }

}
