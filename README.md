# Documentação da API de Gerenciamento de Eventos

> ## Pré-requisitos
> - É necessário ter o [Docker](https://www.docker.com/get-started) instalado na sua máquina.
> - Ter o [Docker Compose](https://docs.docker.com/compose/) instalado (geralmente já incluído no Docker).

> ## Passo a Passo para Rodar a API
>
> 1. **Clone o repositório**
>    ```bash
>    git clone https://github.com/seu-usuario/seu-repositorio.git
>    cd seu-repositorio
>    ```
>
> 2. **Construa a imagem Docker**
>    ```bash
>    docker-compose build
>    ```
>
> 3. **Inicie os containers**
>    ```bash
>    docker-compose up
>    ```
>
> 4. **Acesse a API**
>    A API estará disponível em `http://localhost:8000` (ou a porta configurada no seu `docker-compose.yml`).
>
> ## Endpoints da API
> - `GET /eventos` - Lista todos os eventos.
> - `POST /eventos` - Cria um novo evento.
> - `GET /eventos/{id}` - Retorna os detalhes de um evento específico.
> - `PUT /eventos/{id}` - Atualiza um evento existente.
> - `DELETE /eventos/{id}` - Remove um evento.
>
> ## Exemplos de Uso
>
> ### Listar Eventos
> ```bash
> curl -X GET http://localhost:8000/eventos
> ```
>
> ### Criar Evento
> ```bash
> curl -X POST http://localhost:8000/eventos -H "Content-Type: application/json" -d '{"nome": "Evento Teste", "data": "2024-10-30"}'
> ```
>
> ### Atualizar Evento
> ```bash
> curl -X PUT http://localhost:8000/eventos/1 -H "Content-Type: application/json" -d '{"nome": "Evento Atualizado", "data": "2024-11-01"}'
> ```
>
> ### Remover Evento
> ```bash
> curl -X DELETE http://localhost:8000/eventos/1
> ```
>
> ## Contribuições
> Sinta-se à vontade para abrir issues ou pull requests. Sua contribuição é bem-vinda!
>
> ## Licença
> Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
# Crio-BackEnd
