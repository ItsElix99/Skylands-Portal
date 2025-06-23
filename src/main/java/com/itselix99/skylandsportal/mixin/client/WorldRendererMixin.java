package com.itselix99.skylandsportal.mixin.client;

import com.itselix99.skylandsportal.particle.SkylandsPortalParticle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.world.World;
import net.minecraft.world.event.listener.GameEventListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin implements GameEventListener {
    @Shadow private World world;
    @Shadow private Minecraft client;

    @Inject(method = "addParticle", at = @At("TAIL"))
    private void addSkylandsPortalParticle(String particle, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfo ci) {
        if (this.client != null && this.client.camera != null && this.client.particleManager != null) {
            double var14 = this.client.camera.x - x;
            double var16 = this.client.camera.y - y;
            double var18 = this.client.camera.z - z;
            double var20 = (double)16.0F;
            if (!(var14 * var14 + var16 * var16 + var18 * var18 > var20 * var20)) {
                if (particle.equals("skylands_portal")) {
                    this.client.particleManager.addParticle(new SkylandsPortalParticle(this.world, x, y, z, velocityX, velocityY, velocityZ));
                }
            }
        }
    }
}
