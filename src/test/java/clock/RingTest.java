package clock;

import junit.framework.TestCase;

public class RingTest extends TestCase {
    //Criar uma classe generica que representara uma Lista circular (x)
    //Essa lista circular manterá um elemento atual (posição do ponteiro atual dentro da lista)(x)

    //Tem que ter os métodos:
        //Retornar e remover elemento atual
        //Avança e retroceder o ponteiro atual
        //Adicionar elemento (x)

    // A classe deve implementar a interface Iterable<T> (x)
    // Para permitir que os clientes percorram todos os elementos começando do ponteiro atual (x)
        // Utilizando um loop for-each.(x)

    // Tem que ter o tratamento de excessões

    //Criar a própria estrutura de nós usando uma classe interna (aninhada). (x)
    // Cada nó deve conter: (x)
        // O elemento de dados. (x)
        //Uma referência para o próximo nó no círculo. (x)
        //Uma referência para o nó anterior no círculo. (x)

    //Garanta que a estrutura mantém sua natureza circular: (x)
        //O primeiro nó deve referenciar o último nó como seu anterior. (x)
        //O último nó deve referenciar o primeiro nó como seu próximo. (x)

    //Obs: Se a lista não é vazia e adiciono um novo elemento: (x)
        // o "nó_proximo" do elemento adicionado será o no_proximo do elemento atual (x)
        // o "no_proximo" do elemento atual, será o elemento adicionado (x)
        // o "nó_anterio" do elemento adicionado será o elemento atual (x)
        // o "no_anterioe" do elemento atual, continuara o mesmo. (x)

    //Obs2: Se a lista estiver vazia e adiciona um novo elemento: (x)
        // O elemento será o único nó na lista circular. Portanto, ele referenciará a si mesmo como próximo e anterior. (x)
        // Dessa forma, a circularidade da lista é mantida mesmo com apenas um elemento. (x)
        // E o elemento atual apontará para o elemento adicionado automaticamente. (x)

    public void testCreate(){
        Ring<String> ring = new Ring<>();
        assertEquals(0, ring.size());

        String name1 = "Name1";
        ring.add(name1);
        assertTrue(ring.hasElement(name1));
        assertEquals(1, ring.size());

        String name2 = "Name2";
        ring.add(name2);
        assertTrue(ring.hasElement(name2));
        assertEquals(2, ring.size());

        String nameNotAdded = "name Not Added";
        assertFalse(ring.hasElement(nameNotAdded));
    }
}
