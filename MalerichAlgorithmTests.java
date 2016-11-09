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
	
	@Test public void testAllTopoSort0() {
		Graph<String, String> test = new Graph<String, String>();
		test.setDirectedGraph();

		test.addVertex("B");
		test.addVertex("A");
		test.addVertex("C");
		
		test.addEdge("B", "C");
		test.addEdge("A", "B");
		
		// There should be only one valid topological sort with this input.
		List<List<Vertex<String>>> res = GraphAlgorithms.AllTopologicalSort(test);
		assertEquals("There is only 1 valid topological sort.", 1, res.size());
		List<Vertex<String>> arr = res.get(0);
		assertEquals("A", arr.get(0).getVertexName());
		assertEquals("B", arr.get(1).getVertexName());
		assertEquals("C", arr.get(2).getVertexName());
	}
	
	@Test public void testAllTopoSort1() {
		Graph<String, String> test = new Graph<String, String>();
		test.setDirectedGraph();

		test.addVertex("C");
		test.addVertex("A");
		test.addVertex("B");
		
		test.addEdge("A", "B");
		test.addEdge("A", "C");
		
		// A must be first, but ordering of B, C does not matter, thus there are 2 possible topological sorts.
		List<List<Vertex<String>>> res = GraphAlgorithms.AllTopologicalSort(test);
		assertEquals("There is only 2 valid topological sort.", 2, res.size());
		// Make sure they both start with A.
		assertEquals("A", res.get(0).get(0).getVertexName());
		assertEquals("A", res.get(1).get(0).getVertexName());
	}
	
	@Test public void testAllTopoSort2() {
		Graph<String, String> test = new Graph<String, String>();
		test.setDirectedGraph();
		
		final int COUNT = 10;
		int fact = 1;
		for (int i=COUNT; i>1; i--) {
			fact *= i;
		}

		// Add 10 vertices and 0 edges.
		for (int i=0; i<COUNT; i++) {
			test.addVertex(String.valueOf(i));
		}
		
		// Number of topological sorts is equal to 10!
		List<List<Vertex<String>>> res = GraphAlgorithms.AllTopologicalSort(test);
		assertEquals("There are 10! valid topological sort.", fact, res.size());
	}
}