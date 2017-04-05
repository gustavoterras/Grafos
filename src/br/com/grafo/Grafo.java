package br.com.grafo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gustavo.souza
 *
 */
public class Grafo {

	private List<Vertice> vertices = new ArrayList<Vertice>();

	public void setVertices(List<Vertice> vertices) {
		this.vertices.addAll(vertices);
	}

	public void adicionarVertice(Vertice novoVertice) {
		this.vertices.add(novoVertice);
	}

	public List<Vertice> getVertices() {
		return this.vertices;
	}

	// Método que retorna o vértice cuja descrição é igual à procurada.
	public Vertice encontrarVertice(String nome) {
		for (int i = 0; i < this.getVertices().size(); i++) {
			if (nome.equalsIgnoreCase(this.getVertices().get(i).getDescricao())) {
				return this.getVertices().get(i);
			}
		}
		return null;
	}

}