package newsanalyzer.Downloader;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
public class Sequential { @Override
public int process(List<String> urls) throws IOException {
    long startTime = System.nanoTime();

    int count = 0;

    for (String url : urls) {
        try {
            String fileName = saveUrl2File(url);
            if (fileName != null)
                count++;
        } catch (Exception e) {
            System.out.println("An exception was caught while downloading your File");
        }
    }

    System.out.println("Die Laufzeit betrug:: "+(System.nanoTime()-startTime)/1000000);
    return count;
}

}
