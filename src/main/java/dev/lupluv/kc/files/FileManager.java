package dev.lupluv.kc.files;

import net.dv8tion.jda.api.entities.Activity;
import org.simpleyaml.configuration.file.FileConfiguration;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    public void loadFiles() throws IOException {
        File folder = new File("KirstensCat");
        File configFile = new File("KirstensCat//config.yml");
        File rrFile = new File("KirstensCat//reactionroles.yml");
        if(!folder.exists()) folder.mkdir();
        if(!configFile.exists()){
            configFile.createNewFile();
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
            List<Activity> activities = new ArrayList<>();
            activities.add(Activity.playing("Brits SMP"));
            cfg.set("Activities.0.Type", "STREAMING");
            cfg.set("Activities.0.Name", "kirstenjbx");
            cfg.set("Activities.0.Url", "https://www.twitch.tv/kirstenjbx");
            cfg.set("Activities.1.Type", "PLAYING");
            cfg.set("Activities.1.Name", "Brits SMP");
            cfg.set("Activities.2.Type", "LISTENING");
            cfg.set("Activities.2.Name", "Creeper Aaaaaw Man");
            cfg.set("Activities.3.Type", "COMPETING");
            cfg.set("Activities.3.Name", "MC Championship 2029");
            cfg.save(configFile);
        }
        if(!rrFile.exists()){
            rrFile.createNewFile();
            FileConfiguration cfg = YamlConfiguration.loadConfiguration(rrFile);
        }
    }

    public FileConfiguration getReactionRoleConfiguration(){
        try {
            return YamlConfiguration.loadConfiguration(new File("KirstensCat//reactionroles.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public FileConfiguration getConfig(){
        try {
            return YamlConfiguration.loadConfiguration(new File("KirstensCat//config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Activity[] getActivities(){
        FileConfiguration cfg = getConfig();
        List<Activity> activities = new ArrayList<>();
        for(int i = 0; i<20; i++){
            if(cfg.getString("Activities." + i + ".Type") != null){
                Activity ac = null;
                String parKey = "Activities." + i + ".";
                if(cfg.getString(parKey + "Type").equalsIgnoreCase("STREAMING")){
                    ac = Activity.streaming(cfg.getString(parKey + "Name")
                            , cfg.getString(parKey + "Url"));
                }else if(cfg.getString(parKey + "Type").equalsIgnoreCase("LISTENING")){
                    ac = Activity.listening(cfg.getString(parKey + "Name"));
                }else if(cfg.getString(parKey + "Type").equalsIgnoreCase("PLAYING")){
                    ac = Activity.playing(cfg.getString(parKey + "Name"));
                }else if(cfg.getString(parKey + "Type").equalsIgnoreCase("COMPETING")){
                    ac = Activity.competing(cfg.getString(parKey + "Name"));
                }
                activities.add(ac);
            }
        }
        return (Activity[]) activities.toArray();
    }

}
