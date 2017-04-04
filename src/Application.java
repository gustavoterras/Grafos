import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

	public static void main(String args[]) {

		Grafo grafo = new Grafo();
		Dijkstra algoritmo = new Dijkstra();

		grafo.setVertices(lerGrafo("../Grafos/src/grafo.txt"));

//		System.out.println("Esse é disância percorrida: " + calculaDistancia(grafo, new String[]{"A", "B", "C"}));
//		System.out.println("Esse é disância percorrida: " + calculaDistancia(grafo, new String[]{"A", "D"}));
//		System.out.println("Esse é disância percorrida: " + calculaDistancia(grafo, new String[]{"A", "D", "C"}));
//		System.out.println("Esse é disância percorrida: " + calculaDistancia(grafo, new String[]{"A", "E", "B", "C", "D"}));
//		System.out.println("Esse é disância percorrida: " + calculaDistancia(grafo, new String[]{"A", "E", "D"}));

		Vertice vertice1 = new Vertice();
		Vertice vertice2 = new Vertice();

		vertice1 = grafo.encontrarVertice("C");
		vertice2 = grafo.encontrarVertice("C");

		algoritmo.go(vertice1, vertice2, 3);
		
		//List<Vertice> resultado = algoritmo.encontrarMenorCaminho(grafo, vertice1, vertice2);
		//System.out.println("Esse é o menor caminho feito pelo algoritmo: " + resultado);
		
	}

	public static List<Vertice> lerGrafo(String nomeArquivo) {

		Grafo g = new Grafo();
		Vertice v;
		File f = new File(nomeArquivo);
		String vertices[];
		String linha;
		ArrayList<String[]> s1 = new ArrayList<String[]>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));

			Map<String, Vertice> mapa = new HashMap<String, Vertice>();

			while ((linha = br.readLine()) != null) {

				if (linha.contains(",")) {
					s1.add(linha.split("/"));
					vertices = s1.get(0)[0].split(",");

					v = (Vertice) mapa.get(vertices[0]);
					if (v == null)
						v = new Vertice();

					List<Vertice> vizinhosAtual = new ArrayList<Vertice>();
					List<Aresta> arestasAtual = new ArrayList<Aresta>();
					v.setDescricao(vertices[0]);
					mapa.put(vertices[0], v);

					if (linha.contains("/")) {

						String pesoArestas[] = s1.get(0)[1].split(",");

						for (int i = 1; i < vertices.length; i++) {
							Vertice vit;
							// vit = g.encontrarVertice(vertices[i]);
							vit = mapa.get(vertices[i]);
							if (vit == null)
								vit = new Vertice();
							vit.setDescricao(vertices[i]);
							vizinhosAtual.add(vit);
							mapa.put(vertices[i], vit);

							Aresta ait = new Aresta(v, vit);
							ait.setPeso(Integer.parseInt(pesoArestas[i - 1]));
							arestasAtual.add(ait);

						}
						v.setVizinhos(vizinhosAtual);
						v.setArestas(arestasAtual);

					}

				}

				// Vertices finais
				else {

					// v = g.encontrarVertice(linha);
					v = (Vertice) mapa.get(linha);
					if (v == null)
						v = new Vertice();
					v.setDescricao(linha);
					mapa.put(linha, v);

				}

				g.adicionarVertice(v);
				s1.clear();

			}

			br.close();

			// catch do BufferedReader
		} catch (FileNotFoundException e) {
			System.out.println("Nao encontrou o arquivo");
			e.printStackTrace();
		}

		// catch do readLine
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Retornando os vertices
		return g.getVertices();
	}

}
