/* 

Oi Prof,

espero tudo esteja bem com vc aqui eu fiz o projeto agenda
FOI DIFICIL demais nao posso te explicar como eu estava preocupado com este projeto ja que outros projetos dependiam desse.

Eu fiz sozinho denovo com muita pesquisa no youtube pq eu ja tava muito perdido com a disciplina e posso dizer q aprendi muito aqui

Eu fiquei preocupado pq eram tres classes e ficava bem pessado pensar em como todas essas vao interagir 
mas bom eu comecei como sempre seguindo o diagrama de vc posso garantir q isso ajuda muito

eu fui criando classe por classe e logo comecei com a classe List q neste caso eh a Agenda em si 
fui com o primeiro metodo q originalmente tinha assim

int findContact(String name){
        int index = -1;
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).name == name){
                index = i; 
            }
        }
        return index;
    }

mas nao estava do jeito q vc queria ja que precisava ordenar os nomes e seus fones juntos e este codigo daqui so ordenava por ordem de entrada
entao fiz uma pesquisa na net e encontrei um iterador.
com o iterador estava dando um monte de problema ate pq meu codigo nao tava dando certo e foi ate q segui pesquisando e tive q acrescentar um break nesse iterador ja que
cada vez q eu passava outro nome ou fone o programa se fechava.

logo assim vc me ajudou numa parte do codigo no removecontacts ja q nao estava removendo nada
tinha um erro sumamente pequeno q era em vez de ter .equals eu tinha == e resolveu ai meu codigo gracas a deus
ai tu tambem me ajudou com ter esse for que ia no codigo 

o search foi ficil demais eu acho pq nao so era procurar pelo nome mais tambem procurar por nomes iguais ou similares
ai aprovetei para utilizar o .contains para resolver esse codigo dai
tambem aprovetei foi para fazer outro search so para os fones e dexei na mesma caixa no main interativo para procurar o q o usuario precisar

David este codigo demorou muito tempo e fiquei bem estressado mas posso garantir assim msm como o topic q a gente aprende muito 
e gracas a este codigo eu resolvi logo o dos favoritos

o unico q posso falar e sei la se vc pode me ajudar a entender eh o metodo validate pq ai so botei o return.


*/