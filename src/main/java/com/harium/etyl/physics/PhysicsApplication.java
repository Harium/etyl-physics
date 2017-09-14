package com.harium.etyl.physics;

import com.harium.etyl.commons.context.Application;
import org.dyn4j.dynamics.World;

public abstract class PhysicsApplication extends Application {

    /**
     * The Physic World
     */
    protected World world = new World();

    public PhysicsApplication(int w, int h) {
        super(w, h);

        world.getSettings().setStepFrequency(1 / 6d);
        world.setGravity(World.EARTH_GRAVITY.negate());
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

}
