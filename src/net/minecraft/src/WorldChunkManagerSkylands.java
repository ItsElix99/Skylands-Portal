package net.minecraft.src;

import java.lang.reflect.Field;

public class WorldChunkManagerSkylands extends WorldChunkManager {

    public WorldChunkManagerSkylands(World var1) {
        super(var1);
        GenLayer[] var2 = GenLayerSkylands.func_35497_a(var1.getRandomSeed());
        try {
            Field field_34903_b = WorldChunkManager.class.getDeclaredField("b");
            field_34903_b.setAccessible(true);
            field_34903_b.set(this, var2[0]);

            Field field_34902_c = WorldChunkManager.class.getDeclaredField("c");
            field_34902_c.setAccessible(true);
            field_34902_c.set(this, var2[1]);

            Field field_34901_d = WorldChunkManager.class.getDeclaredField("d");
            field_34901_d.setAccessible(true);
            field_34901_d.set(this, var2[2]);

            Field field_35565_e = WorldChunkManager.class.getDeclaredField("e");
            field_35565_e.setAccessible(true);
            field_35565_e.set(this, var2[3]);
        } catch (Exception e) {
            try {
                Field field_34903_b = WorldChunkManager.class.getDeclaredField("field_34903_b");
                field_34903_b.setAccessible(true);
                field_34903_b.set(this, var2[0]);

                Field field_34902_c = WorldChunkManager.class.getDeclaredField("field_34902_c");
                field_34902_c.setAccessible(true);
                field_34902_c.set(this, var2[1]);

                Field field_34901_d = WorldChunkManager.class.getDeclaredField("field_34901_d");
                field_34901_d.setAccessible(true);
                field_34901_d.set(this, var2[2]);

                Field field_35565_e = WorldChunkManager.class.getDeclaredField("field_35565_e");
                field_35565_e.setAccessible(true);
                field_35565_e.set(this, var2[3]);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}