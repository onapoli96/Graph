package src.giorgio.graph;

/**
 * This class implements the interface VertexAnalyser, that contain a method to analize the vertex passed as parameter 
 * in this implementation the method print on consol the vertex
 * @author Giorgio
 * @param <V> generic type to define the graphos vertex
 */
public class VertexAnalyserImplements<V> implements VertexAnalyser<V>{

	/**
	 * The method print on consol the vertex
	 *  @param <V> generic type to define the graphos vertex
	 */
	@Override
	public void analyse(V vertex) {
		System.out.println(vertex);
	}

}
