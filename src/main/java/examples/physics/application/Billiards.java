package examples.physics.application;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.etyl.physics.PhysicsApplication;
import com.harium.etyl.physics.RigidBody;
import org.dyn4j.dynamics.World;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;

/*
 * Copyright (c) 2010-2016 William Bittle  http://www.dyn4j.org/
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 *   * Redistributions of source code must retain the above copyright notice, this list of conditions
 *     and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 *     and the following disclaimer in the documentation and/or other materials provided with the
 *     distribution.
 *   * Neither the name of dyn4j nor the names of its contributors may be used to endorse or
 *     promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class Billiards extends PhysicsApplication {

    public Billiards(int w, int h) {
        super(w, h);
    }

    private RigidBody wallr;
    private RigidBody ball1;
    private RigidBody ball2;

    @Override
    public void load() {
        // Setup world
        // no gravity on a top-down view of a billiards game
        this.world.setGravity(World.ZERO_GRAVITY);

        wallr = new RigidBody(new Layer(600, 0, 1, 500));
        wallr.setMass(MassType.INFINITE);
        world.addBody(wallr);

        ImageLayer blueBall = new ImageLayer("ball_blue.png");
        ball1 = new RigidBody(blueBall, false);
        ball1.addFixture(Geometry.createCircle(32), // 0.032 m radius
                217.97925,				  //  0.126 oz/in^3 = 217.97925 kg/m^3
                0.08,
                0.9);
        ball1.translate(100, 190);
        ball1.setLinearVelocity(30, 1); // 30m/s
        ball1.setMass(MassType.NORMAL);
        this.world.addBody(ball1);

        ImageLayer redBall = new ImageLayer("ball_red.png", false);
        ball2 = new RigidBody(redBall);
        ball2.removeAllFixtures();
        ball2.addFixture(Geometry.createCircle(32), 217.97925, 0.08, 0.9);
        ball2.translate(180, 200);
        ball2.setMass(MassType.NORMAL);
        this.world.addBody(ball2);

        loading = 100;
    }

    @Override
    public void update(long now) {
        updateWorld(now);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(wallr.getLayer());

        ball1.draw(g);
        ball2.draw(g);
    }

    private void updateWorld(long time) {
        // update the world with the elapsed time
        world.update(time);
    }
}
