package be.springboot.pp.searchtypeahead.workers;

import be.springboot.pp.searchtypeahead.interfaces.SuggestionDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbToCacheSyncWorker implements Runnable {

    private final SuggestionDataStructure suggestionDataStructure;

    @Autowired
    public DbToCacheSyncWorker(SuggestionDataStructure suggestionDataStructure) {
        this.suggestionDataStructure = suggestionDataStructure;
    }

    @Override
    public void run() {
        doSynchronization();
    }

    private void doSynchronization() {
        while (true) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("DbToCacheSyncWorker: starts: " + Thread.currentThread().getName());
            suggestionDataStructure.reload();
        }
    }
}
