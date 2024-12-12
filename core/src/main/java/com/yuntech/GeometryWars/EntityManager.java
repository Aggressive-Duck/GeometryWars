package com.yuntech.GeometryWars;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EntityManager {
    private static List<Entity> entities = new ArrayList<>();
    private static List<Bullet> bullets = new ArrayList<>();
    private static boolean isUpdating;
    private static List<Entity> addedEntities = new ArrayList<>();

    public static int getCount() {
        return entities.size();
    }

    public static void add(Entity entity) {
        if (!isUpdating) {
            addEntity(entity);
        } else {
            addedEntities.add(entity);
        }
    }

    private static void addEntity(Entity entity) {
        entities.add(entity);
        if (entity instanceof Bullet) {
            bullets.add((Bullet) entity);
        }
    }

    public static void update(float delta) {
        isUpdating = true;

        for (Entity entity : entities) {
            entity.update(delta);
        }

        isUpdating = false;

        for (Entity entity : addedEntities) {
            addEntity(entity);
        }

        addedEntities.clear();

        Iterator<Entity> iterEntity = entities.iterator();
        while (iterEntity.hasNext()) {
            Entity entity = iterEntity.next();
            if (entity.isExpired) {
                iterEntity.remove();
            }
        }

        Iterator<Bullet> iterBullet = bullets.iterator();
        while (iterBullet.hasNext()) {
            Bullet bullet = iterBullet.next();
            if (bullet.isExpired) {
                iterBullet.remove();
            }
        }
    }

    public static void draw(SpriteBatch spriteBatch) {
        for (Entity entity : entities) {
            entity.draw(spriteBatch);
        }
    }
}

