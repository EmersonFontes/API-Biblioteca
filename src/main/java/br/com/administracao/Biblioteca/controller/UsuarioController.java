package br.com.administracao.Biblioteca.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.administracao.Biblioteca.model.Usuario;
import br.com.administracao.Biblioteca.repository.UsuarioRepository;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/Listar")
    public List<Usuario> listar() {return usuarioRepository.findAll(); }


    @PostMapping("/Cadastrar")
    public ResponseEntity<String> incluir(@RequestBody Usuario usuario){

        if((usuario.getEmail() != null && usuario.getNome() != null && usuario.getSenha()!=null) ){
            usuarioRepository.save(usuario);
            return new ResponseEntity<String>("Cadastrado Com Sucesso", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Erro ao Cadastrar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Atualizar")
    public ResponseEntity<String> alterar(@RequestBody Usuario usuario){


        if(usuarioRepository.existsById(usuario.getId())){
            usuarioRepository.save(usuario);
            return new ResponseEntity<String>("Atualizado Com Sucesso", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("ID não encontrado", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/Deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable  Integer id){

        if(id > 0){
            if(usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                return new ResponseEntity<String>("Deletedo Com Sucesso", HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("Esse ID não existe", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<String>("Esse ID é 0 ou Negativo!", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/Pesquisar/{id}")
    public ResponseEntity<?> ler(@PathVariable Integer id){

        if(id > 0){
            if(usuarioRepository.existsById(id)) {
                return new ResponseEntity<>(usuarioRepository.findById(id), HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("Esse ID não existe", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<String>("Esse ID é 0 ou Negativo!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/Nome/{nome}")
    public List<Usuario> ler(@PathVariable String nome){

        return usuarioRepository.findAllByNome(nome);

    }
}
