package cs311.hw7.graph;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import cs311.hw7.graph.IGraph.Vertex;
import cs311.hw7.graphalgorithms.GraphAlgorithms;

public class MalerichAlgorithmTests {

	@Test public void testTopoSort0() {
		Graph<String, String> test = new Graph<String, String>();
		test.setDirectedGraph();

		test.addVertex("A");
		test.addVertex("B");
		test.addVertex("C");
		
		test.addEdge("B", "A");
		test.addEdge("A", "C");
		
		List<Vertex<String>> l = GraphAlgorithms.<String, String>TopologicalSort(test);
		ArrayList<String> arr = new ArrayList<String>();
		for (Vertex<String> v : l) {
			arr.add(v.getVertexName());
		}
		
		// There is only one ordering for this topological sort (A, B, C).
		assertEquals("B", arr.get(0));
		assertEquals("A", arr.get(1));
		assertEquals("C", arr.get(2));
	}
	
	@Test public void testTopoSort1() {
		Graph<String, String> test = new Graph<String, String>();
		test.setDirectedGraph();

		test.addVertex("A");
		test.addVertex("B");
		test.addVertex("C");
		
		test.addEdge("C", "B");
		test.addEdge("C", "A");
		
		List<Vertex<String>> l = GraphAlgorithms.<String, String>TopologicalSort(test);
		ArrayList<String> arr = new ArrayList<String>();
		for (Vertex<String> v : l) {
			arr.add(v.getVertexName());
		}
		
		// C must be the first element, but the ordering of A and B do not matter so just check the size of the returned list.
		assertEquals("C", arr.get(0));
		assertEquals(3, l.size());
	}
	
	@Test public void testTopoSort2() {
		Graph<String, String> test = new Graph<String, String>();
		test.setDirectedGraph();
		
		// Add a bunch of random data, I'm not going to test it is right,
		// this is just a test to make sure the method doesn't break under pressure.
		for (int i=0; i<100; i++) {
			test.addVertex(String.valueOf(i));
		}
		
		// Make sure all edges are going forward in some way.
		for (int i=1; i<100; i++) {
			int r = (int)(Math.random() * 1000) % i;
			test.addEdge(String.valueOf(i), String.valueOf(r));
		}
		
		// Run a really big test, will never fail unless your code throws
		// an exception or has an infinite loop hiding in it.
		GraphAlgorithms.<String, String>TopologicalSort(test);
	}
}