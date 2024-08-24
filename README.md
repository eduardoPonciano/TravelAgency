# TravelAgency
Este é um exemplo de projeto de uma Agencia de viagem, onde a cada reserva feita pelo usuario, um quarto, um carro e asento de avião são reservados.
Desenvolvido com spring boot, com a finalidade de gerar microserviços, utilizando ActiveMQ para mensageria assíncrona, eventos Publisher/Subscriber usando ApplicationEventPublisher, banco de dados PostgreSQL e uma arquitetura modular para facilitar a visualização dos módulos. para a parte MVC foi utilizado Thymeleaf para o front-end.

# Módulos do Projeto
- **Módulo app:** que utiliza um mvc com thymelead para interface amigavel com o usuario
- **Módulo de Aluguel de Carros:** Gerencia as operações relacionadas ao aluguel de carros.
- **Módulo de Reservas de Hotéis:** Lida com as operações de reserva de hotéis.
- **Módulo de Reservas de Passagem aerea:**  Gerencia as operações relacionadas a compra de passagem area
- **Modulo de controle de usuarios:**  Gerencia acesso e autenticação dos usuarios.

# Arquitetura e Design

A aplicação segue uma arquitetura de microserviços, com cada funcionalidade implementada como um serviço independente. Possui um modulo gateway como ponto de entrada único para a aplicação, roteando as solicitações para os diferentes módulos.

Os módulos de serviço (hotel, car, airplane, user) têm responsabilidade específica, interagindo com o banco de dados PostgreSQL para operações de CRUD. Cada módulo pode se comunicar com outros serviços para oferecer funcionalidades mais complexas de modo assíncrono, utilizando eventos através de tópicos do serviço de mensageria, desencadeando ações nos outros serviços sem precisar conhecer detalhes da implementação dos mesmos.

A comunicação assíncrona entre os módulos é suportada pelo ActiveMQ, que facilita o envio de mensagens e eventos entre eles. Isso permite um fluxo de trabalho mais eficiente e resiliente, além de possibilitar a implementação de padrões de retry e rollback em caso de falhas.

Essa arquitetura modular e distribuída garante escalabilidade e flexibilidade, além de facilitar a manutenção e o desenvolvimento futuros. A imagem abaixo ilustra a arquitetura geral do sistema.
![Descrição da imagem](https://github.com/eduardoPonciano/TravelAgency/blob/main/docs/img/arch.png)

# Serviços de CRUD e Reserva
Pensando na responsabilidade e coerência do domínio, os serviços de CRUD e os serviços de reserva estão no mesmo módulo com suas respectivas responsabilidades (hotel, carro, avião, usuário), garantindo assim o compartilhamento de lógicas e dados relevantes as funcionalidades.

# Reserva através de Fila e Eventos
Para realizar as reservas de viagens, os serviços utilizam um sistema de fila e eventos. Quando um usuário confirma uma reserva, os dados são enviados para uma fila de mensagens associada ao serviço responsável (por exemplo, serviço de reserva de hotel, serviço de reserva de carro). O serviço correspondente então processa a reserva, interagindo com o banco de dados e realizando as operações necessárias.

Essa abordagem permite uma comunicação assíncrona entre os serviços, garantindo a escalabilidade e a resiliência do sistema. Além disso, o uso de eventos facilita a implementação de padrões de retry e rollback em caso de falhas durante o processamento da reserva.


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
- PostgreSQL

# Configuração do Projeto

1. **Clonar o Repositório:**
   
   Execute o comando abaixo para clonar o repositório do projeto

   `git clone https://github.com/eduardoPonciano/TravelAgency.git`

2. **Configuração com Docker Compose**
   
   Para facilitar a configuração do ambiente de execução, você pode utilizar o Docker Compose para subir o banco de dados PostgreSQL e a fila ActiveMQ em um único comando.
   - .**Passo 1**: Certifique-se de que o Docker e o Docker Compose estejam instalados em sua máquina.
   - .**Passo 2**: No diretório raiz do projeto, execute o comando a seguir para subir o banco de dados PostgreSQL e a fila ActiveMQ:
     `docker-compose up -d`
     
     Este comando irá iniciar os serviços do banco de dados e da fila em segundo plano. Verifique a saída do terminal para garantir que ambos os serviços foram iniciados corretamente.
   - . **Passo 3:** Após o comando, os serviços devem estar disponíveis nas seguintes portas:
     * Banco de dados PostgreSQL: localhost:5432
     * Fila ActiveMQ: localhost:61616 (para conexões de aplicativos) e localhost:8161 (para acesso ao console web)

3. **Configuração do Banco de Dados PostgreSQL:**

    - Caso utilize uma **outra instancia** do PostgreSQL ou outra base de dados:
      - É recomendavel utilizar uma base de dados para cada módulo ou ao menos, separar por schemas.
      - Altere os dados referente o banco nos arquivos de propriedades de cada módulo

4. **Compilação e Execução:**

   Após subir os serviços com Docker Compose, compile e execute cada módulo separadamente 

6. **Acesso ao Front-end:**

   Após a execução dos módulos, acesse o front-end da aplicação no navegador:
   ` http://localhost:8080.`
   CAso queria testar os serviços, utilize o arquivo collection no Postman. O arquivo está disponível no diretório **resources** do módulo **booking-app**.

# Observações

  Certifique-se de que todos os módulos estejam configurados corretamente antes de executar o projeto.

  Verifique os arquivos de log em caso de erros durante a execução do projeto.

## Créditos

  Para dar agilidade no desevolvimento do front, foram utilizados estruturas de interfaces e arquitetura MVC desenvolvida inicialmemte por Kaya, disponivel em https://github.com/kayaalpkoker/spring-mvc-hotel-booking

