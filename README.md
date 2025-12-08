Sistema de Gestão UBS

Este é um sistema de gestão de Unidade Básica de Saúde (UBS), desenvolvido como projeto da disciplina de Programação Orientada a Objetos (POO).
O objetivo é aplicar conceitos fundamentais de POO e a arquitetura do Spring Boot para criar uma solução modular, escalável e bem estruturada, incluindo CRUDs completos de Pacientes, Médicos e Consultas.

O sistema expõe APIs REST que permitem o gerenciamento de todos os recursos envolvidos no atendimento básico da unidade de saúde.

Funcionalidades:

- Cadastro de Pacientes: criação, listagem, atualização e exclusão.

- Cadastro de Médicos: CRUD completo, com especialidade.

- Gestão de Consultas: criação de consultas vinculando Paciente + Médico, listagem, edição e exclusão.

- API REST completa: endpoints REST seguindo boas práticas de arquitetura.

- Validações e tratamento de exceções: respostas adequadas para erros de entrada e regras de negócio.

Conceitos de POO Aplicados:

O projeto foi estruturado utilizando os pilares fundamentais da Programação Orientada a Objetos em Java:

- Encapsulamento: uso de atributos privados com getters e setters para controle seguro dos dados.
- Herança: possibilidade de extensão do modelo (ex.: classe Pessoa → Médico/Paciente, caso esteja na sua modelagem).
- Polimorfismo: métodos sobrescritos, e separação de responsabilidades via interfaces e abstrações.
- Abstração: cada classe representa claramente seu papel no domínio (Paciente, Médico, Consulta, Serviço, Repositório, etc.).

Além disso, o sistema utiliza:

- Estruturas de dados: como ArrayList para armazenamento temporário e manipulação de listas.
- Tratamento de exceções: com try/catch, validações e respostas adequadas via Spring (ResponseEntity e exceções personalizadas).

Tecnologias Utilizadas:

- Java 17+
- Spring Boot
- Spring Web (REST)
- Maven

Requisitos do Sistema

- JDK 17 ou superior
- Maven instalado
- IDE recomendada: IntelliJ, VS Code ou Eclipse

Instalação e Execução:

Siga os passos abaixo para clonar o repositório e executar o projeto:

1. Clone o repositório
git clone https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git
cd NOME_DO_REPOSITORIO

Atualize o arquivo src/main/resources/application.properties com suas credenciais:

spring.datasource.url=jdbc:mysql://localhost:3306/gestaoubs
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update

3. Compile e Execute
mvn clean install
mvn spring-boot:run


Ou execute a classe principal pela IDE:

src/main/java/.../GestaoUbsApplication.java

Endpoints Principais (CRUD):

- Pacientes
Método	Endpoint	Descrição
POST	/pacientes	Criar paciente
GET	/pacientes	Listar pacientes
GET	/pacientes/{id}	Buscar por ID
PUT	/pacientes/{id}	Atualizar
DELETE	/pacientes/{id}	Excluir

- Médicos
Método	Endpoint	Descrição
POST	/medicos	Criar médico
GET	/medicos	Listar médicos
GET	/medicos/{id}	Buscar por ID
PUT	/medicos/{id}	Atualizar
DELETE	/medicos/{id}	Excluir

- Consultas
Método	Endpoint	Descrição
POST	/consultas	Criar consulta
GET	/consultas	Listar consultas
GET	/consultas/{id}	Buscar por ID
PUT	/consultas/{id}	Atualizar
DELETE	/consultas/{id}	Excluir
Demonstração (Opcional)

Contribuições são bem-vindas.
Sinta-se à vontade para abrir issues ou enviar pull requests.

Autores

Jamili Martins de Oliveira / @Jamili-Martins — Líder Técnico e Desenvolvedor
Maria Clara Gomes da Silva Pollini / @clarap0llini — Deselvolvedor
Vinícius Rafael Rodrigues Queiroz / @Vinicius-SoftwareEngineer — Deselvolvedor

Licença

Licenciado sob a licença MIT.
Consulte o arquivo LICENSE para mais informações.

Este projeto é a culminância da disciplina de Programação II (P2), ministrada pela professora Aeda, e teve como objetivo consolidar os conhecimentos de POO aplicados no desenvolvimento de sistemas Java com Spring Boot.

Agradecimentos:

Spring Boot pela estrutura e praticidade no desenvolvimento.
Professores e colegas que auxiliaram no processo.
