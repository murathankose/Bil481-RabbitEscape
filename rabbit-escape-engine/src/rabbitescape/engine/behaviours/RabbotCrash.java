package rabbitescape.engine.behaviours;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourTools;
import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;

public class RabbotCrash extends Behaviour
{
    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        if ( rabbit.type == Rabbit.Type.RABBOT )
        {
            for ( Rabbit otherRabbit : world.rabbits )
            {
                if ( otherRabbit.type == Rabbit.Type.RABBIT &&
                    otherRabbit.x == rabbit.x &&
                    otherRabbit.y == rabbit.y
                )
                {
                    world.changes.killRabbit( otherRabbit );
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            return State.RABBIT_CRASHING;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state == State.RABBIT_CRASHING )
        {
		Block block = world.getBlockAt( rabbit.x+1, rabbit.y );
		if(block!=null){
		world.changes.removeBlockAt( rabbit.x+1, rabbit.y);	
		}
		block= world.getBlockAt( rabbit.x-1, rabbit.y );
		if(block!=null){
		world.changes.removeBlockAt( rabbit.x-1, rabbit.y);
		}
		block= world.getBlockAt( rabbit.x, rabbit.y+1 );
		if(block!=null){
		world.changes.removeBlockAt( rabbit.x, rabbit.y+1);
		}
		block= world.getBlockAt( rabbit.x, rabbit.y-1 );
		if(block!=null){
		world.changes.removeBlockAt( rabbit.x, rabbit.y-1);
		}
            world.changes.killRabbit( rabbit );
            return true;
        }

        return false;
    }
}
