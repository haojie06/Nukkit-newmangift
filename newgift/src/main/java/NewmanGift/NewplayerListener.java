package NewmanGift;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.utils.TextFormat;
import oshi.jna.platform.unix.solaris.LibKstat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class NewplayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Boolean ifNew = true;
        String name;
        List<String> items, players;
        name = event.getPlayer().getName();
        NewmanGift plugin;
        plugin = NewmanGift.getPlugin();
        //判断加入的玩家是否为新玩家
        players = NewmanGift.players;
        for (String p : players) {
            if (p.equals(name))
                ifNew = false;
        }

        // event.getPlayer().sendMessage(TextFormat.GREEN + "欢迎来到服务器");
        if (ifNew) {

            items = NewmanGift.items;
            //解析list
            for (String s : items) {

                String id, num;
                String get[];
                get = s.split(";");
                id = get[0];
                num = get[1];
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "give " + name + " " + id + " " + num);
                //plugin.getServer().getConsoleSender().sendMessage("给予物品：" + id + "数量" + num);
            }
            plugin.getServer().broadcastMessage(TextFormat.GREEN + "欢迎新玩家" + name + "来到服务器");
            //将该玩家添加入列表，下次进去服务器时不再赠送
            players.add(name);
            PrintWriter printWriter;
            File file = new File(plugin.getDataFolder().getPath() + "/players.yml");
            try{
                 printWriter = new PrintWriter(file);
                for (String s : players) {
                    plugin.getServer().getConsoleSender().sendMessage("列表中已有玩家:" + s);
                    printWriter.write(s + "\n");
                }
                printWriter.close();
            }catch (FileNotFoundException ex){ex.printStackTrace();}

        }
    }


}
