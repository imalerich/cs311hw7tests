package cs311.hw7.graph;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cs311.hw7.graph.IGraph.DuplicateVertexException;

public class MalerichGraphTests {

	@Test public void testAddVertices() {
		// Create a graph and add some vertices to it.
		Graph<String, String> test = new Graph();
		test.addVertex("A");
		test.addVertex("B");
		test.addVertex("C");
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("A");
		arr.add("B");
		arr.add("C");
		
		List<IGraph.Vertex<String>> vertices = test.getVertices();
		for (IGraph.Vertex<String> v : vertices) {
			if (!arr.remove(v.getVertexName())) {
				fail("Graph added an unwanted vertex.");
			}
		}
		
		if (arr.size() > 0) {
			fail("Graph is missing a vertex!");
		}
	}

	@Test public void testAddVerticesFailure() {
		// Create a graph and add some vertices to it.
		Graph<String, String> test = new Graph();
		test.addVertex("A");
		test.addVertex("B");
		test.addVertex("C");
		
		try {
			test.addVertex("C");
		} catch (DuplicateVertexException e) {
			// Test passed, return early.
			return;
		}
		
		// Catch statement missed, test failed.
		fail("Failed to throw DuplicateVertexException when adding duplicate vertex.");
	}
}