package newsanalyzer.Downloader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Parrallel<URLThreaded> extends Downloader{
    private URLThreaded urlCallable;

    @Override
    public int process(List<String> urls) {
        int count = 0;
        List<Future<String>> futures = new ArrayList<>();

        for (String url : urls) {
            if(url == null){
                continue;
            }

            Callable<String> urlC = new URLThreaded(url, this);
            ExecutorService executor = Executors.newCachedThreadPool();
            futures.add(executor.submit(urlC));
        }

        for(Future<String> future : futures){
            try {
                String fileName = future.get();
                if(fileName != null)
                    count++;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return count;
    }
}
