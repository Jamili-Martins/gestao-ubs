Sistema de Gestão UBS

Este é um sistema de gestão de Unidade Básica de Saúde (UBS), desenvolvido como projeto da disciplina de Programação Orientada a Objetos (POO) na Universidade de Pernambuco – Campus Garanhuns.

O objetivo é aplicar os princípios fundamentais de POO e a arquitetura em camadas do Spring Boot para construir uma aplicação modular, organizada e com responsabilidades bem definidas.
O sistema disponibiliza uma API REST completa para gerenciamento de Pacientes, Médicos, Consultas e Relatórios.

Funcionalidades:
Pacientes
Cadastro completo (criar, listar, editar, excluir)
Dados: nome, CPF, telefone
Médicos
CRUD completo
Informações: nome, especialidade e CRM

Consultas
Criação de consultas vinculando Paciente + Médico
Verificação automática de conflitos de horário
Edição e exclusão
Listagem geral

Relatórios
Consultas por médico
Consultas por data específica
Consultas por intervalo de datas
Integração com frontend para exibição em tabela e gráficos (Chart.js)
Tratamento estruturado de exceções
Exceções personalizadas (PacienteNaoEncontradoException, HorarioIndisponivelException, etc.)
Responses padronizadas com ApiError
GlobalExceptionHandler para interceptação centralizada
Conceitos de POO Aplicados

O sistema foi planejado para demonstrar domínio dos pilares da Programação Orientada a Objetos:

Encapsulamento
Atributos privados acessados via getters e setters (gerados com Lombok).

Herança
A classe Medico herda de Profissional, aproveitando nome e especialidade.

Polimorfismo
O uso de Profissional como tipo genérico permite que futuros profissionais sejam adicionados sem alterar o restante do sistema.

Abstração
Cada classe representa corretamente uma entidade do domínio da UBS.
Regras de Negócio claro na camada Service
Validação de horários ocupados
Garantia de existência de médico/paciente antes de criar consulta
Geração automática de IDs

Tecnologias Utilizadas:
Java 17
Spring Boot
Spring Web (REST)
Maven
Jackson (serialização JSON)
Lombok
HTML, CSS, JavaScript
Bootstrap
Chart.js



Arquitetura do Sistema

O projeto utiliza arquitetura em camadas, separando responsabilidades:

Controller  → recebe requisições HTTP
Service     → aplica regras de negócio
Repository  → manipula listas em memória e persistência
Persistence → grava e lê arquivos JSON


Essa divisão facilita manutenção, testes, expansões e entendimento do fluxo interno da aplicação.
Persistência em JSON
O sistema não utiliza banco de dados relacional.
Todos os dados são armazenados e recuperados por meio de arquivos JSON, utilizando a classe JsonFileManager.

Caminhos dos arquivos
Consultas: src/main/resources/consultas.json
Médicos: src/main/resources/data/medicos.json
Pacientes: data/pacientes.json
Como funciona a persistência
Serialização: converte objetos Java em JSON
Desserialização: converte JSON em objetos Java
Utiliza ObjectMapper da biblioteca Jackson
Suporte especial para datas (LocalDateTime) via JavaTimeModule
Essa solução atende completamente aos objetivos da disciplina, eliminando a necessidade de um banco de dados real.

Instalação e Execução
1. Clone o repositório
git clone https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git
cd NOME_DO_REPOSITORIO

2. Executar o backend
mvn clean install
mvn spring-boot:run


Ou execute a classe principal:

src/main/java/.../GestaoApplication.java

Endpoints da API

Pacientes
Método	Endpoint	Função
POST	/pacientes	Criar
GET	/pacientes	Listar
GET	/pacientes/{id}	Buscar
PUT	/pacientes/{id}	Atualizar
DELETE	/pacientes/{id}	Remover

Médicos
Método	Endpoint	Função
POST	/medicos	Criar
GET	/medicos	Listar
GET	/medicos/{id}	Buscar
PUT	/medicos/{id}	Editar
DELETE	/medicos/{id}	Remover

Consultas
Método	Endpoint	Função
POST	/consultas	Criar
GET	/consultas	Listar
GET	/consultas/{id}	Buscar
PUT	/consultas/{id}	Editar
DELETE	/consultas/{id}	Remover

Relatórios
/relatorios/consultas/medico/{id}
/relatorios/consultas/data?data=YYYY-MM-DD
/relatorios/consultas/data?inicio=YYYY-MM-DD&fim=YYYY-MM-DD



Frontend

O sistema possui um frontend completo desenvolvido em:

HTML

CSS (Bootstrap)

JavaScript

Chart.js

Inclui:

Dashboard

CRUDs completos (Pacientes, Médicos, Consultas)

Relatórios com tabela + gráfico

Sidebar fixa e layout responsivo

Consumo de API via fetch()



Autores

Jamili Martins de Oliveira — @Jamili-Martins

Maria Clara Gomes da Silva Pollini — @clarap0llini

Vinícius Rafael Rodrigues Queiroz — @Vinicius-SoftwareEngineer



Licença

Licenciado sob a MIT License.
Consulte o arquivo LICENSE para mais informações.



Agradecimentos

Projeto desenvolvido como culminância da disciplina Programação II (P2), ministrada pela professora Aeda,
no curso de Engenharia de Software – UPE Campus Garanhuns (2025.2, 2º período).
