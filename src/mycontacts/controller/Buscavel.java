package mycontacts.controller;

import mycontacts.exceptions.ContatoNaoEncontradoException;
import mycontacts.model.Contato;

public interface Buscavel {
    Contato buscarPorNome(String nome) throws ContatoNaoEncontradoException;
}