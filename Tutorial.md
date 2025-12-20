Comandos git que meu namorado me obrigou a decorar
===============================================

Ver quais arquivos estão preparados para o commit
------------------------------------------------------------------  
git status

Adicionar arquivos ao stage (preparar para o commit)
------------------------------------------------------------------
git add .
git add nome_do_arquivo.txt

Fazer o commit (salvar as mudanças preparadas)
------------------------------------------------------------------
git commit -m "Mensagem do commit"
git commit -am "Mensagem do commit"  # Adiciona e comita em um só comando'

Subir mudanças para o repositório remoto (branch)
------------------------------------------------------------------
git push origin nome_da_branch
git push origin main  # Subir para a branch main
git push origin master  # Subir para a branch master

Puxar mudanças do repositório remoto (branch)
------------------------------------------------------------------
git pull origin nome_da_branch
git pull origin main  # Puxar da branch main
git pull origin master  # Puxar da branch master

Criar uma nova branch
------------------------------------------------------------------
git checkout -b nome_da_nova_branch

Trocar para uma branch existente
------------------------------------------------------------------
git checkout nome_da_branch_existente

Ver o ultimo commit
------------------------------------------------------------------
git log --oneline


