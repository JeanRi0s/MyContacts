package mycontacts.controller;

import mycontacts.exceptions.ContatoNaoEncontradoException;
import mycontacts.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class Agenda implements Buscavel {

    private final List<Contato> contatos = new ArrayList<>();

    public void adicionarContato(Contato contato) {
        contatos.add(contato);
        System.out.println("\n Contato \"" + contato.getNome() + "\" adicionado com sucesso!");
    }

    public void listarContatos() {
        System.out.println("\n**** LISTA DE CONTATOS ****");
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
            return;
        }
        for (int i = 0; i < contatos.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, contatos.get(i));
        }
        System.out.println("Total: " + contatos.size() + " contato(s).");
    }

    @Override
    public Contato buscarPorNome(String nome) throws ContatoNaoEncontradoException {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        throw new ContatoNaoEncontradoException(nome);
    }

    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        Contato encontrado = buscarPorNome(nome);
        contatos.remove(encontrado);
        System.out.println("\n Contato \"" + encontrado.getNome() + "\" removido com sucesso!");
    }

    public boolean isEmpty() {
        return contatos.isEmpty();
    }
}