# Tech Challenge 2 : Luis Antônio de Melo Gomes

## Pré-requisitos

Para rodar este projeto, é necessário que o Kind esteja configurado na máquina.

Tambem é necessário que o docker esteja instalado.

para utilizar o webhook é necessario utilizar o ngrok, foi adicionado o ngrok junto a aplicação para subir com docker

e rodar o projeto


Foi criado um arquivo de configuração ./deploy.sh 

ele será responsável por rodar todos os aquivos de configuração para o cluster do docker


### Instalando o Kind no windows:

1. Faça o download do Kind:
   ```bash
   choco install kind
   ```

Torne o arquivo executável:

### Instalando o Kind no Linux:

1. Faça o download do Kind:
   ```bash
   curl -Lo ./kind https://kind.sigs.k8s.io/dl/latest/kind-linux-amd64
   ```

Torne o arquivo executável:

```

	chmod +x ./kind

```

    Mova o executável para uma pasta do sistema:

```
`sudo mv ./kind /usr/local/bin/kind`
```

Configurando o docker.internal:

para linux./host-docker-internal.sh
    Execute o script

`./host-docker-internal.sh:`

**Buildar a imagem** 

`docker build -t devluis27/app-tech-challenge-fast-food:1.3 .`

subir no repositorio docker hub

`docker push  devluis27/app-tech-challenge-fast-food:1.3`
