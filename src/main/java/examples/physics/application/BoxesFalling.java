package examples.physics.application;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.etyl.physics.PhysicsApplication;
import com.harium.etyl.physics.RigidBody;
import org.dyn4j.geometry.MassType;

import java.util.ArrayList;
import java.util.List;

public class BoxesFalling extends PhysicsApplication {

    public BoxesFalling(int w, int h) {
        super(w, h);
    }

    private RigidBody floor;

    private List<RigidBody> crates;

    @Override
    public void load() {

        floor = new RigidBody(new Layer(60, 500, w - 60, 20));
        floor.setMass(MassType.INFINITE);

        this.world.addBody(floor);

        crates = new ArrayList<RigidBody>();
        loading = 10;

        for (int i = 0; i < 6; i++) {
            RigidBody crate = new RigidBody(new ImageLayer(180 + 50 * i, 10 + 70 * i, "crate.png"));

            world.addBody(crate);
            crates.add(crate);
        }

        loading = 100;
    }

    @Override
    public void update(long now) {
        updateWorld(now);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(floor.getLayer());

        for (RigidBody crate : crates) {
            crate.draw(g);
        }
    }

    private void updateWorld(long time) {
        // update the world with the elapsed time
        world.update(time);
    }

}
