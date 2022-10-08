package dev.lupluv.kc.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandHandler extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e){
        String name = e.getName();
        if(name.equalsIgnoreCase("ping")){
            long time = System.currentTimeMillis();
            e.reply("Pong!").setEphemeral(true)
                    .flatMap(v ->
                            e.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis()-time)
                    ).queue();
        }
    }

}
