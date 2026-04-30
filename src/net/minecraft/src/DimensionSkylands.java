package net.minecraft.src;

import sapi.DimensionBase;
import sapi.Loc;

public class DimensionSkylands extends DimensionBase {
    public DimensionSkylands() {
        super(1, WorldProviderSkylands.class, TeleporterSkylands.class);
        this.name = "Skylands";
    }

    public Loc getDistanceScale(Loc loc, boolean flag) {
        double d = flag ? 8.0D : 0.125D;
        return loc.multiply(d, 1.0D, d);
    }
}