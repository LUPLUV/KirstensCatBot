package dev.lupluv.kc;

import dev.lupluv.kc.events.GuildJoinEventHandler;
import dev.lupluv.kc.events.MessageReceivedEventHandler;
import dev.lupluv.kc.events.ReactionEvent;
import dev.lupluv.kc.events.SlashCommandHandler;
import dev.lupluv.kc.files.FileManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.session.ShutdownEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Cat implements EventListener {

    public static JDA jda;
    private static FileManager fileManager;

    public static void main(String[] args){
        new Cat();
    }

    public Cat() {
        JDABuilder jdaBuilder = JDABuilder.create("ODc0MzYzODEzNjk1MjI1ODY2.GNf6eS.HX3m8P7epMuTluk1XIMVJmVOqPCGrZM0V3r1WI"
                , GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_BANS, GatewayIntent.GUILD_MESSAGE_REACTIONS
                , GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.DIRECT_MESSAGES
                , GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MESSAGES);
        jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jdaBuilder.setActivity(Activity.streaming("kirstenjbx", "https://twitch.tv/kirstenjbx"));
        jdaBuilder.addEventListeners(new MessageReceivedEventHandler(), new GuildJoinEventHandler(), new ReactionEvent(), this, new SlashCommandHandler());
        jda = jdaBuilder.build();
        jda.upsertCommand("ping", "Calculate ping of the bot").queue();

        fileManager = new FileManager();

        try {
            fileManager.loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if(genericEvent instanceof ReadyEvent){
            System.out.println("Bot Ready");
        }else if(genericEvent instanceof ShutdownEvent){
            jda.getShardManager().setStatus(OnlineStatus.OFFLINE);
            System.out.println("Bot offnline.");
        }
    }

    public static FileManager getFileManager() {
        return fileManager;
    }

}
