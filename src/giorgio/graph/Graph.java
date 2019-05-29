package giorgio.graph;

import java.io.IOException;
import java.util.ArrayList;
/**
 * interface to create the graph
 * 
 * @author Giorgio
 *
 * @param <V> generic type to represent the vertex of the graph
 * @param <E> generic type to represent the information about the edges of the grapg
 */
public interface Graph<V, E> {
	
	/**
	 * to add a new vertex into the graph
	 * @param vertex vertex to insert
	 * @return true if it is correctly inserted, else false (vertex present).
	 */
	boolean addVertex(V vertex);
	
	/**
	 * add the edge v1->v2 if it is not present in the graph
	 * @param v1 source vertex
	 * @param v2 destination vertex
	 * @param info information about the edge
	 * @return true if it is correctly inserted, else false (edge present).
	 */
	boolean addEdge(V v1,V v2,E info);
	
	/**
	 *add the edge v1->v2 if it is not present in the graph (with weight)
	 * @param v1 source vertex
	 * @param v2 destination vertex
	 * @param weight weight of the edge
	 * @param info information about the edge
	 * @return true if it is correctly inserted, else false (edge present).
	 */
	boolean addEdge(V v1,V v2,double weight,E info);
	
	/**
	 *add the edge v1->v2 if it is not present in the graph (with weight)
	 * @param v1 source vertex
	 * @param v2 destination vertex
	 * @param weight weight of the edge
	 * @param info information about the edge
	 * @return true if it is correctly inserted, else false (edge present).
	 */
	boolean addUndirectedEdge(V v1,V v2,E info);
	/**
	 * aggiunge un arco non orientato v1 <-> v2 con informazione info e peso weight.
	 * @param v1 un nodo dell'arco
	 * @param v2 altro nodo dell'arco
	 * @param info informazione sull'arco
	 * @param weight peso dell'arco
	 * @return true se l'arco è stato inserito correttamente, false altrimenti.
	 */
	boolean addUndirectedEdge(V v1,V v2,double weight,E info);
	/**
	 * controlla se vertex è presente nel grafo.
	 * @param vertex vertice di cui si vuole conoscere la presenza nel grafo.
	 * @return true se è contenuto nel grafo, false altrimenti.
	 */
	boolean hasVertex(V vertex);
	/**
	 * controlla se l'arco v1 -> v2 è presente nel grafo
	 * @param v1 vertice uscente dell'arco
	 * @param v2 vertice entrante dell'arco
	 * @return true se l'arco è nel grafo, false altrimenti.
	 */
	boolean hasEdge(V v1,V v2);
	/**
	 * fornisce il peso dell'arco source -> dest
	 * @param source sorgente dell'arco
	 * @param dest destinazione dell'arco
	 * @return valore del peso dell'arco espresso con il tipo double.
	 */
	double getWeight(V source, V dest);
	/**
	 * resitutisce i vertici del grafo sottoforma di un ArrayList
	 * @return ArrayList di vertici del grafo
	 */
	ArrayList<V> vertices();
	/**
	 * restituisce i nodi che sono adiacenti al vertice vertex, sotto forma di un ArrayLis.
	 * 
	 * @param vertex vertice di cui si vuole conoscere gli adiacenti.
	 * @return ArrayList con i vertici che sono vicini di vertex.
	 */
	ArrayList<V> neighbors(V vertex);
	/**
	 * crea la rappresentazione in formato dot language del grafo e ne crea un file che potrà essere elaborato da graphviz
	 * @param string path e nome del file che si vuole creare.
	 * @throws IOException in caso non fosse possibile scivere il file
	 */
	void toDot(String string) throws IOException;
}