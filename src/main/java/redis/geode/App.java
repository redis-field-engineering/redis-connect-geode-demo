package redis.geode;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DeploymentException, IOException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String token = System.getenv("token");
        String uri = String.format("wss://ws.finnhub.io/?token=%s", token);
        System.out.println( "Hello World!" );
        container.connectToServer(WebSocketClient.class, URI.create(uri));
        System.out.println("Connecting to " + uri);

        Thread consumerThread = new Thread(()-> {
            String gemfireHost = "localhost";
            int gemfirePort = 10334;

            if(System.getProperty("gemfire.host") != null){
                gemfireHost = System.getProperty("gemfire.host");
            }

            if(System.getProperty("gemfire.port") != null){
                gemfirePort = Integer.parseInt(System.getProperty("gemfire.port"));
            }


            try(ClientCache cache = new ClientCacheFactory().addPoolLocator(gemfireHost, gemfirePort).create()){
                Region<String, Trade> tradeRegion = cache.<String,Trade>createClientRegionFactory(ClientRegionShortcut.PROXY).create("trades");
                while(true){
                    try {
                        Trade t = TradePipeline.receiveMessage();
                        UUID uuid = UUID.randomUUID();
                        String key = String.format("trade:%s", uuid);
                        tradeRegion.put(key, t);
                    } catch (Exception e){
                        if (e instanceof InterruptedException){
                            throw new RuntimeException(e);
                        }
                        e.printStackTrace();
                    }
                }
            }
        });

        consumerThread.start();

        // Wait to keep the client running
        new java.util.Scanner(System.in).nextLine();
        consumerThread.interrupt();
    }
}
