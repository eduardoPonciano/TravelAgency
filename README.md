# TravelAgency
Este é um exemplo de projeto de uma Agencia de viagem, onde a cada reserva feita pelo usuario, um quarto, um carro e asento de avião são reservados.
Desenvolvido com spring boot, com a finalidade de gerar microserviços, utilizando ActiveMQ para mensageria assíncrona, eventos Publisher/Subscriber usando ApplicationEventPublisher, banco de dados PostgreSQL e uma arquitetura modular para facilitar a visualização dos módulos. para a parte MVC foi utilizado Thymeleaf para o front-end.

# Arquitetura e Design
A aplicação segue uma arquitetura de microserviços, onde cada funcionalidade específica é implementada como um serviço independente. Um gateway é utilizado como um ponto de entrada único para a aplicação, que é consumido pelo serviço de front-end para interagir com os diferentes módulos da aplicação.

# Serviços de CRUD e Reserva
Pensando na responsabilidade e coerência do domínio, os serviços de CRUD e os serviços de reserva estão no mesmo módulo com suas respectivas responsabilidades (hotel, carro, avião, usuário), garantindo assim o compartilhamento de lógicas e dados relevantes as funcionalidades.

# Reserva através de Fila e Eventos
Para realizar as reservas de viagens, os serviços utilizam um sistema de fila e eventos. Quando um usuário confirma uma reserva, os dados são enviados para uma fila de mensagens associada ao serviço responsável (por exemplo, serviço de reserva de hotel, serviço de reserva de carro). O serviço correspondente então processa a reserva, interagindo com o banco de dados e realizando as operações necessárias.

Essa abordagem permite uma comunicação assíncrona entre os serviços, garantindo a escalabilidade e a resiliência do sistema. Além disso, o uso de eventos facilita a implementação de padrões de retry e rollback em caso de falhas durante o processamento da reserva.

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

.Configure ou instale o PostgreSQL, e recomendo utilizar uma base de dados para cada módulo ou separar ppr schemas.
.Apos a configuração das credenciais do banco de dados, preencha os arquivos de propriedades de cada módulo

Caso necessite instalar, pode-se facilmente utilizar a imagem docker do postgres

`docker pull postgres`

`docker run -p 5432:5432 -e POSTGRES_PASSWORD=1234 postgres`

 Antes de iniciar a aplicação, prepare a base de dados executando os seguintes passos:
    
- Configure a base para cada modulo caso necessario. Atualmente estão distribuidos em schemas diferentes, separados por módulos com os seguintes nomes: `modulo_car_rental`, `modulo_flight`, `modulo_hotel` e `modulo_user`.
    
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

