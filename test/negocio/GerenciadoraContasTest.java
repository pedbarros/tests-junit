package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre contas, realizadas pela classe {@link GerenciadoraContas}.
 *
 * @author Pedro Barros
 * @date 09/11/2018
 */
public class GerenciadoraContasTest {

    private GerenciadoraContas gerContas;

    /**
     * Teste básico da transferência de um valor da conta de um cliente para
     * outro, estando ambos os clientes ativos e havendo saldo suficiente para
     * tal transferência ocorrer com sucesso.
     *
     * @author Pedro Barros
     * @date 09/11/2018
     */
    @Test
    public void testTransfereValor() {
        /* ========== Montagem do cenário ========== */

        // criando alguns clientes
        ContaCorrente conta01 = new ContaCorrente(1, 200, true);
        ContaCorrente conta02 = new ContaCorrente(2, 0, true);

        // inserindo os clientes criados na lista de clientes do banco
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);

        gerContas = new GerenciadoraContas(contasDoBanco);

        /* ========== Execução ========== */
        boolean transferidoComSucesso = gerContas.transfereValor(1, 100, 2);

        assertTrue(transferidoComSucesso);
        assertThat(conta01.getSaldo(), is(100.0));
        assertThat(conta02.getSaldo(), is(100.0));
    }

}
