package elina.railwayApp;


import com.rabbitmq.client.*;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Log4j
public class Listener {

    private static final String EXCHANGE_NAME = "messages";

    private Channel channel;
    private Connection connection;
    private DataManager dataManager = DataManager.getInstance();

    public void start() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(EXCHANGE_NAME, false, false, false, null);
        System.out.println("Receive message");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                log.info(" [x] Received '" + message + "'");
                if (message.contains("create") || message.contains("delete") || message.contains("update")) {
                    Long id = getMessageInfo(message);
                    log.info("ID = " + getMessageInfo(message));
                    dataManager.changeState(message, id);
                }
            }
        };
        channel.basicConsume(EXCHANGE_NAME, true, consumer);
    }

    public void stop() throws IOException, TimeoutException {
        channel.close();
        connection.close();
    }

    public Long getMessageInfo(String message) {
        if (message.contains("create") || message.contains("update") || message.contains("delete")) {
            return Long.valueOf(message.substring(message.indexOf("id=")).replace("id=", ""));
        }
        return null;
    }

}
