package bite.entityselector.manager;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class EntitySelectorManager
{
	Map<String, BossBar> entityBossBar;
	Map<String, BossBar> playerSelectedBossBar;
	
	public EntitySelectorManager()
	{
		this.entityBossBar = new HashMap<String, BossBar>();
		this.playerSelectedBossBar = new HashMap<String, BossBar>();
	}
	
	public void clearPlayerBossBar(Player player)
	{
		String playerName = player.getName();
		
		if (this.playerSelectedBossBar.containsKey(playerName))
		{
			this.playerSelectedBossBar.get(playerName).removePlayer(player);
			this.playerSelectedBossBar.remove(playerName);
		}
	}
	
	public Boolean isAlreadySelectedEntity(LivingEntity entity, Player player)
	{
		Boolean result = false;
		BossBar bossBar = null;
		
		if (this.entityBossBar.containsKey(entity.getUniqueId().toString()))
		{
			bossBar = this.entityBossBar.get(entity.getUniqueId().toString());
			
			if (bossBar != null && bossBar.getPlayers() != null && bossBar.getPlayers().contains(player))
			{
				result = true;
			}
		}
		
		if (!(entity instanceof Player))
		{
			String customName = entity.getCustomName();
			
			if (customName != null && bossBar != null)
			{
				String fixedBossBarTitle = this.entityTypeToName(entity.getType()) + "    " + customName;
				
				if (bossBar.getTitle() != fixedBossBarTitle)
				{
					bossBar.setTitle(fixedBossBarTitle);
				}
			}
		}
		
		return result;
	}
	
	public BossBar getEntityBossBar(LivingEntity entity)
	{
		if (this.entityBossBar.containsKey(entity.getUniqueId().toString())) 
		{
			return this.entityBossBar.get(entity.getUniqueId().toString());
		}
		
		return null;
	}
	
	public BossBar createEntityBossBar(LivingEntity entity)
	{
		String printableName = entity.getCustomName() != null ? this.entityTypeToName(entity.getType()) + "    " + entity.getCustomName() : this.entityTypeToName(entity.getType());
		
		if (entity instanceof Player)
		{
			printableName = entity.getName();
		}
		
		Double entityLife = entity.getHealth() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(); 
		BossBar selectedEntity = Bukkit.createBossBar(printableName, BarColor.GREEN, BarStyle.SEGMENTED_20);
		selectedEntity.setProgress(entityLife);
		
		this.entityBossBar.put(entity.getUniqueId().toString(), selectedEntity);
		
		return selectedEntity;
	}
	
	public void upsertPlayerSelectedBossBar(Player player, BossBar bossBar)
	{
		if (this.playerSelectedBossBar.containsKey(player.getName()))
		{
			this.playerSelectedBossBar.get(player.getName()).removePlayer(player);
			this.playerSelectedBossBar.replace(player.getName(), bossBar);
		}
		else
		{
			this.playerSelectedBossBar.put(player.getName(), bossBar);
		}
		
		bossBar.addPlayer(player);
	}
	
	public void updateEntityHealth(LivingEntity entity, Double healthUpdate)
	{
		if (this.entityBossBar.containsKey(entity.getUniqueId().toString()))
		{
			BossBar bossBar =  this.entityBossBar.get(entity.getUniqueId().toString());
			bossBar.setProgress(healthUpdate);
		}
		else
		{
			this.createEntityBossBar(entity);
		}
	}
	
	public void setDeadEntity(LivingEntity entity)
	{
		String entityId = entity.getUniqueId().toString();
		
		if (this.entityBossBar.containsKey(entityId))
		{
			this.entityBossBar.get(entityId).removeAll();
			this.entityBossBar.remove(entityId);
		}
	}
	
	private String entityTypeToName(EntityType entity)
	{
		switch (entity)
		{
		case AXOLOTL:
			return "Axolotl";
			
		case BAT:
			return "Bat";
			
		case BEE:
			return "Bee";
			
		case BLAZE:
			return "Blaze";

		case CAT:
			return "Cat";

		case CAVE_SPIDER:
			return "Cave Spider";
			
		case CHICKEN:
			return "Chicken";
			
		case COD:
			return "Cod";
			
		case COW:
			return "Cow";
			
		case CREEPER:
			return "Creeper";
			
		case DOLPHIN:
			return "Dolphin";
			
		case DONKEY:
			return "Donkey";
			
		case DROWNED:
			return "Drowned";
			
		case ELDER_GUARDIAN:
			return "Elder Guardian";

		case ENDERMAN:
			return "Enderman";
			
		case ENDERMITE:
			return "Endermite";
			
		case ENDER_DRAGON:
			return "Ender Dragon";
			
		case EVOKER:
			return "Evoker";

		case FOX:
			return "Fox";

		case GHAST:
			return "Ghast";
			
		case GLOW_SQUID:
			return "Glow squid";
			
		case GOAT:
			return "Goat";
			
		case GUARDIAN:
			return "Guardian";
			
		case HOGLIN:
			return "Hoglin";
			
		case HORSE:
			return "Horse";
			
		case HUSK:
			return "Husk";
			
		case ILLUSIONER:
			return "Illusioner";
			
		case IRON_GOLEM:
			return "Iron Golem";
			
		case LLAMA:
			return "Llama";
			
		case MAGMA_CUBE:
			return "Magma Cube";
			
		case MULE:
			return "Mule";
			
		case MUSHROOM_COW:
			return "Mushroom Cow";
			
		case OCELOT:
			return "Ocelot";
			
		case PANDA:
			return "Panda";
			
		case PARROT:
			return "Parrot";
			
		case PHANTOM:
			return "Phantom";
			
		case PIG:
			return "Pig";
			
		case PIGLIN:
			return "Piglin";
			
		case PIGLIN_BRUTE:
			return "Piglin Brute";
			
		case PILLAGER:
			return "Pillager";
			
		case POLAR_BEAR:
			return "Polar Bear";
			
		case PUFFERFISH:
			return "Pufferfish";
			
		case RABBIT:
			return "Rabbit";
			
		case RAVAGER:
			return "Ravager";
			
		case SALMON:
			return "Salmon";
			
		case SHEEP:
			return "Sheep";
			
		case SHULKER:
			return "Shulker";
			
		case SILVERFISH:
			return "Silverfish";
			
		case SKELETON:
			return "Skeleton";
			
		case SKELETON_HORSE:
			return "Skeleton Horse";
			
		case SLIME:
			return "Slime";
			
		case SNOWMAN:
			return "Snowman";
			
		case SPIDER:
			return "Spider";
			
		case SQUID:
			return "Squid";
			
		case STRAY:
			return "Stray";
			
		case STRIDER:
			return "Strider";
			
		case TRADER_LLAMA:
			return "Trader Llama";
			
		case TROPICAL_FISH:
			return "Tropical Fish";
			
		case TURTLE:
			return "Turtle";
			
		case VEX:
			return "Vex";
			
		case VILLAGER:
			return "Villager";
			
		case VINDICATOR:
			return "Vindicator";
			
		case WANDERING_TRADER:
			return "Wandering Trader";
			
		case WITCH:
			return "Witch";
			
		case WITHER:
			return "Wither";
			
		case WITHER_SKELETON:
			return "Wither Skeleton";
			
		case WOLF:
			return "Wolf";
			
		case ZOGLIN:
			return "Zoglin";
			
		case ZOMBIE:
			return "Zombie";
			
		case ZOMBIE_HORSE:
			return "Zombie Horse";
			
		case ZOMBIE_VILLAGER:
			return "Zombie Villager";
			
		case ZOMBIFIED_PIGLIN:
			return "Zombified Piglin";
			
		default:
			return "Unknown";
		}
	}
}
