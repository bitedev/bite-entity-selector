package bite.entityselector.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import bite.entityselector.App;
import bite.entityselector.manager.EntitySelectorManager;

public class EntityDeathEventListener implements Listener 
{
private EntitySelectorManager entitySelectorManager;
	
	public EntityDeathEventListener()
	{
		this.entitySelectorManager = App.getInstance().getEntitySelectorManager();
	}
	
	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent event)
	{
		Entity entity = event.getEntity();
		
		if (entity != null && entity instanceof LivingEntity) 
		{
			this.entitySelectorManager.setDeadEntity((LivingEntity)entity);
		}
	}
}
