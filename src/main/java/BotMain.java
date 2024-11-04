import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BotMain extends ListenerAdapter {
    // Initialize the logger
    private static final Logger logger = Logger.getLogger(BotMain.class.getName());

    public static void main(String[] args) {
        // Configure logging level
        logger.setLevel(Level.INFO);

        try {
            // Initialize the bot with your token
            String token = ""; // Replace with your bot's token
            JDABuilder builder = JDABuilder.createDefault(token);

            // Register event listeners
            builder.addEventListeners(new BotMain());
            builder.build();

            // Log that the bot started successfully
            logger.info("Bot is online and running.");

        } catch (LoginException e) {
            logger.log(Level.SEVERE, "Failed to log in to Discord", e);
        }
    }

    // Event listener for message received
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Get the message
        Message message = event.getMessage();
        String content = message.getContentRaw();

        // Log the received message
        logger.info("Received message: " + content + " from " + event.getAuthor().getName());

        // Check if message content is "!ping"
        if (content.equalsIgnoreCase("!ping")) {
            event.getChannel().sendMessage("Pong!").queue();
            logger.info("Responded to !ping with Pong!");
        } else if (content.equalsIgnoreCase("!name")) {
            String userName = event.getAuthor().getName();
            event.getChannel().sendMessage("Your name is " + userName).queue();
            logger.info("Responded to !name with user's name: " + userName);
        }
    }
}
