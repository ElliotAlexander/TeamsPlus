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
public class Pm implements CommandExecutor {

    Plugin plugin;

    ScoreboardManager manager = plugin.getServer().getScoreboardManager();
    Scoreboard board = plugin.getServer().getScoreboardManager().getMainScoreboard();

    public Pm(Plugin instance)
    {
        instance = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pm")) {
            Player p = (Player) sender;
            Team team = board.getPlayerTeam(p);

            if (null == team) {
                p.sendMessage(ChatColor.RED + "You are not on a team");
                return true;
            }

            String prefix = board.getPlayerTeam(p).getPrefix();

            StringBuilder sb = new StringBuilder();

            sb.append("§6[§l§fPM§r§6]§f ").append(prefix).append(p.getName()).append(":§f ").append(ChatColor.ITALIC);
            for (String arg : args) {
                sb.append(" ").append(arg);
            }

            String message = sb.toString();

            for (OfflinePlayer offlineplayer : this.board.getPlayerTeam(p).getPlayers()) {
                if (offlineplayer.isOnline()) {
                    Player player = (Player) offlineplayer;
                    player.sendMessage(message);
                }
            }
            return true;
        }
        return false;
    }
}
