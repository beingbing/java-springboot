package be.springboot.pp.dsalgo.heaps;

import java.util.PriorityQueue;

public class S004_lc_0973 {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.distanceFromOrigin, a.distanceFromOrigin));

        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            int distanceSquared = x * x + y * y;
            maxHeap.add(new Point(distanceSquared, x, y)); // Add point to the heap
            if (maxHeap.size() > k) maxHeap.poll(); // If the heap exceeds size k, remove the farthest point
        }

        // Extract the k closest points from the heap
        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            Point closest = maxHeap.poll();
            result[i][0] = closest.x;
            result[i][1] = closest.y;
        }

        return result;
    }
}

class Point {
    int x;
    int y;
    int distanceFromOrigin;

    public Point(int distanceFromOrigin, int x, int y) {
        this.x = x;
        this.y = y;
        this.distanceFromOrigin = distanceFromOrigin;
    }
}
