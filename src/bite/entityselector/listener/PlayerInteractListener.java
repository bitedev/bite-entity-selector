package bite.entityselector.listener;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;

import bite.entityselector.App;
import bite.entityselector.manager.EntitySelectorManager;

public class PlayerInteractListener implements Listener
{
	private EntitySelectorManager entitySelectorManager;
	
	public PlayerInteractListener()
	{
		this.entitySelectorManager = App.getInstance().getEntitySelectorManager();
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		
		if (event.getAction() != Action.LEFT_CLICK_AIR)
		{
			return;
		}
		
		RayTraceResult rayResult = player.getWorld().rayTraceEntities(player.getEyeLocation(), player.getEyeLocation().getDirection(), 40, (e) -> e.getName() != player.getName());
		
		if (rayResult == null)
		{
			this.entitySelectorManager.clearPlayerBossBar(player);
			
			return;
		}
		
		Entity entity = rayResult.getHitEntity();
		
		if (entity == null || !(entity instanceof LivingEntity))
		{
			return;
		}
		
		if (this.entitySelectorManager.isAlreadySelectedEntity((LivingEntity)entity, player))
		{
			return;
		}
		
		BossBar entityBossBar = this.entitySelectorManager.getEntityBossBar((LivingEntity)entity);
		
		if (entityBossBar == null)
		{
			entityBossBar = this.entitySelectorManager.createEntityBossBar((LivingEntity)entity);
		}
		
		this.entitySelectorManager.upsertPlayerSelectedBossBar(player, entityBossBar);
	}
	
	
}