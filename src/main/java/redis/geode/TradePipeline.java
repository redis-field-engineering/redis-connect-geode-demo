package redis.geode;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TradePipeline {
    private static final BlockingQueue<Trade> tradeQueue = new LinkedBlockingQueue<>();
    public static void sendMessage(Trade trade) throws InterruptedException{
        tradeQueue.put(trade);
    }

    public static Trade receiveMessage() throws InterruptedException{
        return tradeQueue.take();
    }
}
