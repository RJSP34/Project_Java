/******************************************************************************
 *  Compilation:  javac EdgeWeightedDigraph.java
 *  Execution:    java EdgeWeightedDigraph digraph.txt
 *  Dependencies: Bag.java DirectedEdge.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/tinyEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/mediumEWD.txt
 *                https://algs4.cs.princeton.edu/44sp/largeEWD.txt
 *
 *  An edge-weighted digraph, implemented using adjacency lists.
 *
 ******************************************************************************/

package Classimports;

import Project.Cache_log;
import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *  The {@code EdgeWeightedDigraph} class represents a edge-weighted
 *  digraph of vertices named 0 through <em>V</em> - 1, where each
 *  directed edge is of type {@link DirectedEdge} and has a real-valued weight.
 *  It supports the following two primary operations: add a directed edge
 *  to the digraph and iterate over all of edges incident from a given vertex.
 *  It also provides methods for returning the indegree or outdegree of a
 *  vertex, the number of vertices <em>V</em> in the digraph, and
 *  the number of edges <em>E</em> in the digraph.
 *  Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an <em>adjacency-lists representation</em>, which
 *  is a vertex-indexed array of {@link Bag} objects.
 *  It uses &Theta;(<em>E</em> + <em>V</em>) space, where <em>E</em> is
 *  the number of edges and <em>V</em> is the number of vertices.
 *  All instance methods take &Theta;(1) time. (Though, iterating over
 *  the edges returned by {@link #adj(int)} takes time proportional
 *  to the outdegree of the vertex.)
 *  Constructing an empty edge-weighted digraph with <em>V</em> vertices
 *  takes &Theta;(<em>V</em>) time; constructing an edge-weighted digraph
 *  with <em>E</em> edges and <em>V</em> vertices takes
 *  &Theta;(<em>E</em> + <em>V</em>) time. 
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class EdgeWeightedDigraph_copy implements Serializable{
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;                // number of vertices in this digraph
    private int E;                      // number of edges in this digraph
    private Bag<DirectedEdge_copy>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;             // indegree[v] = indegree of vertex v
private Cache_log Log;
    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedDigraph_copy(int V)   {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        this.Log=new Cache_log(new ArrayList<>());
        adj = (Bag<DirectedEdge_copy>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge_copy>();

    }

    /**
     * Initializes a random edge-weighted digraph with {@code V} vertices and <em>E</em> edges.
     *
     * @param  V the number of vertices
     * @param  E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedDigraph_copy(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be non-negative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double[] weight =new double[2];
            this.Log=new Cache_log(new ArrayList<>());
            for (int j = 0; j<2 ; j++) {
                weight[j] = 0.01 * StdRandom.uniform(100);
            }
            DirectedEdge_copy e = new DirectedEdge_copy(v, w, weight);
            addEdge(e);
        }
    }

    /**
     * Initializes an edge-weighted digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices and edge weights,
     * with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public EdgeWeightedDigraph_copy(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be non-negative");
            indegree = new int[V];
            adj = (Bag<DirectedEdge_copy>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<DirectedEdge_copy>();
            }
            this.Log=new Cache_log(new ArrayList<>());
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of edges must be non-negative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);

                double[] weight =new double[2];
                for (int j = 0; in.exists()||j<2 ; j++) {
                    weight[j] = in.readDouble();
                }
                addEdge(new DirectedEdge_copy(v, w, weight));
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph constructor", e);
        }
    }

    /**
     * Initializes a new edge-weighted digraph that is a deep copy of {@code G}.
     *
     * @param  G the edge-weighted digraph to copy
     */
    public EdgeWeightedDigraph_copy(EdgeWeightedDigraph_copy G) {
        this(G.V());
        this.E = G.E();
        this.Log=new Cache_log(new ArrayList<>());
        for (int v = 0; v < G.V(); v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<DirectedEdge_copy> reverse = new Stack<DirectedEdge_copy>();
            for (DirectedEdge_copy e : G.adj[v]) {
                reverse.push(e);
            }
            for (DirectedEdge_copy e : reverse) {
                adj[v].add(e);
            }
        }
    }

    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
     *         and {@code V-1}
     */
    public void addEdge(DirectedEdge_copy e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }
    public boolean If_existed(DirectedEdge_copy a){
        for (DirectedEdge_copy au1:this.adj(a.from())) {
            if (au1==a){
                return true;
            }
        }
        return false;

    }

    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge_copy> adj(int v) {
        validateVertex(v);
        return adj[v];
    }
    public EdgeWeightedDigraph_copy remove_V(int v, ST<Integer,Integer> regist){
        if(this.V<=v||this.V-1<0) {
            return null;
        }
        EdgeWeightedDigraph_copy a=new EdgeWeightedDigraph_copy(this.V-1);
        for (int i = 0; i <this.V ; i++) {
            for (DirectedEdge_copy e : this.adj[i]) {
               if (!(e.from()==v||e.to()==v)){
                   if (e.from()>v &&e.to()<v){
                           DirectedEdge_copy e1 = new DirectedEdge_copy(e.from()-1 , e.to(), e.weight());
                           a.addEdge(e1);

                   }else  if (e.from()<v &&e.to()>v) {
                       DirectedEdge_copy e1 = new DirectedEdge_copy(e.from() , e.to()-1, e.weight());
                       a.addEdge(e1);
                   }else if(e.from()<v &&e.to()<v){
                       DirectedEdge_copy e1 = new DirectedEdge_copy(e.from() , e.to(), e.weight());
                       a.addEdge(e1);

                   }else {
                       DirectedEdge_copy e1 = new DirectedEdge_copy(e.from() -1, e.to()-1, e.weight());
                       a.addEdge(e1);
                   }

               }
            }
        }
        int key = -1;
        for (int key2:regist.keys()) {
            if (regist.get(key2)==v){
                key=key2;
            }else if(key2>key&&key!=-1){
                regist.put(key2,regist.get(key2)-1);
            }
        }
        regist.remove(key);
        this.Log.insertLog("Removed"," Vertix ", String.valueOf(v));
return a;
    }
    public EdgeWeightedDigraph_copy Set_set_by_removal( ST<Integer,Integer> regist,int[] arra){
     EdgeWeightedDigraph_copy aux=this;

        for (int j : arra) {
            if (j >= 0 && j < this.V) {
              aux=  aux.remove_V(j, regist);
            }
        }

        return aux;

    }
  public boolean isEmpty() {
      return this.indegree.length == 0 ||this.V==0||this.E==0;
  }



    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }
    public void switch_bag(DirectedEdge_copy old, DirectedEdge_copy newer){
Bag<DirectedEdge_copy>aux =new Bag<>();
        for (DirectedEdge_copy aux2:this.adj(old.from())) {
            double[] a=aux2.weight();
            if (!(aux2.to()==old.to()&&aux2.from()==aux2.from() &&a!=old.weight()) ){
                aux.add(aux2);
            }else {
                aux.add(newer);
            }

        }
        this.adj[old.from()]=aux;

    }
    public void add_TO_bag( DirectedEdge_copy newer){
        this.adj[newer.from()].add(newer );
    }
    public void edite_vertix( ST<Integer,Integer> aux,int id,int newer_id){
        if (aux.get(id)==null||aux.get(newer_id)!=null){
            return;
        }
        int g=aux.get(id);
        aux.remove(id);
        aux.put(newer_id,g);
    }
    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdge_copy> edges() {
        Bag<DirectedEdge_copy> list = new Bag<DirectedEdge_copy>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge_copy e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    } 

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (DirectedEdge_copy e : adj[v]) {
                s.append(e).append("  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public void find_shorstes_path_time(int v,int w){
DijkstraSP_Copy h=new DijkstraSP_Copy(this,v);
if (!h.hasPathTo(w)){
    this.Log.insertLog("Tried to Visit",String.valueOf(v),"To "+ w);
    System.out.println(v+" has no path to "+w);
}
        Iterable<DirectedEdge_copy> a=h.pathTo(w);
        for (DirectedEdge_copy s:a) {
            System.out.println(s.toString());
        }
        this.Log.insertLog(" Visited",String.valueOf(v),"To "+ w);
    }

    /**
     * Unit tests the {@code EdgeWeightedDigraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph_copy G = new EdgeWeightedDigraph_copy(in);
        StdOut.println(G);
    }


}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
