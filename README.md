# MyContacts

Agenda de contatos em Java, executada via linha de comando (CLI). Permite cadastrar, listar, buscar e remover contatos pessoais ou comerciais, com validação de e-mail e tratamento de exceções.

## Funcionalidades

- Adicionar contato **pessoal** (nome, telefone, e-mail)
- Adicionar contato **comercial** (nome, telefone, e-mail, empresa) — via herança de `Contato`
- Listar todos os contatos cadastrados
- Buscar contato por nome
- Remover contato por nome
- Validação de formato de e-mail (com até 3 tentativas)
- Tratamento de erro para contato não encontrado (`ContatoNaoEncontradoException`)

## Estrutura do projeto

```
MyContacts/
└── src/
    └── mycontacts/
        ├── app/
        │   └── Main.java                    # Ponto de entrada e menu interativo
        ├── controller/
        │   ├── Agenda.java                  # Gerencia a lista de contatos
        │   └── Buscavel.java                # Interface de busca
        ├── model/
        │   ├── Contato.java                 # Modelo base
        │   └── ContatoComercial.java        # Modelo comercial (herda de Contato)
        ├── exceptions/
        │   └── ContatoNaoEncontradoException.java
        └── utils/
            └── ValidadorEmail.java          # Validação de e-mail via regex
```

## Conceitos aplicados

- Programação orientada a objetos (herança, polimorfismo, encapsulamento)
- Interfaces (`Buscavel`)
- Exceções customizadas
- Coleções (`ArrayList`)
- Expressões regulares para validação

## Requisitos

- JDK 17 ou superior

## Como executar

### Via terminal

```bash
# Compilar
javac -d out $(find src -name "*.java")

# Executar
java -cp out mycontacts.app.Main
```

### Via IntelliJ IDEA

1. Abra a pasta do projeto no IntelliJ.
2. Marque `src` como *Sources Root* (já configurado no `.iml`).
3. Execute a classe `mycontacts.app.Main`.

## Exemplo de uso

```
Bem-vindo ao MyContacts!

**** AGENDA DE CONTATOS ****
1. Adicionar novo contato
2. Listar contatos
3. Buscar por nome
4. Remover contato
5. Sair
Escolha uma opção:
```

## Possíveis melhorias futuras

- Persistência dos dados (arquivo ou banco de dados)
- Interface gráfica (JavaFX)
- Edição de contatos existentes
- Testes unitários

## Autor

Desenvolvido por Jean — Engenharia de Mecatrônica , Instituto Federal do Ceará.
