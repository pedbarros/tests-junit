package negocio;

import exception.IdadeNaoPermitidaException;
import model.Cliente;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;

import org.junit.Test;

public class GerenciadoraClientesTest {

    private GerenciadoraClientes gerClientes;
    private int idCliente1 = 1;
    private int idCliente2 = 2;

    @Before
    public void setUp() {
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
    public void testPesquisaClienteExistente() {
        Cliente cliente = gerClientes.pesquisaCliente(idCliente1);

        assertThat(cliente.getId(), is(idCliente1));
        assertThat(cliente.getEmail(), is("gugafarias@gmail.com"));
    }

    /**
     * Teste básico da pesquisa de um cliente que não exista
     *
     * @author Pedro Barros
     * @date 09/11/2018
     */
    @Test
    public void testPesquisaClienteInexistente() {
        Cliente cliente = gerClientes.pesquisaCliente(1001);

        Assert.assertNull(cliente);
        //assertThat(cliente.getEmail(), is("gugafarias@gmail.com"));
    }

    /**
     * Teste básico da remoção de um cliente a partir do seu ID.
     *
     * @author Pedro Barros
     * @date 09/11/2018
     */
    @Test
    public void testRemoveClienteExistente() {
        boolean clienteRemovido = gerClientes.removeCliente(idCliente2);

        assertThat(clienteRemovido, is(true));
        assertThat(gerClientes.getClientesDoBanco().size(), is(idCliente1));
        Assert.assertNull(gerClientes.pesquisaCliente(idCliente2));
    }

    /**
     * Teste básico da remoção de um cliente que não exista
     *
     * @author Pedro Barros
     * @date 09/11/2018
     */
    @Test
    public void testRemoveClienteInexistente() {
        boolean clienteInexistente = gerClientes.removeCliente(1001);

        Assert.assertFalse(clienteInexistente);
        assertThat(gerClientes.getClientesDoBanco().size(), is(2));
    }

    /**
     * Validação da idade de um cliente quando a mesma está no intervalo
     * permitido.
     *
     * @author Pedro Barros
     * @throws IdadeNaoPermitidaException
     * @date 09/11/2018
     */
    @Test
    public void testClienteIdadeAceitavel() throws IdadeNaoPermitidaException {

        /* ========== Montagem do Cenário ========== */
        Cliente cliente = new Cliente(1, "Gustavo", 25, "guga@gmail.com", 1, true);

        /* ========== Execução ========== */
        boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

        /* ========== Verificações ========== */
        assertTrue(idadeValida);
    }

    /**
     * Validação da idade de um cliente quando a mesma está no intervalo
     * permitido.
     *
     * @author Pedro Barros
     * @throws IdadeNaoPermitidaException
     * @date 09/11/2018
     */
    @Test
    public void testClienteIdadeAceitavel_02() throws IdadeNaoPermitidaException {

        /* ========== Montagem do Cenário ========== */
        Cliente cliente = new Cliente(1, "Gustavo", 18, "guga@gmail.com", 1, true);

        /* ========== Execução ========== */
        boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

        /* ========== Verificações ========== */
        assertTrue(idadeValida);
    }
    
    
    /**
     * Validação da idade de um cliente quando a mesma está no intervalo
     * permitido.
     *
     * @author Pedro Barros
     * @throws IdadeNaoPermitidaException
     * @date 09/11/2018
     */
    @Test
    public void testClienteIdadeAceitavel_03() throws IdadeNaoPermitidaException {

        /* ========== Montagem do Cenário ========== */
        Cliente cliente = new Cliente(1, "Gustavo", 65, "guga@gmail.com", 1, true);

        /* ========== Execução ========== */
        boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

        /* ========== Verificações ========== */
        assertTrue(idadeValida);
    }
    
    
    /**
     * Validação da idade de um cliente quando a mesma está no fora do intervalo
     *
     * @author Pedro Barros
     * @throws IdadeNaoPermitidaException
     * @date 09/11/2018
     */
    @Test
    public void testClienteIdadeAceitavel_04() throws IdadeNaoPermitidaException {

        /* ========== Montagem do Cenário ========== */
        Cliente cliente = new Cliente(1, "Gustavo", 17, "guga@gmail.com", 1, true);

        /* ========== Execução ========== */
        try{
            boolean idadeValida = gerClientes.validaIdade(cliente.getIdade()); 
            fail();
        }catch(Exception e){
            assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
        } 
    }
    
    /**
     * Validação da idade de um cliente quando a mesma está no fora do intervalo
     *
     * @author Pedro Barros
     * @throws IdadeNaoPermitidaException
     * @date 09/11/2018
     */
    @Test
    public void testClienteIdadeAceitavel_05() throws IdadeNaoPermitidaException {

        /* ========== Montagem do Cenário ========== */
        Cliente cliente = new Cliente(1, "Gustavo", 66, "guga@gmail.com", 1, true);

        /* ========== Execução ========== */
        try{
            boolean idadeValida = gerClientes.validaIdade(cliente.getIdade()); 
            fail();
        }catch(Exception e){
            assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
        } 
    }

    @After
    public void tearDown() {
        gerClientes.limpa();
    }

}
