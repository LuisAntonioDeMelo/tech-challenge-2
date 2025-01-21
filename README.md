"# tech-challenge-2" 

Para rodar esse projeto e necessario ter o kind rodando na maquina

rodar kind linux :

curl -Lo ./kind https://kind.sigs.k8s.io/dl/latest/kind-linux-amd64
chmod +x ./kind
sudo mv ./kind /usr/local/bin/kind

necessario ter o docker na maquina
para configurar o docker.internal 
basta rodar o host-docker-internal.sh
