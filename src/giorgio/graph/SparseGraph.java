package giorgio.graph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * the class that implements the interface Graph
 *  
 * @author Giorgio
 *
 * @param <V> generic type to represent the vertex of the graph
 * @param <E> generic type to represent the information about the edges of the graph
 */
public class SparseGraph<V,E> implements Graph<V,E>{
	ArrayList<V> nodes;	
	HashMap<V,ArrayList<E>> edges;
	int n;
	int m;
	
	/**
	 * constructor that inizialize:
	 *  - ArrayList of vertices (nodes)
	 *  - l'HashMap of the edges
	 *  - the vertex number to 0
	 *  - the vertices number to 0
	 */
	public SparseGraph(){
		nodes = new ArrayList<V>();
		edges = new HashMap<V,ArrayList<E>>();
		n = 0;
		m = 0;
	}
	
	/**
	 * add a vertex v to the graph
	 * the method add the V element to the ArrayList, create a list of neighbors using the HashMap and increment the number of vertex in the graph.
	 * @param vertex vertex to add in the graph
	 * @return boolean to check if the insert was successful or not 
	 */
	@Override
	public boolean addVertex(V vertex) {
		if(!nodes.contains(vertex)){
			nodes.add(vertex);
			n++;
			ArrayList<E> neighbors = new ArrayList<E>();
			edges.put(vertex,(ArrayList<E>) neighbors);
			return true;
		}
		
		return false;
	}

	/**
	 * aggiunge un Edge orientato nel grafo.
	 * il metodo controlla se i due vertici forniti come parametri sono già presenti nel grafo,
	 * in caso contrario procede ad inserirli tramite il metodo addVertex ed infine, dopo aver creato un Edge v1 -> v2 (uscente da v1 e entrante in v2), 
	 * lo aggiunge alla lista degli adicenti (neighbors) del vertice v1.
	 * @param v1 vertice da cui l'Edge è uscente
	 * @param v2 vertice adiacente a v1
	 * @param info informazioni riguardanti l'Edge, possono anche essere null.
	 * @exception IllegalArgumentException l'eccezzione viene sollevata in caso uno dei due nodes o entrambi siano null.
	 * @return booleano, true in caso di Edge aggiunto correttamente, altrimenti, se già presente false.
	 */
	@Override
	public boolean addEdge(V v1, V v2, E info) {
		if(v1 == null || v2 == null ) throw new IllegalArgumentException("il nodo non può essere null");
		if(nodes.contains(v1)){
			if(!nodes.contains(v1)) addVertex(v1);
			if(!nodes.contains(v2)) addVertex(v2);
			Edge<V,E> a = new Edge<V, E>(info,v1,v2);
			ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(v1);
			neighbors.add(a);
			m++;
			return true;
		}
		return false;
	}

