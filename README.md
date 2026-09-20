# SistemaFerramentas

Aplicativo Android para gerenciamento de ferramentas, com banco de dados
local em SQLite (armazenado no próprio aparelho).

## Como abrir e rodar

1. Abra o **Android Studio Narwhal**.
2. Na tela inicial, clique em **Open** (ou File > Open).
3. Selecione a pasta `SistemaFerramentas` (a pasta que contém o arquivo
   `settings.gradle`).
4. Aguarde o Android Studio baixar o Gradle e sincronizar o projeto
   (é necessário estar conectado à internet na primeira sincronização).
5. Conecte um celular Android (com depuração USB ativada) ou crie um
   emulador (Device Manager > Create Device).
6. Clique no botão verde de **Run ▶** (ou Shift+F10).

## Funcionalidades

- **Cadastrar Ferramenta**: cadastra nome, quantidade e localização.
- **Alterar Dados**: escolhe uma ferramenta na lista e edita os dados.
- **Consultar Ferramenta**: lista todas as ferramentas cadastradas.
- **Excluir Ferramenta**: toca em uma ferramenta e confirma a exclusão.

Todos os dados ficam salvos em um banco SQLite (`ferramentas.db`) que
persiste mesmo depois de fechar o app.

## Tecnologias

- Java
- SQLiteOpenHelper (banco de dados local)
- Material Components
