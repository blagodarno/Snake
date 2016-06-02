package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by asu06 on 20.05.16.
 */

public class ManageSnake implements Solver<Board> {

       private static final String USER_NAME = "blagodarno@gmail.com";

        private Dice dice;
        private Board board;
        private List<Vertex> nodes;
        private List<Edge> edges;
        private String edgeYr_name;
        private String edgeXr_name;
        private List <Integer> forRemoveEdges = new LinkedList<Integer>();
        private int nextHopX;
        private int nextHopY;



    public ManageSnake(Dice dice) {
            this.dice = dice;
        }

        @Override
        public String get(Board board) {
            this.board = board;

           CreateArrayVertex(2525);

            Point head = board.getHead();
           // Point apple = board.getApples().get(0);
            Point tail = board.getTail();

            char[][] field = board.getField();


            int headIndex = board.getHeadIndex();
            int appleIndex = board.getAppleIndex();
            int tailIndex = board.getTailIndex();


            CreateArrayEdges(field);


            // Lets check from location Loc_1 to Loc_10
            Graph graph = new Graph(this.nodes, edges);
            DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
            dijkstra.execute(this.nodes.get(headIndex));
            LinkedList<Vertex> path = dijkstra.getPath(this.nodes.get(appleIndex));
            System.out.println(" Path is: "+path);


            if (board.getAt(tail.getX()+1,tail.getY()) == Elements.NONE) {
                int tailPointBesideFree = ((tail.getX() + 11) * 100 + tail.getY() + 10);
                edgeYr_name = "Edge_" + String.valueOf(tailPointBesideFree) + String.valueOf(tailIndex);
                addLane(edgeYr_name, tailPointBesideFree, tailIndex, 1);
            }
            if (board.getAt(tail.getX()-1,tail.getY()) == Elements.NONE) {
                int tailPointBesideFree = ((tail.getX() + 9) * 100 + tail.getY() + 10);
                edgeYr_name = "Edge_" + String.valueOf(tailPointBesideFree) + String.valueOf(tailIndex);
                addLane(edgeYr_name, tailPointBesideFree, tailIndex, 1);
            }
            if (board.getAt(tail.getX(),tail.getY()+1) == Elements.NONE) {
                int tailPointBesideFree = ((tail.getX() + 10) * 100 + tail.getY() + 11);
                edgeYr_name = "Edge_" + String.valueOf(tailPointBesideFree) + String.valueOf(tailIndex);
                addLane(edgeYr_name, tailPointBesideFree, tailIndex, 1);
            }
            if (board.getAt(tail.getX(),tail.getY()-1) == Elements.NONE) {
                int tailPointBesideFree = ((tail.getX() + 10) * 100 + tail.getY() + 9);
                edgeYr_name = "Edge_" + String.valueOf(tailPointBesideFree) + String.valueOf(tailIndex);
                addLane(edgeYr_name, tailPointBesideFree, tailIndex, 1);
            }

            Graph graphTail = new Graph(this.nodes, edges);
            DijkstraAlgorithm dijkstraTail = new DijkstraAlgorithm(graphTail); // was new DijkstraAlgorithm(graph)  ????
            dijkstraTail.execute(this.nodes.get(headIndex));
            LinkedList<Vertex> pathToTail = dijkstraTail.getPath(this.nodes.get(tailIndex));
            System.out.println(" PathToTail is: "+pathToTail);

            if (path.isEmpty()) {
                System.out.println("Path is empty, but pathToTail is" + pathToTail);
                path = pathToTail;
                forRemoveEdges.clear();
            } else {
                 nextHopX = (Integer.parseInt(path.get(1).toString().substring(5,7))-10);
                 nextHopY =(Integer.parseInt(path.get(1).toString().substring(7,9))-10);
                 int nextHopPoint = (nextHopX + 10) * 100 + nextHopY + 10;
                int tailIndexNext = (board.nextHopTailPoint().getX()+10)*100 + board.nextHopTailPoint().getY()+10;
                edgeYr_name = "Edge_" + String.valueOf(tailIndex) + String.valueOf(tailIndexNext);
                addLane(edgeYr_name, tailIndex, tailIndexNext, 1);

                //System.out.println("BeforeRemoveEdges "+forRemoveEdges);
                for (int i = 0; i <forRemoveEdges.size() ; i++) {
                    int toDelete = forRemoveEdges.get(i)-i;
                 //   System.out.println(edges.get(toDelete));
                 //   System.out.println(toDelete + "- remove");
                    edges.remove(toDelete);
                }
                forRemoveEdges.clear();
                //System.out.println("AfterRemoveEdges "+forRemoveEdges);

                Graph graphNext = new Graph(this.nodes, edges);
                DijkstraAlgorithm dijkstraNext = new DijkstraAlgorithm(graphNext);
                dijkstraNext.execute(this.nodes.get(nextHopPoint));
                LinkedList<Vertex> pathToTailNext = dijkstraNext.getPath(this.nodes.get(tailIndexNext));
                System.out.println(" PathToTailNext is: "+pathToTailNext);

                if (pathToTailNext.isEmpty()){
                    System.out.println("PathToTailNext is empty, but pathToTail is" + pathToTail);
                    path = pathToTail;
                }

            }

            nextHopX = (Integer.parseInt(path.get(1).toString().substring(5,7))-10);
            nextHopY =(Integer.parseInt(path.get(1).toString().substring(7,9))-10);

            int dx = head.getX() - nextHopX;
            int dy = head.getY() - nextHopY;

            if (dx<0 && board.getAt(head.getX(),head.getY())!=Elements.HEAD_LEFT &&
                    board.getAt(head.getX()+1,head.getY())!=Elements.TAIL_LEFT_DOWN &&
                    board.getAt(head.getX()+1,head.getY())!=Elements.TAIL_RIGHT_DOWN &&
                    board.getAt(head.getX()+1,head.getY())!=Elements.TAIL_LEFT_UP &&
                    board.getAt(head.getX()+1,head.getY())!=Elements.TAIL_RIGHT_UP &&
                    board.getAt(head.getX()+1,head.getY())!=Elements.TAIL_END_UP&&
                    board.getAt(head.getX()+1,head.getY())!=Elements.TAIL_END_DOWN &&
                    board.getAt(head.getX()+1,head.getY())!=Elements.TAIL_VERTICAL &&
                    board.getAt(head.getX()+1,head.getY())!=Elements.BAD_APPLE) {
                return Direction.RIGHT.toString();
            }else if (board.getAt(head.getX(),head.getY()+1)==Elements.NONE && dy<0) {
                return Direction.DOWN.toString();
            }else if ( board.getAt(head.getX(),head.getY()-1)==Elements.NONE&& dy>0) {
                return Direction.UP.toString();
            }
            if (dx>0 && board.getAt(head.getX(),head.getY())!=Elements.HEAD_RIGHT &&
                    board.getAt(head.getX()-1,head.getY())!=Elements.TAIL_LEFT_DOWN &&
                    board.getAt(head.getX()-1,head.getY())!=Elements.TAIL_RIGHT_DOWN &&
                    board.getAt(head.getX()-1,head.getY())!=Elements.TAIL_LEFT_UP &&
                    board.getAt(head.getX()-1,head.getY())!=Elements.TAIL_RIGHT_UP &&
                    board.getAt(head.getX()-1,head.getY())!=Elements.TAIL_END_UP&&
                    board.getAt(head.getX()-1,head.getY())!=Elements.TAIL_END_DOWN &&
                    board.getAt(head.getX()-1,head.getY())!=Elements.TAIL_VERTICAL &&
                    board.getAt(head.getX()-1,head.getY())!=Elements.BAD_APPLE) {
                return Direction.LEFT.toString();
            }else if (board.getAt(head.getX(),head.getY()+1)==Elements.NONE && dy<0) {
                return Direction.DOWN.toString();
            }else if ( board.getAt(head.getX(),head.getY()-1)==Elements.NONE&& dy>0) {
                return Direction.UP.toString();
            }
            if (dy<0 && board.getAt(head.getX(),head.getY())!=Elements.HEAD_UP &&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.TAIL_LEFT_DOWN &&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.TAIL_RIGHT_DOWN &&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.TAIL_LEFT_UP &&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.TAIL_RIGHT_UP &&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.TAIL_END_RIGHT&&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.TAIL_END_LEFT &&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.TAIL_HORIZONTAL &&
                    board.getAt(head.getX(),head.getY()+1)!=Elements.BAD_APPLE) {
                return Direction.DOWN.toString();
            }else if (board.getAt(head.getX()+1,head.getY())==Elements.NONE && dy<0) {
                return Direction.DOWN.toString();
            }else if ( board.getAt(head.getX()-1,head.getY())==Elements.NONE&& dy>0) {
                return Direction.UP.toString();
            }
            if (dy>0 && board.getAt(head.getX(),head.getY())!=Elements.HEAD_DOWN &&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.TAIL_LEFT_DOWN &&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.TAIL_RIGHT_DOWN &&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.TAIL_LEFT_UP &&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.TAIL_RIGHT_UP &&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.TAIL_END_RIGHT&&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.TAIL_END_LEFT &&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.TAIL_HORIZONTAL &&
                    board.getAt(head.getX(),head.getY()-1)!=Elements.BAD_APPLE)  {
                return Direction.UP.toString();
            }else if (board.getAt(head.getX()+1,head.getY())==Elements.NONE && dy<0) {
                return Direction.DOWN.toString();
            }else if ( board.getAt(head.getX()-1,head.getY())==Elements.NONE&& dy>0) {
                return Direction.UP.toString();
            }

            System.out.println(" | "+path+" | ");
            return Direction.UP.toString();
        }


