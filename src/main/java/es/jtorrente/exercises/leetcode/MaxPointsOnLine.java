package es.jtorrente.exercises.leetcode;

import es.jtorrente.datastructures.dictionaries.Dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jtorrente on 12/09/2015.
 */
public class MaxPointsOnLine {

    /**
     * Definition for a point.
     */
    public static class Point {
          int x;
          int y;
          public Point() { x = 0; y = 0; }
          public Point(int a, int b) { x = a; y = b; }
    }


    public static int maxPoints(Point[] points) {
        if (points==null){
            return 0;
        }
        HashMap<Integer, HashMap<Integer, Integer>> count = new HashMap<>();
        int nDif = 0;
        for (int i=0; i<points.length; i++){
            int x = points[i].x;
            int y = points[i].y;
            if (!count.containsKey(x)){
                count.put(x, new HashMap<>());
            }
            if (!count.get(x).containsKey(y)){
                count.get(x).put(y,0);
                nDif++;
            }
            count.get(x).put(y,count.get(x).get(y)+1);
        }
        int nMaxPoints = points.length==0?0:1;
        Point[] difPoints = new Point[nDif];
        {
            int i=0;
            for (Map.Entry<Integer, HashMap<Integer, Integer>> entry:count.entrySet()) {
                HashMap<Integer, Integer> ys = entry.getValue();
                int x = entry.getKey();
                for (Map.Entry<Integer, Integer> entry2: ys.entrySet()){
                    int y = entry2.getKey();
                    int c = entry2.getValue();
                    difPoints[i] = new Point(x,y);
                    if (c>nMaxPoints){
                        nMaxPoints=c;
                    }
                    i++;
                }
            }
            points = difPoints;
        }

        for (int i=0; i<points.length; i++) {
            int x0 = points[i].x;
            int y0 = points[i].y;
            for (int j=i+1; j<points.length; j++) {
                int x1 = points[j].x;
                int y1 = points[j].y;
                int dx = x1-x0;
                int dy = y1-y0;

                float a=0, b=0, c=1;
                int nPoints = count.get(x0).get(y0)+count.get(x1).get(y1);
                if (dx==0 && dy==0) {
                    continue;
                } else if (dx!=0 && dy==0) {
                    if (y0==0) {
                        c=0;b=1;
                    } else {
                        b = -1.0F/y0;
                    }
                } else if (dx==0 && dy!=0) {
                    if (x0==0) {
                        c=0;a=1;
                    } else {
                        a = -1.0F/x0;
                    }
                } else if (dx!=0 && dy!=0) {
                    if (x0==0 && y0==0 || x1==0 && y1==0) {
                        c=0;
                        b=1;
                        if (x0==0 && y0==0) {
                            a = -1.0F*y1/x1;
                        } else {
                            a = -1.0F*y0/x0;
                        }
                    } else {
                        b = -1.0F*dx / (y0*dx-x0*dy);
                        a = -b*dy / dx;
                    }
                }

                for (int k=j+1; k<points.length; k++) {
                    int x2 = points[k].x;
                    int y2 = points[k].y;
                    float val = a*x2+b*y2+c;
                    if (val>=-0.0005F && val<=0.0005F){
                        nPoints+=count.get(x2).get(y2);
                    }
                }
                if (nPoints>nMaxPoints) {
                    nMaxPoints = nPoints;
                }
            }
        }
        return nMaxPoints;
    }
}
