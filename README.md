# TravelAgency
Este é um exemplo de projeto de uma Agencia de viagem, onde a cada reserva feita pelo usuario, um quarto, um carro e asento de avião são reservados.
Desenvolvido com spring boot, com a finalidade de gerar microserviços, utilizando ActiveMQ para mensageria assíncrona, eventos Publisher/Subscriber usando ApplicationEventPublisher, banco de dados PostgreSQL e uma arquitetura modular para facilitar a visualização dos módulos. para a parte MVC foi utilizado Thymeleaf para o front-end.

# Módulos do Projeto
- **Módulo app:** que utiliza um mvc com thymelead para interface amigavel com o usuario
- **Módulo de Aluguel de Carros:** Gerencia as operações relacionadas ao aluguel de carros.
- **Módulo de Reservas de Hotéis:** Lida com as operações de reserva de hotéis.
- **Módulo de Reservas de Passagem aerea:**  Gerencia as operações relacionadas a compra de passagem area
- **Modulo de controle de usuarios:**  Gerencia acesso e autenticação dos usuarios.

# Technology Stack
  - **Backend**:
  - Java 17
  - Spring Boot
  - MVC Architecture
- **Frontend**:
  - Thymeleaf
- **Database**:
  - Postgres
- **Security**:
  - Spring Security

# Pré-requisitos
- Java Development Kit (JDK) 11 ou superior
- Maven 3.x
- ActiveMQ
- PostgreSQ

# Configuração do Projeto

1. **Configuração do ActiveMQ**
   
.Certifique-se de que o ActiveMQ esteja instalado e em execução.

.Configure a URL do ActiveMQ nos arquivos de propriedades de cada módulo.

pode-se utilizar a imagem docker do activemq


`docker pull rmohr/activemq`

`docker run -d --name activemq -p 61616:61616 -p 8161:8161 rmohr/activemq`


2. **Configuração do Banco de Dados PostgreSQL:**

.Instale o PostgreSQL e crie um banco de dados para cada módulo.
.A configuração do banco nos modulos Configure as credenciais do banco de dados nos arquivos de propriedades de cada módulo

pode-se utilizar a imagem docker do activemq

`docker pull postgres`

`docker run -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres`

 Antes de iniciar a aplicação, prepare a base de dados executando os seguintes passos:
    
- Execute os scripts SQL para criar os esquemas dos módulos. Os scripts devem ser ajustados conforme necessário, pois os esquemas atualmente estão separados por módulos com os seguintes nomes: `modulo_car_rental`, `modulo_flight`, `modulo_hotel` e `modulo_user`.
    
- Certifique-se de preparar os dados necessários para cada módulo executando os scripts de carga apropriados.
  os scripts se encontram no modulo correspondente [travel-agency-app ,travel-agency-car-rental e travel-agency-hotel]

3. **Clonar o Repositório:**
   
`git clone https://github.com/seu-usuario/seu-projeto.git`

4. **Compilação e Execução:**

  execute todos os modulos separadamente

6. **Acesso ao Front-end:**

Abra o navegador e acesse http://localhost:8080.

ou utilize o arquivo colection no postman. o arquivo esta disponilizado no diretorio resources do modulo **travel-agency-app**

# Observações
Certifique-se de que todos os módulos estejam configurados corretamente antes de executar o projeto.

Verifique os arquivos de log em caso de erros durante a execução do projeto.

## Créditos

Para dar agilidade no desevolvimento do front, foram utilizados estruturas de interfaces e arquitetura MVC desenvolvida inicialmemte por Kaya, disponivel em https://github.com/kayaalpkoker/spring-mvc-hotel-booking

