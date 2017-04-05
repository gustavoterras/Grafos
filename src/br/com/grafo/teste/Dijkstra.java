package br.com.grafo.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.grafo.Aplicacao;
import br.com.grafo.Grafo;
import br.com.grafo.Vertice;

public class Dijkstra {

	private static final String ARQUIVO = "../Grafos/src/grafo.txt";
	private String[] caminho;
	private Grafo grafo;

	@Before
	public void inicializaGrafo() {

		grafo = new Grafo(Aplicacao.construirGrafo(ARQUIVO));

		caminho = new String[] { "A", "B", "C" };
	}

	@Test
	public void calculaMenorDistanciaComBaseNaRota() {

		br.com.grafo.Dijkstra dijkstra = new br.com.grafo.Dijkstra();

		assertEquals(9, dijkstra.calculaDistancia(grafo, caminho));
	}

	@Test
	public void encontrarMenorCaminhoComBaseNaOrigemDestino() {

		br.com.grafo.Dijkstra dijkstra = new br.com.grafo.Dijkstra();

		List<Vertice> vertices = dijkstra.encontrarMenorCaminho(grafo, grafo.encontrarVertice("A"), grafo.encontrarVertice("C"));

		caminho = new String[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			caminho[i] = vertices.get(i).getDescricao();
		}

		assertEquals(9, dijkstra.calculaDistancia(grafo, caminho));

	}

}
