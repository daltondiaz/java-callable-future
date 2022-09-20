package daltondiaz.github.java.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main implements Callable<String>{

    public String call() throws Exception {
        Random rand = new Random();
        Thread.sleep((1000)* rand.nextLong(10));
        return Thread.currentThread().getName();
    }
    public static void main(String []args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>> list = new ArrayList<Future<String>>();
        Callable<String> callable = new Main();
        for(int i=0; i<100; i++) {
            Future<String> future = executorService.submit(callable);
            list.add(future);
        }

        for(Future<String> fut: list) {
            try {
                System.out.println(String.format(" %s - %s",new Date(), fut.get()));
            } catch(InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