	/**
	 * aggiunge un Edge orientato pesato al grafo
	 * il metodo controlla se i due vertici forniti come parametri sono già presenti nel grafo,
	 * in caso contrario procede ad inserirli tramite il metodo addVertex ed infine, dopo aver creato un Edge v1 -> v2 (uscente da v1 e entrante in v2) con peso weight, 
	 * lo aggiunge alla lista degli adicenti (neighbors) del vertice v1.
	 * @param v1 vertice da cui l'Edge è uscente
	 * @param v2 vertice adiacente a v1
	 * @param info informazioni riguardanti l'Edge, possono anche essere null.
	 * @param weight peso dell'Edge.
	 * @exception IllegalArgumentException l'eccezzione viene sollevata in caso uno dei due nodes o entrambi siano null.
	 * @return booleano, true in caso di Edge aggiunto correttamente, altrimenti, se già presente false.
	 */
	@Override 
	public boolean addEdge(V v1, V v2, double weight, E info) {
		if(v1 == null || v2 == null ) throw new IllegalArgumentException("il nodo non può essere null");
		if(nodes.contains(v1)){
			if(!nodes.contains(v1)) addVertex(v1);
			if(!nodes.contains(v2)) addVertex(v2);
			Edge<V,E> a = new Edge<V, E>(info,v1,v2,weight);
		
			ArrayList<Edge<V,E>> neighbors = (ArrayList<Edge<V,E>>)edges.get(v1);
			neighbors.add(a);
			m++;
			return true;
		}
		return false;
	}
	/**
	 * aggiunge un Edge non orientato al grafo.
	 * il metodo crea un Edge orientato uscente da v1 e entrante in v2 e un altro Edge uscente da v2 e entrante in v1.
	 * si serve del metodo addEdge definito in precedenza.
	 * in questo modo l'Edge verrà aggiunto sia alla lista di adiacenza di v2 che di v2 in modo che l'Edge sia incidente su entrambi in nodes.
	 * @param v1 vertice da cui l'Edge è uscente
	 * @param v2 vertice adiacente a v1
	 * @param info informazioni riguardanti l'Edge, possono anche essere null.
	 * @return booleano, true in caso di Edge aggiunto correttamente in entrambe le liste di adiacenza, altrimenti, se già presente in entrambe le liste di adiacenza false.
	 */
	@Override
	public boolean addUndirectedEdge(V v1, V v2, E info) {
		if(addEdge(v1,v2,info) || addEdge(v2,v1,info)) return true;
		else return false;
	}

	/**
	 * aggiunge un Edge pesato non orientato al grafo.
	 * il metodo crea un Edge orientato uscente da v1 e entrante in v2 e un altro Edge uscente da v2 e entrante in v1 entrambi con peso weight.
	 * si serve del metodo addEdge definito in precedenza.
	 * in questo modo l'Edge verrà aggiunto sia alla lista di adiacenza di v2 che di v2 in modo che l'Edge sia incidente su entrambi in nodes.
	 * @param v1 vertice da cui l'Edge è uscente
	 * @param v2 vertice adiacente a v1
	 * @param weight peso dell'Edge non oreintato (peso degli edges orientati in entrambe le direzioni).
	 * @param info informazioni riguardanti l'Edge, possono anche essere null.
	 * @return booleano, true in caso di Edge aggiunto correttamente in entrambe le liste di adiacenza, altrimenti, se già presente in entrambe le liste di adiacenza false.
	 */
	@Override
	public boolean addUndirectedEdge(V v1, V v2, double weight, E info) {
		addEdge(v1,v2,weight,info);
		addEdge(v2,v1,weight,info);
		return true;
	}
	/**
	 * controlla se un vertice,fornito come parametro, è presente nel grafo.
	 * il metodo invoca contains dell'ArrayList sulla lista dei vertici del grafo, se è presente resituisce true false altrimenti
	 * @param vertex vertice di cui si vuole sapere o meno la presenza all'interno del grafo.
	 * @return booleano true in caso di vertice presente nel grafo, false altrimenti
	 */
	@Override
	public boolean hasVertex(V vertex) {
		if(nodes.contains(vertex))
			return true;
		return false;
	}
/**
 * conrrolla se esiste un Edge v1 -> v2.
 * il metodo prima esegue un controllo all'interno della lista dei nodes del grafo, se entrambi i vertici sono presenti,
 * procede al controllo all'interno della lista di adiacenti di v1 per vedere se l'Edge v1 -> v2 è presente.
 * @param v1 nodo uscente dell'Edge cercato
 * @param v2 nodo entrante dell'Edge cercato
 * @return booleano, true se l'Edge v1 -> v2 fa parte del grafo, false altrimenti
 */
	@Override
	public boolean hasEdge(V v1, V v2) {
		if(nodes.contains(v1)){
			if(nodes.contains(v2)){
				ArrayList<Edge<V,E>> neighbors = (ArrayList<Edge<V,E>>)edges.get(v1);
				if(neighbors.contains(new Edge(v1,v2)));
				return true;
			}
		}
		return false;
	}

