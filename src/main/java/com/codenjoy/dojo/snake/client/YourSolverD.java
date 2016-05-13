package com.codenjoy.dojo.snake.client;


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.model.Elements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * User: your name
 */
public class YourSolverD implements Solver<Board> {

    private static final String USER_NAME = "blagodarno@gmail.com";

    private Dice dice;
    private Board board;
    private List<Vertex> nodes;
    private List<Edge> edges;

    public YourSolverD(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;


            nodes = new ArrayList<Vertex>();
            edges = new ArrayList<Edge>();
            for (int i = 0; i < 2525; i++) {
                Vertex location = new Vertex("Node_" + i, "Node_" + i);
                nodes.add(location);
            }


        char[][] field = board.getField();

        // found had of snake, apple
        int snakeHeadX = -1;
        int snakeHeadY = -1;
        int snakeHeadPoint = 0;
        int appleX = -1;
        int appleY = -1;
        int applePoint = 0;
        int snakeTailX = -1;
        int snakeTailY = -1;
        int snakeTailPoint = 0;
        int point1 = 0;
        int point2 = 0;
        int point3 = 0;
        String edgeX_name ;
        String edgeXr_name ;
        String edgeY_name ;
        String edgeYr_name ;
        int nextHopX;
        int nextHopY;
/*

        // found had of snake
        for (int x = 1; x < field.length-1; x++) {
            for (int y = 1; y < field.length-1; y++) {
                char ch = field[x][y];
                if (ch == Elements.HEAD_DOWN.ch() ||
                        ch == Elements.HEAD_UP.ch() ||
                        ch == Elements.HEAD_LEFT.ch() ||
                        ch == Elements.HEAD_RIGHT.ch())
                {
                    snakeHeadX = x;
                    snakeHeadY = y;
                    snakeHeadPoint = (x + 10) * 100 + y + 10;
                    break;

                }
            }
            if (snakeHeadX != -1) {
                break;
            }
        }

        // found apple
        for (int x = 1; x < field.length-1; x++) {
            for (int y = 1; y < field.length-1; y++) {
                char ch = field[x][y];
                if (ch == Elements.GOOD_APPLE.ch()) {
                    appleX = x;
                    appleY = y;
                    applePoint = (x + 10) * 100 + y + 10;
                    break;

                }
            }
            if (appleX != -1) {
                break;
            }
        }
*/

//        System.out.println("1:1= |"+field[1][1]+"|");
//        System.out.println("field.length-1:field.length-1= |"+field[field.length-1][field.length-1]+"|");
//        System.out.println("field.length-1:field.length-2= |"+field[field.length-2][field.length-2]+"|");
//        System.out.println("field.length= "+field.length);

        for (int y = 1; y < field.length-1; y++) {
            for (int x = 1; x < field.length-1; x++) {
                char ch = field[x][y];
                char chNextX = field[x+1][y];
                char chNextY = field[x][y+1];
                if (ch == Elements.HEAD_DOWN.ch() ||
                        ch == Elements.HEAD_UP.ch() ||
                        ch == Elements.HEAD_LEFT.ch() ||
                        ch == Elements.HEAD_RIGHT.ch())
                {
                    snakeHeadX = x;
                    snakeHeadY = y;
                    snakeHeadPoint = (x + 10) * 100 + y + 10;
                }
                if (ch == Elements.GOOD_APPLE.ch()) {
                    appleX = x;
                    appleY = y;
                    applePoint = (x + 10) * 100 + y + 10;
                }
                if (ch == Elements.TAIL_END_DOWN.ch() ||
                        ch == Elements.TAIL_END_UP.ch() ||
                        ch == Elements.TAIL_END_LEFT.ch() ||
                        ch == Elements.TAIL_END_RIGHT.ch())
                {
                    snakeTailX = x;
                    snakeTailY = y;
                    snakeTailPoint = (x + 10) * 100 + y + 10;
                }
                if(ch == ' '|| ch == Elements.HEAD_DOWN.ch() || ch == Elements.HEAD_UP.ch() ||
                        ch == Elements.HEAD_LEFT.ch() || ch == Elements.HEAD_RIGHT.ch() ||
                        ch == Elements.GOOD_APPLE.ch() ) {
                    point1 = (x + 10) * 100 + y + 10;
                    if ((x < 14) && (chNextX == ' '|| chNextX == Elements.HEAD_DOWN.ch() || chNextX == Elements.HEAD_UP.ch() ||
                            chNextX == Elements.HEAD_LEFT.ch() || chNextX == Elements.HEAD_RIGHT.ch() ||
                            chNextX == Elements.GOOD_APPLE.ch() )) {
                        point2 = (x + 11) * 100 + y + 10;
                        edgeX_name = "Edge_" + String.valueOf(point1) + String.valueOf(point2);
                        addLane(edgeX_name, point1, point2, 1);
                        edgeXr_name = "Edge_" + String.valueOf(point2) + String.valueOf(point1);
                        addLane(edgeXr_name, point2, point1, 1);
                    }
                    if ((y < 14) && (chNextY == ' '|| chNextY == Elements.HEAD_DOWN.ch() || chNextY == Elements.HEAD_UP.ch() ||
                            chNextY == Elements.HEAD_LEFT.ch() || chNextY == Elements.HEAD_RIGHT.ch() ||
                            chNextY == Elements.GOOD_APPLE.ch() )) {
                        point3 = (x + 10) * 100 + y + 11;
                        edgeY_name = "Edge_" + String.valueOf(point1) + String.valueOf(point3);
                        addLane(edgeY_name, point1, point3, 1);
                        edgeYr_name = "Edge_" + String.valueOf(point3) + String.valueOf(point1);
                        addLane(edgeYr_name, point3, point1, 1);
                    }
                }

            }
        }



        System.out.println(" snakeHeadPoint is: " + snakeHeadPoint);
        System.out.println(" applePoint is: " + applePoint);

        int dxA = snakeHeadX - appleX;
        int dyA = snakeHeadY - appleY;
//
//        // don't eat break ( snake in vertical position )
//        if (((field[snakeHeadX][snakeHeadY+1]=='☼') && (field[snakeHeadX][snakeHeadY]=='▼')) ||
//                ((field[snakeHeadX][snakeHeadY-1]=='☼') && (field[snakeHeadX][snakeHeadY]=='▲'))) {
//            if (dxA<0 && field[snakeHeadX+1][snakeHeadY]==' ') {
//                return Direction.RIGHT.toString();
//            } else {
//                return Direction.LEFT.toString();
//            }
//        }
//        // don't eat break ( snake in horizontal position )
//        if (((field[snakeHeadX+1][snakeHeadY]=='☼') && (field[snakeHeadX][snakeHeadY]=='►')) ||
//                ((field[snakeHeadX-1][snakeHeadY]=='☼') && (field[snakeHeadX][snakeHeadY]=='◄'))) {
//            if (dyA<0 && field[snakeHeadX][snakeHeadY+1]==' ') {
//                return Direction.DOWN.toString();
//            } else {
//                return Direction.UP.toString();
//            }
//        }


        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, edges);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(snakeHeadPoint));
        LinkedList<Vertex> path = dijkstra.getPath(nodes.get(applePoint));

        System.out.println(" Path is: "+path);

        if(path.isEmpty()){
//            Graph graph1 = new Graph(nodes, edges);
//            DijkstraAlgorithm dijkstra1 = new DijkstraAlgorithm(graph1);
            dijkstra.execute(nodes.get(snakeHeadPoint));
            LinkedList<Vertex> pathToTail = dijkstra.getPath(nodes.get(snakeTailPoint));

            if (pathToTail.isEmpty()) {
                if (field[snakeHeadX][snakeHeadY+1] == ' ') {
                    return Direction.DOWN.toString();
                }else if (field[snakeHeadX][snakeHeadY-1] == ' ') {
                    return Direction.UP.toString();
                }else if (field[snakeHeadX + 1][snakeHeadY] == ' ') {
                    return Direction.RIGHT.toString();
                }else if (field[snakeHeadX - 1][snakeHeadY] == ' ') {
                    return Direction.LEFT.toString();
                }
            }

            System.out.println(" PathToTail is: "+pathToTail);
            path = pathToTail;
        }

        nextHopX = (Integer.parseInt(path.get(1).toString().substring(5,7))-10);
        nextHopY =(Integer.parseInt(path.get(1).toString().substring(7,9))-10);

        int dx = snakeHeadX - nextHopX;
        int dy = snakeHeadY - nextHopY;
