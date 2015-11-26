package es.jtorrente.exercises.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 07/09/2015.
 */
public class IslandTest {

    @Test
    public void test1x1(){
        int[][] grid = {{1}};
        test(grid,1);

        int[][] grid2 = {{0}};
        test(grid2,0);

        test(new int[0][0], 0);
    }

    @Test
    public void test4x5(){
        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,1,0,0},{0,0,0,1,1}};
        test(grid, 3);

        int[][] grid2 = {{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};
        test(grid2, 1);
    }

    @Test
    public void testHole(){
        int[][] grid = {{1,1,1},{1,0,1},{1,1,1}};
        test(grid,1);
    }

    private void test(int[][] intGrid, int expected){
        char[][] grid = new char[intGrid.length][intGrid.length>0?intGrid[0].length:0];
        for (int i=0; i<intGrid.length; i++) {
            for (int j=0; j<intGrid[i].length; j++){
                grid[i][j] = intGrid[i][j]==1?'1':'0';
            }
        }
        assertEquals(expected, Island.numIslandsCandidates(grid));
        assertEquals(expected, Island.numIslandsBFS(grid));
    }
}
