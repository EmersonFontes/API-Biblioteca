package br.com.administracao.Biblioteca.controller;

import java.util.List;
import java.util.Optional;

import br.com.administracao.Biblioteca.model.Livro;
import br.com.administracao.Biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.administracao.Biblioteca.model.LivroUsuario;
import br.com.administracao.Biblioteca.repository.LivroUsuarioRepository;


@RestController
@RequestMapping("/LivroUsuario")
public class LivroUsuarioController {
	
	@Autowired
    private LivroUsuarioRepository livroUsuarioRepository;

    /*@Autowired
    private UsuarioRepository usuarioRepository;*/

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/Listar")
    public List<LivroUsuario> listar() {return livroUsuarioRepository.findAll(); }


    @PostMapping("/Cadastrar")
    public ResponseEntity<?> incluir(@RequestBody LivroUsuario livroUsuario){

        Livro l = livroRepository.findById(livroUsuario.getLivro().getId()).get();

    	if(l.getQnt_total() > 0){
            livroUsuarioRepository.save(livroUsuario);
            l.setQnt_total(l.getQnt_total() - 1);
            livroUsuario.setDataEntrega(livroUsuario.calculaDataEntrega());
            livroRepository.save(l);

            return new ResponseEntity<String>("Sucesso ao cadastrar", HttpStatus.OK);
        }else if(l.getQnt_total() == 0){
            l.setDisponibilidade(l.livroDisponivel());
            livroRepository.save(l);
            return new ResponseEntity<String>("O livro encontra-se :" + l.getDisponibilidade(), HttpStatus.OK);
        }else{

            return new ResponseEntity<String>("erro ao cadastrar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/Atualizar")
    public void alterar(@RequestBody LivroUsuario livroUsuario){
 
    	livroUsuarioRepository.save(livroUsuario);
    	
    }

    @DeleteMapping("/Deletar/{id}")
    public void deletar(@PathVariable  Integer id){
        Optional<LivroUsuario> lu = livroUsuarioRepository.findById(id);
        Livro livro = livroRepository.findById(lu.get().getLivro().getId()).get();

        if(id > 0){
            if(livro.getQnt_total() > 0){
                livro.setQnt_total(livro.getQnt_total() + 1);
                livroRepository.save(livro);
                livroUsuarioRepository.deleteById(id);
            }else{
                livro.setQnt_total(livro.getQnt_total() + 1);
                livro.setDisponibilidade(livro.livroDisponivel());
                livroRepository.save(livro);
                livroUsuarioRepository.deleteById(id);
            }

        }


    }

    @GetMapping("/Pesquisar/{id}")
    public ResponseEntity<?> ler(@PathVariable Integer id){

        if(id > 0){
            if(livroUsuarioRepository.existsById(id)) {

                return new ResponseEntity<>(livroUsuarioRepository.findById(id), HttpStatus.OK);

            }else {
                return new ResponseEntity<String>("Esse ID não existe", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }else{
            return new ResponseEntity<String>("Esse ID é 0 ou Negativo!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
