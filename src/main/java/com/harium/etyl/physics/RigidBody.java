package com.harium.etyl.physics;

import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.graphics.Graphics;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.MassType;

/**
 * Layer wrapper that handles physics
 *
 * @author yuripourre
 */

public class RigidBody extends Body {

    int x = 0, y = 0;
    private Layer layer;

    public RigidBody(Layer layer) {
        this(layer, true);
    }

    public RigidBody(Layer layer, boolean initFixture) {
        super();
        this.layer = layer;
        this.x = layer.getX();
        this.y = layer.getY();

        if (initFixture) {
            addFixture(new BodyFixture(new org.dyn4j.geometry.Rectangle(layer.getW() * layer.getScaleX(), layer.getH() * layer.getScaleY())));
            translate(layer.getX() + (layer.getW() * layer.getScaleX()) / 2, layer.getY() + (layer.getH() * layer.getScaleY()) / 2);
        }

        setMass(MassType.NORMAL);
    }

    public void draw(Graphics g) {
        layer.setX((int) (this.transform.getTranslationX() - layer.getOriginY()));
        layer.setY((int) (this.transform.getTranslationY() - layer.getOriginY()));
        layer.setAngle(Math.toDegrees(this.transform.getRotation()));

        layer.draw(g);
    }

    public Layer getLayer() {
        return layer;
    }

}
