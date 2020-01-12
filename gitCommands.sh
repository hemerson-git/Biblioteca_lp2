git checkout hemerson
clear
echo "Branch: hemerson"

#pede a mensagem de commit ao usuário
echo "Mensagem de commit"
read msg

#adiciona todos os arquivos ao stagging
git add .
clear

#commita os arquivos e faz o push
git commit -m "$msg"
git push
clear

#muda para a branch master
git checkout master
echo "Branch: master"

#faz o merge com a brach hemerson e faz o push
git merge hemerson
clear

#muda de volta para a branch hemerson
git checkout hemerson
echo "Branch: hemerson"

$shell
