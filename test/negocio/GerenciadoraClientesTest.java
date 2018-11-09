package negocio;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

public class GerenciadoraClientesTest {

    private GerenciadoraClientes gerClientes;
    private int idCliente1 = 1;
    private int idCliente2 = 2;
    
    @Before
    public void setUp(){
        /* =================== MONTANDO O CENÁRIO ======================*/
        // criando alguns clientes
        Cliente cliente01 = new Cliente(idCliente1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(idCliente2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);

        gerClientes = new GerenciadoraClientes(clientesDoBanco);
    }

    /**
     * Teste básico da pesquisa de um cliente a partir do seu ID.
     *
     * @author Pedro Barros
     * @date 09/11/2018
     */
    @Test
    public void testPesquisaCliente() {  
        Cliente cliente = gerClientes.pesquisaCliente(idCliente1);

        assertThat(cliente.getId(), is(idCliente1));
        assertThat(cliente.getEmail(), is("gugafarias@gmail.com"));
    }

    /**
     * Teste básico da remoção de um cliente a partir do seu ID.
     *
     * @author Pedro Barros
     * @date 09/11/2018
     */
    @Test
    public void testRemoveCliente() { 
        boolean clienteRemovido = gerClientes.removeCliente(idCliente2);

        assertThat(clienteRemovido, is(true));
        assertThat(gerClientes.getClientesDoBanco().size(), is(idCliente1));
        Assert.assertNull(gerClientes.pesquisaCliente(idCliente2));
    }
    
    @After
    public void tearDown(){
        gerClientes.limpa();
    }

}
