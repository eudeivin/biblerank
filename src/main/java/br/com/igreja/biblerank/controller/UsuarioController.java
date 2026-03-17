package br.com.igreja.biblerank.controller;

import br.com.igreja.biblerank.model.Usuario;
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

    // Tela Inicial (Ranking)
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("usuarios", service.obterRanking());
        return "index";
    }

    @PostMapping("/ler")
    public String marcarLeitura(@RequestParam Long id,
                                @RequestParam int capitulos,
                                RedirectAttributes attrs) {
        service.registrarLeitura(id, capitulos);
        attrs.addFlashAttribute("mensagem", "Leitura registrada com sucesso!");
        return "redirect:/";
    }

    // NOVO: Exibir Tela de Perfil
    @GetMapping("/perfil")
    public String exibirPerfil(Model model, Principal principal) {
        // Principal é quem o Spring Security diz que está logado (o e-mail dele)
        String email = principal.getName();
        Usuario usuario = service.buscarPorEmail(email);
        model.addAttribute("usuario", usuario);
        return "perfil";
    }

    // NOVO: Salvar atualização do Perfil
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