package bite.entityselector;

import bite.entityselector.manager.EntitySelectorManager;

public class App 
{
	private static App instance = null;
	
	private EntitySelectorManager entitySelectorManager;
	
	private App()
	{
		this.entitySelectorManager = new EntitySelectorManager();
	}
	
	public static App getInstance()
	{
		if (instance == null)
		{
			instance = new App();
		}
		
		return instance;
	}
	
	public void init()
	{
		
	}

	public EntitySelectorManager getEntitySelectorManager()
	{
		return this.entitySelectorManager;
	}
}
