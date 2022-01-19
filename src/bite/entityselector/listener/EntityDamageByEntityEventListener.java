package bite.entityselector.listener;

import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import bite.entityselector.App;
import bite.entityselector.manager.EntitySelectorManager;

public class EntityDamageByEntityEventListener implements Listener
{
	private EntitySelectorManager entitySelectorManager;
	
	public EntityDamageByEntityEventListener()
	{
		this.entitySelectorManager = App.getInstance().getEntitySelectorManager();
	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event)
	{
		Entity entity = event.getEntity();
		Entity damager = event.getDamager();
		Player player = null;
		
		if (damager == null)
		{
			return;
		}
		
		if (damager instanceof Arrow)
		{
			Arrow arrow = (Arrow)damager;
			
			ProjectileSource source = arrow.getShooter();
			
			if (source instanceof Player)
			{
				player = (Player)source;
			}
			else
			{
				return;
			}
		}
		else if (damager instanceof SpectralArrow)
		{
			SpectralArrow arrow = (SpectralArrow)damager;
			
			ProjectileSource source = arrow.getShooter();
			
			if (source instanceof Player)
			{
				player = (Player)source;
			}
			else
			{
				return;
			}
		}
		else if (damager instanceof Player)
		{
			player = (Player)damager;
		}
		
		if (entity != null && entity instanceof LivingEntity)
		{		
			if (!this.entitySelectorManager.isAlreadySelectedEntity((LivingEntity)entity, player))
			{	
				BossBar entityBossBar = this.entitySelectorManager.getEntityBossBar((LivingEntity)entity);
				
				if (entityBossBar == null)
				{
					entityBossBar = this.entitySelectorManager.createEntityBossBar((LivingEntity)entity);
				}
				
				this.entitySelectorManager.upsertPlayerSelectedBossBar(player, entityBossBar);
			}
		
			Double healthUpdate = (((LivingEntity)entity).getHealth() - event.getFinalDamage()) / ((LivingEntity)entity).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			
			if (healthUpdate > 0.0)
			{
				this.entitySelectorManager.updateEntityHealth((LivingEntity)entity, healthUpdate);
			}
		}
	}
}
