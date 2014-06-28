package Commands;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * Created by Elliot on 28/06/2014.
 */
public class Aggregate implements CommandExecutor {

    Plugin plugin;
    ScoreboardManager manager = plugin.getServer().getScoreboardManager();
    Scoreboard board = plugin.getServer().getScoreboardManager().getMainScoreboard();

    public Aggregate(Plugin instance)
    {
        plugin = instance;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args )
    {
            for(Team t : board.getTeams()){

                if(t.getSize()>1) {
                    Player p = getRandomElement(t);
                    for (OfflinePlayer p2 : t.getPlayers()) {
                        if(p2.isOnline()||p2!=p) {
                            p2.getPlayer().teleport(p.getLocation());
                            p2.getPlayer().sendMessage(ChatColor.RED + "You have been teleported to your team.");
                        }
                    }
                }

            }
            return true;
    }

    private Player getRandomElement(Team t)
    {
        int size = t.getSize();
        int player = (int)(Math.random() * size + 1);
        int x = 1;
        for(OfflinePlayer p : t.getPlayers())
        {
            if(player==x && plugin.getServer().getPlayer(p.getName())!=null)
            {
                return p.getPlayer();
            }
            x++;
        }
        Player p2 = null;
        return p2;
    }
}