//        System.out.println(" nextHopX is: " + nextHopX);
//        System.out.println(" nextHopY is: " + nextHopY);
//        System.out.println(" dx is: " + dx);
//        System.out.println(" dy is: " + dy);

        if (dx<0 && (field[snakeHeadX][snakeHeadY]!='◄')  && (field[snakeHeadX+1][snakeHeadY]!='║' ) &&
                (field[snakeHeadX+1][snakeHeadY]!='╙') && (field[snakeHeadX+1][snakeHeadY]!='╓') &&
                (field[snakeHeadX+1][snakeHeadY]!='╗') && (field[snakeHeadX+1][snakeHeadY]!='╝') &&
                (field[snakeHeadX+1][snakeHeadY]!='╔') && (field[snakeHeadX+1][snakeHeadY]!='╚') &&
                (field[snakeHeadX+1][snakeHeadY]!='☻')){
            return Direction.RIGHT.toString();
        }else if (field[snakeHeadX][snakeHeadY+1] == ' ' && dy<0) {
            return Direction.DOWN.toString();
        }else if (field[snakeHeadX][snakeHeadY-1] == ' ' && dy>0) {
            return Direction.UP.toString();
        }
        if (dx>0 && (field[snakeHeadX][snakeHeadY]!='►')&& (field[snakeHeadX-1][snakeHeadY]!='║' ) &&
                (field[snakeHeadX-1][snakeHeadY]!='╙') && (field[snakeHeadX-1][snakeHeadY]!='╓')&&
                (field[snakeHeadX-1][snakeHeadY]!='╗') && (field[snakeHeadX-1][snakeHeadY]!='╝')&&
                (field[snakeHeadX-1][snakeHeadY]!='╔') && (field[snakeHeadX-1][snakeHeadY]!='╚')&&
                (field[snakeHeadX-1][snakeHeadY]!='☻')){
            return Direction.LEFT.toString();
        }else if (field[snakeHeadX][snakeHeadY+1] == ' ' && dy<0) {
            return Direction.DOWN.toString();
        }else if (field[snakeHeadX][snakeHeadY-1] == ' ' && dy>0) {
            return Direction.UP.toString();
        }
        if (dy<0 && (field[snakeHeadX][snakeHeadY]!='▲')&& (field[snakeHeadX][snakeHeadY+1]!='═' ) &&
                (field[snakeHeadX][snakeHeadY+1]!='╘') && (field[snakeHeadX][snakeHeadY+1]!='╕')&&
                (field[snakeHeadX][snakeHeadY+1]!='╗') && (field[snakeHeadX][snakeHeadY+1]!='╝')&&
                (field[snakeHeadX][snakeHeadY+1]!='╔') && (field[snakeHeadX][snakeHeadY+1]!='╔')&&
                (field[snakeHeadX][snakeHeadY+1]!='☻')){
            return Direction.DOWN.toString();
        }else if (field[snakeHeadX + 1][snakeHeadY] == ' ' && dx<0) {
            return Direction.RIGHT.toString();
        }else if (field[snakeHeadX - 1][snakeHeadY] == ' ' && dx>0) {
            return Direction.LEFT.toString();
        }
        if (dy>0 && (field[snakeHeadX][snakeHeadY]!='▼') && (field[snakeHeadX][snakeHeadY-1]!='═' ) &&
                (field[snakeHeadX][snakeHeadY-1]!='╘') && (field[snakeHeadX][snakeHeadY-1]!='╕') &&
                (field[snakeHeadX][snakeHeadY-1]!='╗') && (field[snakeHeadX][snakeHeadY-1]!='╝') &&
                (field[snakeHeadX][snakeHeadY-1]!='╔') && (field[snakeHeadX][snakeHeadY-1]!='╚') &&
                (field[snakeHeadX][snakeHeadY-1]!='☻')){
            return Direction.UP.toString();
        }else if (field[snakeHeadX + 1][snakeHeadY] == ' '&& dx<=0) {
            return Direction.RIGHT.toString();
        }else if (field[snakeHeadX - 1][snakeHeadY] == ' '  && dx>=0) {
            return Direction.LEFT.toString();
        }

        System.out.println(" | "+path+" | ");


        return Direction.UP.toString();
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
                    new YourSolverD(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
