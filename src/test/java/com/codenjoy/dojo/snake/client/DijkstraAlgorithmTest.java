package com.codenjoy.dojo.snake.client;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

//import de.vogella.algorithms.dijkstra.engine.DijkstraAlgorithm;
//import de.vogella.algorithms.dijkstra.model.Edge;
//import de.vogella.algorithms.dijkstra.model.Graph;
//import de.vogella.algorithms.dijkstra.model.Vertex;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
/**
 * Created by oleg on 10.05.16.
 */
public class DijkstraAlgorithmTest {

    private List<Vertex> nodes;
    private List<Edge> edges;

    @Test
    public void testExcute() {
        nodes = new ArrayList<Vertex>();
        edges = new ArrayList<Edge>();
        for (int i = 0; i < 2525; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }

//
//        int point1 = 0;
//        int point2 = 0;
//        int point3 = 0;
//        String edgeX_name ;
//        String edgeXr_name ;
//        String edgeY_name ;
//        String edgeYr_name ;
//        int nextHopX;
//        int nextHopY;
//        for (int y = 10; y < 25; y++) {
//            for (int x = 10; x < 25; x++) {
//                point1 = x*100+y;
//                if (x<24){
//                    point2 = (x+1)*100+y;
//                    edgeX_name = "Edge_"+String.valueOf(point1)+String.valueOf(point2);
//                    addLane(edgeX_name,point1,point2,1);
//                    edgeXr_name = "Edge_"+String.valueOf(point2)+String.valueOf(point1);
//                    addLane(edgeXr_name,point2,point1,1);
//                }
//                if (y<24) {
//                    point3 = x * 100 + y + 1;
//                    edgeY_name = "Edge+"+String.valueOf(point1) + String.valueOf(point3);
//                    addLane(edgeY_name, point1, point3, 1);
//                    edgeYr_name = "Edge+"+String.valueOf(point3) + String.valueOf(point1);
//                    addLane(edgeYr_name, point3, point1, 1);
//
//                }
//            }
//        }

        addLane("Edge_0", 10, 11, 85);
        addLane("Edge_1", 10, 12, 217);
        addLane("Edge_2", 10, 14, 173);
        addLane("Edge_3", 12, 16, 186);
        addLane("Edge_4", 12, 17, 103);
        addLane("Edge_5", 13, 17, 183);
        addLane("Edge_6", 15, 18, 250);
        addLane("Edge_7", 18, 19, 84);
        addLane("Edge_8", 17, 19, 167);
        addLane("Edge_9", 14, 19, 502);
        addLane("Edge_10", 19, 110, 40);
        addLane("Edge_11", 11, 110, 600);
        //addLane("Edge_12", 12, 10, 217);
        //addLane("Edge_13", 16, 12, 186);



        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(12));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(10));

        assertNotNull(path);
        assertTrue(path.size() > 0);

//        nextHopX = Integer.parseInt(path.get(1).toString().substring(5,7));
//        nextHopY =Integer.parseInt(path.get(1).toString().substring(7,9));
//        System.out.println(nextHopX);
//        System.out.println(nextHopY);
        System.out.println(" | "+path.get(1)+" | ");
        System.out.println(" | "+path+" | ");

//      for (Vertex vertex : path) {
//            System.out.println(vertex);
//        }
//
  }


    private void addLane(String laneId, int sourceLocNo, int destLocNo,
                         int duration) {
        Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
        edges.add(lane);
//        System.out.println(edges);
//        System.out.println(lane);
    }
}