    private List<Edge> CreateArrayEdges(char[][] field ) {
        edges = new ArrayList<Edge>();
        String edgeX_name;
//        String edgeXr_name;
        String edgeY_name;
//        String edgeYr_name;
//        List <Integer> forRemoveEdges = new LinkedList<Integer>();

        for (int y = 1; y < this.board.size()-1; y++) {
            for (int x = 1; x < this.board.size()-1; x++) {
                Elements ch = this.board.getAt(x,y);
                Elements chNextX = this.board.getAt(x+1,y);
                Elements chNextY = this.board.getAt(x,y+1);
        if(ch == Elements.NONE || ch == Elements.HEAD_DOWN || ch == Elements.HEAD_UP ||
                ch == Elements.HEAD_LEFT || ch == Elements.HEAD_RIGHT ||
                ch == Elements.GOOD_APPLE) {
            int point1 = (x + 10) * 100 + y + 10;
            if ((x < this.board.size()-2) && (chNextX == Elements.NONE || chNextX == Elements.HEAD_DOWN ||
                    chNextX == Elements.HEAD_UP || chNextX == Elements.HEAD_LEFT || chNextX == Elements.HEAD_RIGHT ||
                    chNextX == Elements.GOOD_APPLE)) {
                int point2 = (x + 11) * 100 + y + 10;
                edgeX_name = "Edge_" + String.valueOf(point1) + String.valueOf(point2);
                addLane(edgeX_name, point1, point2, 1);
                edgeXr_name = "Edge_" + String.valueOf(point2) + String.valueOf(point1);
                addLane(edgeXr_name, point2, point1, 1);
                if ( chNextX != Elements.GOOD_APPLE && (ch == Elements.HEAD_DOWN || ch == Elements.HEAD_UP ||
                        ch == Elements.HEAD_LEFT || ch == Elements.HEAD_RIGHT ||
                        chNextX == Elements.HEAD_DOWN || chNextX == Elements.HEAD_UP ||
                        chNextX == Elements.HEAD_LEFT || chNextX == Elements.HEAD_RIGHT))  {
                    forRemoveEdges.add(edges.size()-2);
                    forRemoveEdges.add(edges.size()-1);
                }
            }
            if ((y < this.board.size()-2) && (chNextY == Elements.NONE || chNextY == Elements.HEAD_DOWN ||
                    chNextY == Elements.HEAD_UP || chNextY == Elements.HEAD_LEFT || chNextY == Elements.HEAD_RIGHT ||
                    chNextY == Elements.GOOD_APPLE)) {
                int  point3 = (x + 10) * 100 + y + 11;
                edgeY_name = "Edge_" + String.valueOf(point1) + String.valueOf(point3);
                addLane(edgeY_name, point1, point3, 1);
                edgeYr_name = "Edge_" + String.valueOf(point3) + String.valueOf(point1);
                addLane(edgeYr_name, point3, point1, 1);
                if ( chNextY != Elements.GOOD_APPLE && (ch == Elements.HEAD_DOWN || ch == Elements.HEAD_UP ||
                        ch == Elements.HEAD_LEFT || ch == Elements.HEAD_RIGHT ||
                        chNextY == Elements.HEAD_DOWN || chNextY == Elements.HEAD_UP ||
                        chNextY == Elements.HEAD_LEFT || chNextY == Elements.HEAD_RIGHT)) {
                    forRemoveEdges.add(edges.size()-2);
                    forRemoveEdges.add(edges.size()-1);
                }
            }
          }

          }
       }
    return edges;
    }


    private List<Vertex> CreateArrayVertex(int size) {
        nodes = new ArrayList<Vertex>();
        for (int i = 0; i < size; i++) {
            Vertex location = new Vertex("Node_" + i, "Node_" + i);
            nodes.add(location);
        }
        return nodes;
    }


   private void addLane(String laneId, int sourceLocNo, int destLocNo,
                             int duration) {
            Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
            edges.add(lane);
        }

        public static void main(String[] args) {
            start(USER_NAME, WebSocketRunner.Host.REMOTE);
        }

        public static void start(String name, WebSocketRunner.Host server) {
            try {
                WebSocketRunner.run(server, name,
                        new com.codenjoy.dojo.snake.client.ManageSnake(new RandomDice()),
                        new Board());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}

