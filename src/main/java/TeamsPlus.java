import Commands.Aggregate;
import Commands.Pm;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Elliot on 28/06/2014.
 */
public final class TeamsPlus extends JavaPlugin {

    Plugin plugin;

    @Override
    public void onEnable()
    {
       plugin = this;

        getCommand("aggregate").setExecutor(new Aggregate(plugin));
        getCommand("pm").setExecutor(new Pm(plugin));

    }
}
