package mindustry.client.navigation.waypoints;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import mindustry.client.navigation.waypoints.Waypoint;

import static mindustry.Vars.*;

public class PositionWaypoint extends Waypoint implements Position {
    private final float drawX, drawY;
    public float tolerance = 16f;

    public PositionWaypoint(float drawX, float drawY) {
        this.drawX = drawX;
        this.drawY = drawY;
    }

    public PositionWaypoint(float drawX, float drawY, float tolerance) {
        this(drawX, drawY);
        this.tolerance = tolerance;
    }

    @Override
    public boolean isDone() {
        return player.within(this, tolerance);
    }

    @Override
    public void run() {
        float direction = player.angleTo(this);
        float x = Mathf.cosDeg(direction) * 2f;
        float y = Mathf.sinDeg(direction) * 2f;
        x = Mathf.clamp(x / 10, -1f, 1f);
        y = Mathf.clamp(y / 10, -1f, 1f);
        if (player.dst(this) > tolerance) {
            control.input.updateMovementCustom(player.unit(), x, y, direction);
        }
    }

    @Override
    public float getX() {
        return drawX;
    }

    @Override
    public float getY() {
        return drawY;
    }

    @Override
    public void draw() {
        Draw.color(Color.green);
        Draw.alpha(0.3f);
        Fill.circle(getX(), getY(), tolerance);
        Draw.color();
    }
}
