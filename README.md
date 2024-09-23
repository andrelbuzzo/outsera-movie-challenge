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

## Requisitos
Para executar o projeto é necessário instalação do JDK 17. 
O passo-a-passo abaixo foi feito com base no Intellij IDEA.

## Configurações
- Por padrão a aplicação está configurada com o servlet.contextPath=/api para alterá-lo abra o arquivo application.properties e altere o valor da propriedade.
```yaml
    # Context
    server:
      port: 8080
      servlet:
        contextPath: /api
```
- Para alterar as configurações do banco de dados com URL, usuário, senha e url do console, e ativar/desativar o console, abra o arquivo application.properties. A opções do banco H2 e as propriedades do datasource são exibidas como abaixo:
```yaml
    # Datasource
    datasource:
    url: jdbc:h2:mem:mydb
    username: sa
    password:
    driverClassName: org.h2.Driver
    # JPA
    jpa:
      database-platform: org.hibernate.dialect.H2Dialect
      properties.hibernate.dialect: org.hibernate.dialect.H2Dialect
      hibernate.ddl-auto: create-drop
      defer-datasource-initialization: true
    # H2
    h2:
      console:
        enabled: true
        path: /h2
```

## Para executar o projeto
Para executar o projeto, nenhuma instalação externa é necessária. Ao ser iniciada, a aplicação cria o banco de dados e o popula com os dados do arquivo movielist.csv, que se encontra em *src/main/resources/static*.
1. Clone o repositório o faça download;
2. Se está usando uma ferramenta externa a IDE, importe o projeto como projeto Maven existente;
3. Execute o comando Maven abaixo:
```sh
        $ mvn install -Dmaven.test.skip=true
```
4. Para iniciar a aplicação clique no projeto com o botão direito do mouse, vá até a opção *Run As* e selecione Spring Boot App.

## EndPoints
Para ver a lista de chamadas REST disponíveis, seus parametros, códigos de resposta HTTP, e tipo de retorno, inicie a aplicação e acesse: http://localhost:8080/api/docs

## Testes
Para executar os testes abra a classe AppTest.java, clique em Run AppTest. Isso fará com que todos os testes de integração implementados sejam executados.