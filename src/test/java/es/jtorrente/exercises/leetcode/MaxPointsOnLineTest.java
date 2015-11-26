package es.jtorrente.exercises.leetcode;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jtorrente on 12/09/2015.
 */
public class MaxPointsOnLineTest {

    @Test
    public void test() {
        assertEquals(3, MaxPointsOnLine.maxPoints(parse("[[0,0],[-1,-1],[2,2]]")));
        assertEquals(3, MaxPointsOnLine.maxPoints(parse("[[1,1],[1,1],[2,3]]")));

        String input = "[[0,9],[138,429],[115,359],[115,359],[-30,-102],[230,709],[-150,-686],[-135,-613],[-60,-248],[-161,-481],[207,639],[23,79],[-230,-691],[-115,-341],[92,289],[60,336],[-105,-467],[135,701],[-90,-394],[-184,-551],[150,774]]";
        assertEquals(12, MaxPointsOnLine.maxPoints(parse(input)));
        /*draw(parse(input));
        while (true);*/
    }

    public static void draw(MaxPointsOnLine.Point[] points){
        System.out.println(points.length);
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (int i=0; i<points.length; i++){
            int x = points[i].x;
            int y = points[i].y;
            if (x<minX){
                minX = x;
            }
            if (x>maxX){
                maxX = x;
            }
            if (y<minY){
                minY = y;
            }
            if (y>maxY){
                maxY = y;
            }
        }
        int[] limits = {minX,minY,maxX,maxY};
        JFrame jFrame = new JFrame("Showing: "+points.length+" points");
        jFrame.getRootPane().setLayout(new BorderLayout());
        JPanel pointsOnPlane = new JPanel(){
            @Override
            public void paint(Graphics g){
                int width = getWidth()-100;
                int height = getHeight()-100;
                float w = width/(limits[2]-limits[0]);
                float h = height/(limits[3]-limits[1]);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.RED);
                float cx = getWidth()/2-w*(limits[0]+limits[2])/2.0F;
                float cy = getHeight()/2-h*(limits[1]+limits[3])/2.0F;
                for (MaxPointsOnLine.Point point:points){
                    g.fillOval(Math.round(w*point.x-10+cx), Math.round(h*point.y-10+cy), 20, 20);
                }
            }
        };
        jFrame.getRootPane().add(pointsOnPlane, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1200, 1200);
        jFrame.setVisible(true);
    }

    @Test
    public void testParse(){
        String input3 = "[[0,1],[11,2],[222,3]]";
        MaxPointsOnLine.Point[] output3 = parse(input3);
        assertEquals(3,output3.length);
        for (int i=0; i<3; i++){
            StringBuilder builder = new StringBuilder();
            for (int j=0; j<=i; j++){
                builder.append(""+i);
            }
            assertEquals(Integer.parseInt(builder.toString()),output3[i].x);
            assertEquals(i+1,output3[i].y);
        }

        String input1 = "[[-205,-1789]]";
        MaxPointsOnLine.Point[] output1 = parse(input1);
        assertEquals(1,output1.length);
        assertEquals(-205,output1[0].x);
        assertEquals(-1789,output1[0].y);

        String input0 = "[]";
        MaxPointsOnLine.Point[] output0 = parse(input0);
        assertEquals(0, output0.length);
    }

    private static MaxPointsOnLine.Point[] parse(String str){

        if (str.length()<=2){
            return new MaxPointsOnLine.Point[0];
        }

        if (str.startsWith("[[")){
            str = str.substring(1);
        }
        if (str.endsWith("]]")){
            str = str.substring(0, str.length()-1);
        }

        List<MaxPointsOnLine.Point> pointList = new ArrayList<>();
        boolean started = false;
        boolean readingY = false;
        int sign = 1;
        int valX = 0;
        int valY = 0;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (c=='['){
                started = true;
                readingY = false;
                valX = valY = 0;
                sign = 1;
            } else if (Character.isDigit(c)){
                if (readingY) {
                    valY*=10; valY+=(c-'0');
                } else {
                    valX*=10; valX+=(c-'0');
                }
            } else if (c=='-') {
                sign = -1;
            } else if (c==',' && started) {
                valX *=sign;
                readingY = true;
                sign = 1;
            } else if (c==']'){
                started = false;
                valY *= sign;
                MaxPointsOnLine.Point point = new MaxPointsOnLine.Point(valX, valY);
                pointList.add(point);
            }
        }

        MaxPointsOnLine.Point[] points = pointList.toArray(new MaxPointsOnLine.Point[0]);
        return points;
    }
}
