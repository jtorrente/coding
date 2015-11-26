package es.jtorrente.exercises.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by jtorrente on 07/09/2015.
 */
public class Island {

    public static int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length==0 || grid[0].length == 0) {
            return 0;
        }

        int m=grid.length;
        int n=grid[0].length;

        int nIslands = 0;
        Queue<GridPosition> positions = new ArrayDeque<>(m*n);
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                while (!positions.isEmpty()) {
                    GridPosition p = positions.poll();
                    int x = p.i; int y = p.j;
                    checkAdjPos(grid, positions, x-1, y);
                    checkAdjPos(grid, positions, x+1, y);
                    checkAdjPos(grid, positions, x, y+1);
                    checkAdjPos(grid, positions, x, y-1);
                }

                if (grid[i][j] == '1') {
                    positions.add(new GridPosition(i,j));
                    nIslands++;
                }
            }
        }
        return nIslands;
    }

    private static void checkAdjPos(char[][]grid, Queue<GridPosition> positions, int i, int j) {
        if (i>=0 && i<grid.length && j>=0 && j<grid[0].length && grid[i][j] == '1') {
            positions.add(new GridPosition(i, j));
            grid[i][j] = 'x';
        }
    }

    protected static class GridPosition {
        protected int i;
        protected int j;
        public GridPosition(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static int numIslandsCandidates(char[][] grid) {
        if (grid == null || grid.length==0 || grid[0].length==0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        List<IslandBoundary> islands = new ArrayList<>();
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j]=='1') {
                    islands.add(new IslandBoundary(i,j));
                }
            }
        }

        while (mergeIslands(islands)>0);
        return islands.size();
    }

    private static int mergeIslands(List<IslandBoundary> islands) {
        int merged = 0;
        for (int i=0; i<islands.size(); i++){
            for (int j=i+1; j<islands.size(); j++){
                if (islands.get(i).merge(islands.get(j))) {
                    islands.remove(j);
                    j--;
                }
            }
        }
        return merged;
    }

    protected static class IslandBoundary {
        List<Coordinate> coordinates = new ArrayList<>();

        public IslandBoundary(int i, int j) {
            coordinates.add(new Coordinate(i, j));
        }

        public boolean merge(IslandBoundary other) {
            boolean merge = false;
            for (Coordinate c1: coordinates) {
                for (Coordinate c2: other.coordinates) {
                    if (c1.isTangent(c2)){
                        merge = true;
                        break;
                    }
                }
            }
            if (merge) {
                coordinates.addAll(other.coordinates);
            }
            return merge;
        }
    }

    protected static class Coordinate {
        int i, j;
        public Coordinate(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public boolean isTangent(Coordinate other) {
            return (i==other.i && Math.abs(j-other.j)==1) ||
                    (j==other.j && Math.abs(i-other.i)==1);
        }
    }

    public static int numIslandsDP(char[][] grid) {
        if (grid == null || grid.length==0 || grid[0].length==0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int[][] f = new int[m+1][n+1];
        for (int i=m-1; i>=0; i--) {
            for (int j=n-1; j>=0; j--) {
                int adj = 0;
                if (grid[i][j] == '1') {
                    boolean right = (j<n-1 && grid[i][j+1]=='1');
                    boolean down = (i<m-1 && grid[i+1][j]=='1');
                    boolean down_right = (j<n-1 && i<m-1 && grid[i+1][j+1]=='1');
                    if (right && down && !down_right){
                        adj = -1;
                    } else if (!right && !down){
                        adj = 1;
                    }
                }

                f[i][j] = f[i+1][j] + f[i][j+1] - f[i+1][j+1] + adj;
            }
        }
        return f[0][0];
    }
}
