git checkout giovane
clear
echo "Branch: giovane"

#pede a mensagem de commit ao usuário
echo "Teste script giovane"
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

#faz o merge com a brach giovane e faz o push
git merge giovane
git push

#muda de volta para a branch hemerson
git checkout giovane
echo "Branch: giovane"

$shell
