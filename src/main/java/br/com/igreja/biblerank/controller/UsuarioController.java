package br.com.igreja.biblerank.controller;

import br.com.igreja.biblerank.model.Usuario;
import br.com.igreja.biblerank.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        // Essa é a mensagem que vai aparecer na tela
        attrs.addFlashAttribute("mensagem", "Leitura registrada com sucesso!");

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