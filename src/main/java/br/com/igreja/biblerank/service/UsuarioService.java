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
        usuario.setSenha(encoder.encode(usuario.getSenha()));
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

    // --- NOVOS MÉTODOS PARA O PERFIL ---

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    public void atualizarDadosPerfil(String emailOriginal, Usuario novosDados) {
        Usuario usuario = repository.findByEmail(emailOriginal).orElse(null);
        if (usuario != null) {
            usuario.setNome(novosDados.getNome());

            // Só atualiza a foto se o usuário colou um link novo
            if (novosDados.getFotoUrl() != null && !novosDados.getFotoUrl().isBlank()) {
                usuario.setFotoUrl(novosDados.getFotoUrl());
            }

            repository.save(usuario);
        }
    }
}