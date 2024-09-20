# outsera-movie-challenge
Code challenge for Outsera (Movie API)

## Especificação do Teste
Desenvolva uma API RESTful para possibilitar a leitura da lista de indicados e 
vencedores da categoria Pior Filme do Golden Raspberry Awards.

## Requisito do sistema:
- Ler o arquivo CSV dos filmes e inserir os dados em uma base de dados ao 
  iniciar a aplicação.
   
## Requisitos da API:
- Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que 
  obteve dois prêmios mais rápido, seguindo a especificação de formato definida 
  na página 2.
  
## Requisitos não funcionais do sistema:
- O web service RESTful deve ser implementado com base no nível 2 de maturidade
   de Richardson;
- Devem ser implementados somente testes de integração. Eles devem garantir que
   os dados obtidos estão de acordo com os dados fornecidos na proposta;
- O banco de dados deve estar em memória utilizando um SGBD embarcado (por
   exemplo, H2). Nenhuma instalação externa deve ser necessária;
- A aplicação deve conter um readme com instruções para rodar o projeto e os
   testes de integração.
- O código-fonte deve ser disponibilizado em um repositório git (Github, 
  Gitlab, Bitbucket, etc).

### Formato da API:
Intervalo de prêmios:

```json
{
  "min": [
    {
      "producer": "Producer 1",
      "interval": 1,
      "previousWin": 2008,
      "followingWin": 2009
    },
    {
      "producer": "Producer 2",
      "interval": 1,
      "previousWin": 2018,
      "followingWin": 2019
    }
  ],
  "max": [
    {
      "producer": "Producer 1",
      "interval": 99,
      "previousWin": 1900,
      "followingWin": 1999
    },
    {
      "producer": "Producer 2",
      "interval": 99,
      "previousWin": 2000,
      "followingWin": 2099
    }
  ]
}
```