# Desafio-HubFintech

### Inicialização

* Primeiro, abra o terminal e navege até a raiz do projeto.

* Então realizar o build:
```
mvn clean install
``` 

* Navegue até a pasta target na raiz do projeto e execute o jar do projeto:
``` 
java -jar desafio-hubfintech-0.0.1-SNAPSHOT.jar
```

* Agora para ver os endpoints basta acessar o Swagger: http://localhost:8080/swagger-ui.html#/

### Endpoints

* conta-resource
    * buscarTodos: retorna todas as contas cadastradas
    * buscaPorId: retorna uma conta com o id solicitado
    * inserirCredito: insere credito a uma conta sendo no máximo 500 para pessoa fisica e 2000 para pessoa juridica.
    * criar: cria uma nova conta a partir de uma pessoa fisica ou juridica (necessário ter uma pessoa cadastrada)

* pessoa-fisica-resource
    * buscarTodos: retorna todas as pessoas fisicas cadastradas
    * buscaPorId: retorna uma pessoa fisica com o id solicitado
    * criar: cria uma pessoa a partir de um cpf (o cpf precisa estar formatado) 
    
* pessoa-juridica-resource
    * buscarTodos: retorna todas as pessoas juridicas cadastradas
    * buscaPorId: retorna uma pessoa juridica com o id solicitado
    * criar: cria uma pessoa a partir de um cnpj (o cnpj precisa estar formatado)
    
* transferencia-resource
    * buscarTodos: retorna todas as transferencias de credito realizadas
    * buscaPorId: retorna uma transferencia de credito pelo id solicitado