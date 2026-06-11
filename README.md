# album-figurinhas

gerenciador simples de figurinhas de um album feito em Java com SpringBoot
O usuário faz login, adiciona figurinhas por
número (de 1 a 200), vê quais possui, quais e quantas repetidas, quais faltam e o progresso do álbum.

## Tecnologias
- Java 21, Spring Boot, Spring Data JPA, Thymeleaf
- Banco H2 (em memória, para desenvolvimento)
- IDE: Eclipse

## Como rodar localmente
1. Clone o repositório (GitHub Desktop ou `git clone`).
2. No Eclipse: File > Import > Maven > Existing Maven Projects e selecione a pasta do projeto.
3. Rode a classe AlbumCopaApplication (Run As > Java Application).
4. Acesse http://localhost:8080

## Como usar
1. Acesse /cadastro e crie uma conta.
2. Faça login em /login.
3. Em "Adicionar figurinhas", digite números separados por espaço ou vírgula (ex.: 1, 2 3 50).
4. Veja sua coleção, repetidas, faltantes e progresso em "Minha coleção".

## Funcionalidades
- Cadastro e login simples.
- Catálogo fixo de 200 figurinhas, criado automaticamente.
- Adicionar várias figurinhas de uma vez por texto.
- Aumentar quantidade ao adicionar figurinha já existente.
- Remover figurinha (quantidade nunca fica negativa).
- Lista de figurinhas faltantes (de 1 a 200).
- Lista de figurinhas repetidas (quantidade - 1).
- Progresso do álbum em texto e barra visual.

## Regras de negócio
- O álbum tem exatamente 200 figurinhas.
- Cada figurinha é identificada apenas por um número (1 a 200).
- Números fora de 1–200 ou não numéricos são rejeitados (não adiciona nada).
- Adicionar figurinha existente aumenta a quantidade.
- Remover diminui a quantidade; ao chegar a 0, o item sai da coleção.
- O progresso considera apenas figurinhas distintas.

## Banco de dados (3 tabelas)
- usuarios (id, login, senha)
- figurinhas (id, numero)
- colecao_figurinha (id, usuario_id, figurinha_id, quantidade)

