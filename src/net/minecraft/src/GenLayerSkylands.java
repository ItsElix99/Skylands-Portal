package net.minecraft.src;

public abstract class GenLayerSkylands extends GenLayer {

    public GenLayerSkylands(long var1) {
        super(var1);
    }

    public static GenLayer[] func_35497_a(long var0) {
        LayerIslandSkylands var2 = new LayerIslandSkylands(1L);
        GenLayerZoomFuzzy var9 = new GenLayerZoomFuzzy(2000L, var2);
        GenLayerIsland var10 = new GenLayerIsland(1L, var9);
        GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
        var10 = new GenLayerIsland(2L, var11);
        var11 = new GenLayerZoom(2002L, var10);
        var10 = new GenLayerIsland(3L, var11);
        var11 = new GenLayerZoom(2003L, var10);
        var10 = new GenLayerIsland(3L, var11);
        var11 = new GenLayerZoom(2004L, var10);
        var10 = new GenLayerIsland(3L, var11);
        byte var3 = 4;
        GenLayer var4 = GenLayerZoom.func_35515_a(1000L, var10, 0);
        var4 = GenLayerZoom.func_35515_a(1000L, var4, var3 + 2);
        GenLayerSmooth var14 = new GenLayerSmooth(1000L, var4);
        GenLayer var5 = GenLayerZoom.func_35515_a(1000L, var10, 0);
        GenLayerVillageLandscape var15 = new GenLayerVillageLandscape(200L, var5);
        Object var16 = GenLayerZoom.func_35515_a(1000L, var15, 2);
        Object var6 = new GenLayerTemperature((GenLayer) var16);
        Object var7 = new GenLayerDownfall((GenLayer) var16);

        for (int var8 = 0; var8 < var3; ++var8) {
            var16 = new GenLayerZoom((long) (1000 + var8), (GenLayer) var16);
            if (var8 == 0) {
                var16 = new GenLayerIsland(3L, (GenLayer) var16);
            }

            GenLayerSmoothZoom var17 = new GenLayerSmoothZoom((long) (1000 + var8), (GenLayer) var6);
            var6 = new GenLayerTemperatureMix(var17, (GenLayer) var16, var8);
            GenLayerSmoothZoom var21 = new GenLayerSmoothZoom((long) (1000 + var8), (GenLayer) var7);
            var7 = new GenLayerDownfallMix(var21, (GenLayer) var16, var8);
        }

        GenLayerSmooth var18 = new GenLayerSmooth(1000L, (GenLayer) var16);
        GenLayer var19 = GenLayerSmoothZoom.func_35517_a(1000L, (GenLayer) var6, 2);
        GenLayer var22 = GenLayerSmoothZoom.func_35517_a(1000L, (GenLayer) var7, 2);
        GenLayerZoomVoronoi var23 = new GenLayerZoomVoronoi(10L, var18);
        var14.func_35496_b(var0);
        var19.func_35496_b(var0);
        var22.func_35496_b(var0);
        var23.func_35496_b(var0);
        return new GenLayer[]{var14, var23, var19, var22};
    }
}