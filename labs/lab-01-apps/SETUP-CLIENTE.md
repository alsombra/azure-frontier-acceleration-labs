# Lab 1 Java - Setup

## Fontes incluidos antes da execucao

O [legado H2 v2](modernizacao-java/legado/README.md) e a [referencia final](modernizacao-java/modernizado/README.md) incluem fontes, testes, POM, configuracao H2 e scripts build/start/bootstrap/stop. Nao incluem JARs ou dependencias. Leia os [comandos e a fixture](modernizacao-java/README.md) antes de executar. A publicacao foi validada estaticamente, sem Java/Maven/runtime.

## Preparacao local

1. Conclua a [preparacao comum](../../PREPARACAO.md). Confirme Windows/arquitetura, VS Code e acesso ao Copilot.
2. Para uma origem Java 8, confirme JDK 8 completo; para o alvo Java 21, confirme JDK 21. Valide o runtime exigido pela entrega, nao apenas a instalacao de um JDK.
3. No caminho fonte, confirme Maven 3.9.x compativel e acesso autorizado aos repositorios de dependencias. Compilado fornecido nao equivale a build feito pelo participante.
4. Copie `modernizacao-java/legado` para uma pasta nova fora do repositorio, conforme os comandos operacionais. Preserve o baseline e modernize outra copia. Para o checkpoint final, copie `modernizacao-java/modernizado` para outro destino novo. Confira POM, src e scripts; nao sobrescreva arquivos preexistentes.
5. Se existir Git, confira raiz, branch e alteracoes; nao descarte trabalho local. ZIP nao implica repositorio Git, e branch nao substitui backup.
6. Revise configuracao antes de iniciar. Use somente dados sinteticos e banco local descartavel. Nao reutilize conexoes Oracle/corporativas ou segredos do projeto original.
7. Mantenha o servidor local em loopback, com porta livre. Nao encerre processos de outras pessoas para liberar uma porta. Confirme start/stop e mecanismo de reseed documentados pela entrega.

H2 em memoria e apenas simulacao local: reiniciar pode apagar os dados. Nao demonstra migracao Oracle ou compatibilidade de SQL/PLSQL.

## Destino Azure condicional

O destino deste roteiro e Azure App Service Linux, uma instancia e plano dedicado. Nao exige Docker, ACR, AKS ou Container Apps. Nao crie recursos durante a preparacao.

Antes de qualquer deploy, confirme assinatura/RG exclusivo, regiao, runtime realmente disponivel e compativel, SKU/preco vigentes, quota, menor privilegio, limite de custo, proprietario e prazo. B1 e apenas candidato pago, nao garantia de disponibilidade nem gratuidade. Um alerta de orcamento nao limita automaticamente o gasto.

Defina e teste a barreira de acesso ANTES do deploy: autenticacao Entra restrita ao grupo autorizado, ou restricao IP explicitamente aprovada com HTTPS. Login Entra generico nao substitui restricao ao grupo. Sem controle de acesso e teste de recusa, nao publique. Desabilite console H2 e endpoints sensiveis; nao exponha escrita anonima.

## Criterio de avanco

Avance somente com copia preservada, ferramentas coerentes e configuracao isolada revisada. Os builds passam `maven-local.xml` como settings de usuario e global; confirme mirror/proxy aprovados conforme a [operacao](modernizacao-java/README.md), sem credenciais no repositorio. O setup nao comprova build, testes ou Azure. Siga o [passo a passo](03-guia-completo.md).