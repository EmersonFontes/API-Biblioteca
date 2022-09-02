package br.com.administracao.Biblioteca.controller;


import java.util.List;
import java.util.Optional;

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

import br.com.administracao.Biblioteca.model.Livro;
import br.com.administracao.Biblioteca.repository.LivroRepository;

import javax.swing.*;

@RestController
@RequestMapping("/Livro")
public class LivroController {


    @Autowired
    private LivroRepository livroRepository;


    @GetMapping("/Listar")
    public List<Livro> listar() {return livroRepository.findAll(); }
    
    @PostMapping("/Cadastrar")
    public ResponseEntity<String> incluir(@RequestBody Livro livro){

        if(livro.getQnt_total()>0 && livro.getNome()!=null){
            livro.livroDisponivel();
            livro.setMatricula(livro.gerarMatricula());
            livroRepository.save(livro);

            return new ResponseEntity<String>("Sucesso ao cadastrar", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Erro ao Cadastrar", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    	
   }
    @PutMapping("/Atualizar")
    public ResponseEntity<String> alterar(@RequestBody Livro livro){
        if(livroRepository.existsById(livro.getId())){

            livro.livroDisponivel();
            livroRepository.save(livro);

            return new ResponseEntity<String>("Sucesso ao Atualizar", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("ID não existe", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @DeleteMapping("/Deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable  Integer id){

        if(id > 0){
            if(livroRepository.existsById(id)) {
                livroRepository.deleteById(id);
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
            if(livroRepository.existsById(id)) {
                return new ResponseEntity<>(livroRepository.findById(id), HttpStatus.OK);
            }else {
                return new ResponseEntity<String>("Esse ID não existe", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<String>("Esse ID é 0 ou Negativo!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/Nome/{nome}")
    public List<Livro> Nome(@PathVariable String nome){

        return livroRepository.findAllByNome(nome);

    }

    @GetMapping("/Disponibilidade")
    public List<Livro> Disponibilidade(){

        return livroRepository.findAllByDisponibilidade();

    }
}