	/**
	 * restituisce il peso di un Edge source -> dest
	 * il metodo invoca la procedure getEdge fornendo come parametro i due vertici dati in input,
	 * una volta trovato l'Edge ne restituisce il peso, 
	 * in caso di un Edge senza peso viene restituito 1.
	 * @param source nodo uscente (sorgente) dell'Edge cercato.
	 * @param dest nodo entrante(destinazione) dell'Edge cercato
	 * @return peso dell'Edge espresso in double.
	 */
	@Override
	public double getWeight(V source, V dest) {
		return getEdge(source,dest).getWeight();
	}

	/**
	 * restituisce la lista dei nodes presenti nel grafo
	 * @return ArrayList contenente tutti i nodes inseriti, fino al momento dell'invocazione, all'interno del grafo
	 */
	@Override
	public ArrayList<V> vertices() {
		return nodes;
	}

	/**
	 * restituisce la lista dei nodes adiacenti al nodo passato come parametro.
	 * grazie all'HashMap il metodo riesce a trovare la lista dei suoi adiacenti e viene restituita attraverso questo metodo.
	 * @param vertex vertice di cui si vuole ottenere la lista dei suoi nodes adiacenti.
	 * @return lista dei noidi agiagenti al nodo fornito come parametro, in caso non abbia nodes adiacenti la procedura restituisce null.
	 */
	@Override
	public ArrayList<V> neighbors(V vertex) {
	if(nodes.contains(vertex)){
		ArrayList<V> neighbors = new ArrayList<V>();
		ArrayList<Edge> arch = (ArrayList<Edge>)edges.get(vertex);
	
		for(Edge a: arch)
		neighbors.add((V) a.getOut());
		return neighbors;
	}
	return null;
	}
	
	/**
	 * restituisce l'Edge source -> dest se presente nel grafo.
	 * il metodo ricerca all'interno della lista degli edges del nodo source, se esiste un Edge che ha come sorgente source e destinazione dest
	 * @param source nodo uscente dell'Edge source > dest
	 * @param dest nodo entrante dell'Edge source -> dest
	 * @return se presente Edge source --> dest, altrimenti null
	 */
	public Edge getEdge(V source, V dest){
		ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(source);
		Edge find = new Edge(source,dest);
		for(Edge<V,E> a : neighbors){
			if((a.getOut().equals(find.getOut()))&&(a.getIn().equals(find.getIn())))
				return a;
		}
		return null;
	}
	/**
	 * stampa la lista dei nodes del grafo
	 * la procedura scorre la lista dei nodes attraverso un for e per ogni nodo che incontra ne stampa il valore su console.
	 */
	public void printVertex(){
		for(V i : nodes)
			System.out.println(i);
	}
	/**
	 * stampa la lista di adiacenza del vertice passato come parametro;
	 * dopo aver ottento la lista di adiacenza del nodo passato come parametro,
	 * scorre tale seqenza con un for e stampa a video l'Edge.
	 * @param vertex vertice di cui si vuole stampare la lista di adiacenza
	 */
	public void printNeighbors(V vertex){
		ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(vertex);
		for(Edge<V,E> a : neighbors)
			System.out.println("iniziale: "+a.getIn()+" finale: "+a.getOut()+" info: "+a.getInfo());
	}
	
	/**
	 * crea un file .dot per esserre elaborato da graphviz.
	 * metodo che crea un file .dot con nome del file passato come parametro, 
	 * che poi sarà laborato da graphviz per produrne un immagine del grafo.
	 * @param GraphName nome che si è deciso di dare al file .dot.
	 */
	public void toDot(String GraphName) throws IOException{
		String Graph = GraphName+".dot";
	  FileWriter outFile = new FileWriter(Graph, false);
	  PrintWriter out = new PrintWriter(outFile);
	  out.println("digraph "+GraphName+"{");
	  for(V i : nodes){
	  	ArrayList<Edge> neighbors = (ArrayList<Edge>)edges.get(i);
			for(Edge<V,E> a : neighbors){
				out.println(a.getIn()+" -> "+a.getOut()+";");
			}
	  }
	  out.println("}");
	  out.close();
	}
}
