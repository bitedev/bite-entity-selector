package bite.entityselector;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import bite.entityselector.listener.EntityDamageByEntityEventListener;
import bite.entityselector.listener.EntityDeathEventListener;
import bite.entityselector.listener.PlayerInteractListener;

public class Main extends JavaPlugin 
{
	@Override
	public void onEnable() 
	{
		PluginManager pluginManager = getServer().getPluginManager();
		
		App.getInstance().init();
		
		pluginManager.registerEvents(new PlayerInteractListener(), this);
		pluginManager.registerEvents(new EntityDamageByEntityEventListener(), this);
		pluginManager.registerEvents(new EntityDeathEventListener(), this);
	}
	
	@Override
	public void onDisable()
	{
		
	}
}
