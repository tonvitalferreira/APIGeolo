# API Geolo

Está API foi desenvolvida em Java com Spring boot.

## • O que faz?
    • Mostra todos os prestadores de serviços mais pertos de uma localização(latitude, longitude)

## • Executar o deploy
    1 - clone ou baixe o .zip do projeto
    2 - depois, no terminal, entre na pasta do projeto
    3 - execute: mvn package 
    4 - acesse a pasta "target", que foi gerada na build
    5 - execute: java -jar api-geolo-0.0.1-SNAPSHOT.jar

## • Rotas:

    • **[GET]** - /providers/especialidade/latLong
        - especialidade: String
        - latLong:       String
            • format: latitude,longitude
        - exemplo:
            http://localhost:1234/providers/Cardiologista/-8.892890,-36.494820
        - retorno: JSON com todos prestadores de serviços perto de LatLong(-8.892890,-36.494820)

    • [POST] - /providers
        - jsonBody: 
                - nome: String
                - especialidade: String
                - latitude: double
                - longitude: double
        - exemplo jsonBody:
            {
                "nome": "Aurora",
                "especialidade": "Dentista",
                "latitude": -8.892890,
                "longitude": -36.494820
            }
        - retorno: JSON com mensagem de sucesso ou erro
        - adiciona um novo prestador de serviço ao sistema

    • [PUT] - /providers
        - jsonBody:
                - nome: String
                - especialidade: String
        - exemplo jsonBody:
            {
                "nome": "Aurora",
                "especialidade": "Cardiologista"
            }
        - retorno: JSON com mensagem de sucesso ou erro
        - adiciona uma nova especialidade à, Aurora.
