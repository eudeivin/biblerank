package br.com.igreja.biblerank.controller;

import br.com.igreja.biblerank.model.Usuario;
import br.com.igreja.biblerank.repository.LivroRepository;
import br.com.igreja.biblerank.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private LivroRepository livroRepository;

    // Tela Inicial (Ranking) - CORRIGIDO (Removido o duplicado)
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("usuarios", service.obterRanking());
        // Envia a lista de livros para o Select do HTML
        model.addAttribute("livros", livroRepository.findAll());
        return "index";
    }

    // Método para Marcar Leitura - ATUALIZADO para receber o livroId
    @PostMapping("/ler")
    public String marcarLeitura(@RequestParam Long id,
                                @RequestParam Long livroId,
                                @RequestParam int capitulos,
                                RedirectAttributes attrs) {

        // Por enquanto, vamos apenas somar os capítulos como você já fazia
        // No futuro, podemos usar o livroId para dar medalhas por livro concluído!
        service.registrarLeitura(id, capitulos);

        attrs.addFlashAttribute("mensagem", "Leitura registrada com sucesso!");
        return "redirect:/";
    }

    // Exibir Tela de Perfil
    @GetMapping("/perfil")
    public String exibirPerfil(Model model, Principal principal) {
        if (principal == null) return "redirect:/login";

        String email = principal.getName();
        Usuario usuario = service.buscarPorEmail(email);
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    // Salvar atualização do Perfil
    @PostMapping("/perfil/atualizar")
    public String atualizarPerfil(@ModelAttribute Usuario dadosAtualizados,
                                  Principal principal,
                                  RedirectAttributes attrs) {
        service.atualizarDadosPerfil(principal.getName(), dadosAtualizados);
        attrs.addFlashAttribute("mensagem", "Perfil atualizado com sucesso!");
        return "redirect:/";
    }

    // Tela de Cadastro
    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(Usuario usuario) {
        service.salvar(usuario);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String telaLogin() {
        return "login";
    }
}