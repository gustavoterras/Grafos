package br.com.grafo.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.com.grafo.Aplicacao;
import br.com.grafo.Constantes;
import br.com.grafo.Grafo;
import br.com.grafo.Vertice;

/**
 * 
 * @author gustavo.souza
 *
 */
public class Dijkstra {

	private static br.com.grafo.Dijkstra dijkstra;
	private static String[] caminho;
	private static Grafo grafo;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void inicializaGrafo() {

		grafo = Aplicacao.construirGrafo(Constantes.ARQUIVO_GRAFO);

		dijkstra = new br.com.grafo.Dijkstra();
	}

	@Test
	public void calculaMenorDistanciaComBaseNaRota() {

		caminho = new String[] { "A", "B", "C" };

		assertEquals(9, dijkstra.calculaDistancia(grafo, caminho));
	}

	@Test(expected = NullPointerException.class)
	public void VerificaErrorMenorDistanciaComBaseNaRota() {

		caminho = null;

		dijkstra.calculaDistancia(grafo, caminho);

		exception.expect(NullPointerException.class);

		exception.expectMessage(Constantes.CAMINHO_NULO);
	}

	@Test
	public void encontrarMenorCaminhoComBaseNaOrigemDestino() {

		List<Vertice> vertices = dijkstra.encontrarMenorCaminho(grafo, grafo.encontrarVertice("A"), grafo.encontrarVertice("C"));

		caminho = new String[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			caminho[i] = vertices.get(i).getDescricao();
		}

		assertEquals(9, dijkstra.calculaDistancia(grafo, caminho));

	}

}
