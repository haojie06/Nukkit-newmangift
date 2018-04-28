package NewmanGift;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;
import com.google.common.base.Strings;
import com.sun.jna.StringArray;
import oshi.jna.platform.unix.solaris.LibKstat;

import javax.tools.Tool;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class NewmanGift extends PluginBase {
    static NewmanGift gift;

    static List<String> items,players = new ArrayList();
    public void onEnable() {
        initConfig();
        loadConfig();
        gift = this;
        this.getServer().getPluginManager().registerEvents(new NewplayerListener(),this);
        this.getLogger().info(TextFormat.GREEN + "[load]newmangift新人礼包");
        checkAndLoadYML();
        super.onEnable();
    }

    public void initConfig(){
        saveDefaultConfig();
        saveResource("players.yml");
    }
    public void loadConfig() {
        this.reloadConfig();
        items = getConfig().getStringList("gift");
    }

    /*
    String s = getConfig().getString("gift");
     getServer().getConsoleSender().sendMessage(s);

     s = getConfig().getString("gift");
     getServ	/*
  * 检查、加载文件
  */
   	/*
	 * 检查、加载文件
	 */
    public void checkAndLoadYML(){
        File file = new File(this.getDataFolder().getPath()+"/players.yml");
        if(!file.exists())
            try {
            file.createNewFile();
            getServer().getConsoleSender().sendMessage("!!!!!!!未找到players");

        } catch (IOException e) {e.printStackTrace();			}

        this.saveResource("players.yml",false);
        Scanner input= null;
        try {	input = new Scanner(file);		} catch (FileNotFoundException e) { e.printStackTrace(); }
        while(input.hasNextLine()){
            // String[] cmdsArr = Tool.split(input.nextLine(), ' ');
            String line = input.nextLine();
            players.add(line);
            getServer().getConsoleSender().sendMessage(line);
        }
        input.close();
        this.getServer().getConsoleSender().sendMessage(TextFormat.BLUE + "数据读取完成");
    }

    public static NewmanGift getPlugin()
    {
        return gift;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }
}
