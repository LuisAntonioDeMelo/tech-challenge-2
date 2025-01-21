#!/bin/bash

# Nome do cluster e arquivo de configuração do Kind
CLUSTER_NAME="tech-challenge"
KIND_CONFIG="kind-config.yaml"

# Diretório contendo os arquivos YAML
YAML_DIR="infra/app"
ls -l $YAML_DIR
# Função para verificar o status do último comando e exibir mensagens apropriadas
check_status() {
  if [ $? -eq 0 ]; then
    echo "Sucesso: $1"
  else
    echo "Erro: $1"
    exit 1
  fi
}

# Passo 1: Criar o cluster com o Kind
echo "Criando o cluster '$CLUSTER_NAME'..."
kind create cluster --name "$CLUSTER_NAME" --config "infra/kind/$KIND_CONFIG"
check_status "Cluster '$CLUSTER_NAME' criado com sucesso."

# Passo 2: Verificar se o diretório de arquivos YAML existe
if [ ! -d "$YAML_DIR" ]; then
  echo "Erro: Diretório $YAML_DIR não encontrado."
    mkdir -p "$YAML_DIR"
  exit 1
fi

# Passo 3: Aplicar cada arquivo YAML individualmente e registrar o status
for yaml_file in "$YAML_DIR"/*.yaml; do
  if [ -f "$yaml_file" ]; then
    echo "Aplicando $yaml_file..."
    kubectl apply -f "$yaml_file"
    check_status "Aplicação de $yaml_file concluída."
  else
    echo "Aviso: Nenhum arquivo YAML encontrado em $YAML_DIR."
  fi
done

echo "Processo de implantação concluído com sucesso."
echo "ficara ativa em alguns segundos"

kubectl get pods -o jsonpath='{.items[*].status.containerStatuses[*].ready}'

#//fica roando o ccurl para verificar se o serviço esta ativo
while [ $(kubectl get pods -o jsonpath='{.items[*].status.containerStatuses[*].ready}' | grep false | wc -l) -gt 0 ]; do
  echo "Aguardando a inicialização dos pods..."
  sleep 10
done

echo "status HPA"
kubectl get hpa 

echo "status dos pods"
kubectl get pods

echo ":::::"
echo "executando efk...."
echo "\n\n"

# Subir monitoramento 
kubectl apply -f infra/monitoring-efk/elastic/elastic-pod.yaml
sleep 1 
kubectl apply -f infra/monitoring-efk/elastic/elastic-service.yaml
sleep 1
kubectl apply -f infra/monitoring-efk/fluentd/fluentd-configmap.yaml
sleep 1
kubectl apply -f infra/monitoring-efk/fluentd/fluentd.yaml
sleep 1
kubectl apply -f infra/monitoring-efk/kibana/kibana-pod.yaml
sleep 1
kubectl apply -f infra/monitoring-efk/kibana/kibana-service.yaml


curl  http://localhost:30000/
echo "Aplicação implantada com sucesso."
echo "Acesse http://localhost:30000 para visualizar a aplicação."