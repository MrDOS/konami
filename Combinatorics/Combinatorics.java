public class NchooseR{  
   public double RepYesOrderNo(double N, double R){
      return factorial(N + R - 1) / (factorial(R) * (factorial(N - 1)));
   }
   
   public double RepNoOrderNo(double N, double R){
      return factorial(N) / (factorial(R) * (factorial(N - R)));      
   }

   public double RepNoOrderYes(double N, double R){
      return factorial(N) / (factorial(N - R));      
   }
   
   public double RepYesOrderYes(double N, double R){
      return Math.pow(N, R);
   }
   
   public double factorial(double num){
      if (num == 0)
         return 1;
      else
         return num * factorial(num - 1);
   }
}
