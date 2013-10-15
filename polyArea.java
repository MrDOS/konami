public class area
{
   public static void main(String[] args)
   {
      int[][] arr = new int[][]{{0, 0}, {2, 0}, {3, -1}, {2, -1}, {3, -3}, {0, -3}};
      double area = 0.0;
      int N = arr.length;
      
      for (int i = 0; i < N - 1; i++)
      {
         int j = (i+1) % N;
         area = area + arr[i][0] * arr[j][1];
         area = area - arr[i][1] * arr[j][0];
      }
      System.out.println(Math.abs(area) / 2);
   }
}
