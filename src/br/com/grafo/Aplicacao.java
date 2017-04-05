package br.com.grafo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author gustavo.souza
 *
 */
public class Aplicacao {

	public static void main(String args[]) {

		Grafo grafo = construirGrafo(Constantes.ARQUIVO_GRAFO);
		Dijkstra algoritmo = new Dijkstra();

		// The distance of the route A-B-C
		System.out.println("Output #1: " + algoritmo.calculaDistancia(grafo, new String[] { "A", "B", "C" }));

		// The distance of the route A-D
		System.out.println("Output #2: " + algoritmo.calculaDistancia(grafo, new String[] { "A", "D" }));

		// The distance of the route A-D-C
		System.out.println("Output #3: " + algoritmo.calculaDistancia(grafo, new String[] { "A", "D", "C" }));

		// The distance of the route A-E-B-C-D
		System.out.println("Output #4: " + algoritmo.calculaDistancia(grafo, new String[] { "A", "E", "B", "C", "D" }));

		// The distance of the route A-E-D
		System.out.println("Output #5: " + algoritmo.calculaDistancia(grafo, new String[] { "A", "E", "D" }));

		// The length of the shortest route (in terms of distance to travel)
		// from A to C
		List<Vertice> resultado8 = algoritmo.encontrarMenorCaminho(grafo, grafo.encontrarVertice("A"),
				grafo.encontrarVertice("C"));

		String[] caminho8 = new String[resultado8.size()];

		for (int i = 0; i < resultado8.size(); i++) {
			caminho8[i] = resultado8.get(i).getDescricao();
		}

		System.out.println("Output #8: " + algoritmo.calculaDistancia(grafo, caminho8));

		// The length of the shortest route (in terms of distance to travel)
		// from B to B
		List<Vertice> resultado9 = algoritmo.encontrarMenorCaminho(grafo, grafo.encontrarVertice("B"),
				grafo.encontrarVertice("B"));

		String[] caminho9 = new String[resultado9.size()];

		for (int i = 0; i < resultado9.size(); i++) {
			caminho9[i] = resultado9.get(i).getDescricao();
		}

		System.out.println("Output #9: " + algoritmo.calculaDistancia(grafo, caminho9));

	}

	/**
	 * Metodo que constrói o grafo lendo as informações do arquivo
	 * 
	 * @param nomeArquivo
	 * @return
	 */
	public static Grafo construirGrafo(String arquivo) {

		Grafo grafo = new Grafo();
		String linha;
		Vertice vertice;
		String vertices[];
		ArrayList<String[]> lista = new ArrayList<String[]>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(arquivo)));

			Map<String, Vertice> mapa = new HashMap<String, Vertice>();

			while ((linha = br.readLine()) != null) {

				if (linha.contains(",")) {
					lista.add(linha.split("/"));
					vertices = lista.get(0)[0].split(",");

					vertice = (Vertice) mapa.get(vertices[0]);
					if (vertice == null)
						vertice = new Vertice();

					List<Vertice> vizinhosAtual = new ArrayList<Vertice>();
					List<Aresta> arestasAtual = new ArrayList<Aresta>();
					vertice.setDescricao(vertices[0]);
					mapa.put(vertices[0], vertice);

					if (linha.contains("/")) {

						String pesoArestas[] = lista.get(0)[1].split(",");

						for (int i = 1; i < vertices.length; i++) {
							Vertice vit = mapa.get(vertices[i]);
							if (vit == null)
								vit = new Vertice();
							vit.setDescricao(vertices[i]);
							vizinhosAtual.add(vit);
							mapa.put(vertices[i], vit);

							Aresta ait = new Aresta(vertice, vit);
							ait.setPeso(Integer.parseInt(pesoArestas[i - 1]));
							arestasAtual.add(ait);

						}
						vertice.setVizinhos(vizinhosAtual);
						vertice.setArestas(arestasAtual);

					}

				}

				// Vertices finais
				else {

					// v = g.encontrarVertice(linha);
					vertice = (Vertice) mapa.get(linha);
					if (vertice == null)
						vertice = new Vertice();
					vertice.setDescricao(linha);
					mapa.put(linha, vertice);

				}

				grafo.adicionarVertice(vertice);
				lista.clear();

			}

			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		// Retornando o grafo
		return grafo;
	}

}
