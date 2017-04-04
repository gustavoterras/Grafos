import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	// Lista que guarda os vertices pertencentes ao menor caminho encontrado
	List<Vertice> menorCaminho = new ArrayList<Vertice>();

	// Variavel que recebe os vertices pertencentes ao menor caminho
	Vertice verticeCaminho = new Vertice();

	// Variavel que guarda o vertice que esta sendo visitado
	Vertice atual = new Vertice();

	// Variavel que marca o vizinho do vertice atualmente visitado
	Vertice vizinho = new Vertice();

	// Lista dos vertices que ainda nao foram visitados
	List<Vertice> naoVisitados = new ArrayList<Vertice>();

	
	/***
	 * Calcula a distância total do vertice de origem ao
	 * vertice de destino somando os pessos de cada aresta
	 * @param grafo Grafo a ser percorrido
	 * @param caminhos Caminhos a percorrer
	 * @return Distância total
	 */
	public int calculaDistancia(Grafo grafo, String[] caminhos) {

		// Variavel que recebe soma dos pessos das arestas
		int distanciaTotal = 0;

		for (int i = 0; i < grafo.getVertices().size(); i++) {
			
			// Variavel vertice
			Vertice vertice = grafo.getVertices().get(i);
			
			for (int j = 0; j < vertice.getArestas().size(); j++) {
				
				// Variavel aresta
				Aresta aresta = vertice.getArestas().get(j);
				
				for (int k = 0; k < caminhos.length; k++) {

					if(k == caminhos.length -1) continue;
					
					// Caso aresta de origem e destino for igual a descricao do vertice de oirgem e destino soma a distancia
					if(aresta.getOrigem().getDescricao().equals(caminhos[k]) && aresta.getDestino().getDescricao().equals(caminhos[k + 1]))
						distanciaTotal += aresta.getPeso();
				}
				
			}
			
		}

		return distanciaTotal;
	}
	
	/***
	 * O Algoritimo de Dijkstra soluciona o problema do caminho mais curto 
	 * num grafo dirigido ou não dirigido com arestas de peso não negativo
	 * @param grafo grafo a ser percorrido
	 * @param v1 vértice de origem 
	 * @param v2 vértice de destino
	 * @return lista de vértices com o caminho mais curto percorrido pelo algoritimo
	 */
	public List<Vertice> encontrarMenorCaminho(Grafo grafo, Vertice v1, Vertice v2) {

		System.out.println("Saindo de " + v1.getDescricao()  + " indo para " + v2.getDescricao());
		
		// Adiciona a origem na lista do menor caminho
		menorCaminho.add(v1);

		// Colocando a distancias iniciais
		for (int i = 0; i < grafo.getVertices().size(); i++) {

			// Vertice atual tem distancia zero, e todos os outros, 9999 ("infinita")
			if (grafo.getVertices().get(i).getDescricao().equals(v1.getDescricao())) {
				grafo.getVertices().get(i).setDistancia(0);
			} else {
				grafo.getVertices().get(i).setDistancia(9999);
			}
			// Insere o vertice na lista de vertices nao visitados
			this.naoVisitados.add(grafo.getVertices().get(i));
		}

		Collections.sort(naoVisitados);

		// O algoritmo continua ate que todos os vertices sejam visitados
		while (!this.naoVisitados.isEmpty()) {

			// Toma-se sempre o vertice com menor distancia, que é o primeiro da lista
			atual = this.naoVisitados.get(0);
			System.out.println("Pegou esse vertice:  " + atual);
			/*
			 * Para cada vizinho (cada aresta), calcula-se a sua possivel
			 * distancia, somando a distancia do vertice atual com a da aresta
			 * correspondente. Se essa distancia for menor que a distancia do
			 * vizinho, esta é atualizada.
			 */
			for (int i = 0; i < atual.getArestas().size(); i++) {

				vizinho = atual.getArestas().get(i).getDestino();
				System.out.println("Olhando o vizinho de " + atual + ": " + vizinho);
				
				if (!vizinho.verificarVisita()) {

					// Comparando a distância do vizinho com a possível distância
					if (vizinho.getDistancia() > (atual.getDistancia() + atual.getArestas().get(i).getPeso())) {

						vizinho.setDistancia(atual.getDistancia() + atual.getArestas().get(i).getPeso());
						vizinho.setPai(atual);

						/*
						 * Se o vizinho eh o vertice procurado, e foi feita uma
						 * mudanca na distancia, a lista com o menor caminho
						 * anterior e apagada, pois existe um caminho menor
						 * vertices pais, ate o vertice origem.
						 */
						if (vizinho == v2) {
							menorCaminho.clear();
							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							
							while (verticeCaminho.getPai() != null) {
								menorCaminho.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();
							}
							
							// Ordena a lista do menor caminho, para que ele
							// seja exibido da origem ao destino.
							Collections.sort(menorCaminho);

						}
						
					}
					
				}

			}
			
			// Marca o vertice atual como visitado e o retira da lista de nao visitados
			atual.visitar();
			this.naoVisitados.remove(atual);
			
			// Ordena a lista, para que o vertice com menor distancia fique na primeira posicao 
			Collections.sort(naoVisitados);
			System.out.println("Nao foram visitados ainda:" + naoVisitados);

		}

		return menorCaminho;
	}
	
	public void go(Vertice v1, Vertice v2, int paradas) {
			
		dsf(v1, v2);
		
		System.out.println("visitando " + count);
	}
	
	
	int count = 0;
	private void dsf(Vertice v1, Vertice v2) {
		
		if(count == 3) return;
		
		count++;
		
		if(v1.verificarVisita()) return;
		
		System.out.println("visitando " + v1.getDescricao());		
		
		for (int i = 0; i < v1.getArestas().size(); i++) {

			Vertice destino = v1.getArestas().get(i).getDestino();
						
			if (destino.getDescricao().equals(v2.getDescricao())){		
				System.out.println(v1.getDescricao() + " ----> " + v2.getDescricao());
			} else
				dsf(destino, v2);
		}
		
		v1.visitar();
		
	}
		
}