public class Polygons{
   public static double area(Point[] points){
      double area = 0.0;
      for (int i = 0; i < points.length - 1; i++){
         int j = (i+1) % points.length;
         area = area + points[i].x * points[j].y;
         area = area - points[i].y * points[j].x;
      }      
      return Math.abs(area / 2.0);
   }
   
   private static class Point{
      public double x;
      public double y;
      public Point(double anX, double anY)
      {
         this.x = anX;
         this.y = anY;
      }  
   }
}
