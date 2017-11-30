public class TestThread {

   public static void main(String args[]) {
      RunnableDemo R1 = new RunnableDemo( "Thread A");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Thread B");
      R2.start();
   }   
}