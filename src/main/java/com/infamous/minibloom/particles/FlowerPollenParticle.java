package com.infamous.minibloom.particles;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FlowerPollenParticle extends SpriteTexturedParticle {
	private final double portalPosX;
	private final double portalPosY;
	private final double portalPosZ;
	private final IAnimatedSprite spriteWithAge;


	public FlowerPollenParticle(ClientWorld world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, IAnimatedSprite spriteWithAge) {
		super(world, x, y, z, xSpeed, ySpeed, zSpeed);

		this.spriteWithAge = spriteWithAge;
		this.motionX = xSpeed;
		this.motionY = ySpeed;
		this.motionZ = zSpeed;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.portalPosX = this.posX;
		this.portalPosY = this.posY;
		this.portalPosZ = this.posZ;

		float f1 = 1.0F - (float) (Math.random() * (double) 0.3F);

		this.particleRed = f1;
		this.particleGreen = f1;
		this.particleBlue = f1;
		this.particleAlpha = 1f;

		this.particleScale *= 0.6F;

		this.maxAge = (int) (Math.random() * 10.0D) + 40;
		this.selectSpriteWithAge(spriteWithAge);
	}

	public void move(double x, double y, double z) {
		this.setBoundingBox(this.getBoundingBox().offset(x, y, z));
		this.resetPositionToBB();
	}

	public float getScale(float p_217561_1_) {
		float f = ((float) this.age + p_217561_1_) / (float) this.maxAge;
		f = 1.0F - f;
		f = f * f;
		f = 1.0F - f;
		return this.particleScale * f;
	}

	public int getBrightnessForRender(float partialTick) {
		int i = super.getBrightnessForRender(partialTick);
		float f = (float) this.age / (float) this.maxAge;
		f = f * f;
		f = f * f;
		int j = i & 255;
		int k = i >> 16 & 255;
		k = k + (int) (f * 15.0F * 16.0F);
		if (k > 240) {
			k = 240;
		}

		return j | k << 16;
	}

	public void tick() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.age++ >= this.maxAge) {
			this.setExpired();
		} else {
			float f = (float) this.age / (float) this.maxAge;
			float f1 = -f + f * f * 2.0F;
			float f2 = 1.0F - f1;
			this.posX = this.portalPosX + this.motionX * (double) f2;
			this.posY = this.portalPosY + this.motionY * (double) f2 + (double) (1.0F - f);
			this.posZ = this.portalPosZ + this.motionZ * (double) f2;
		}
	}

	@Override
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@OnlyIn(Dist.CLIENT)
	public static class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;

		public Factory(IAnimatedSprite p_i50607_1_) {
			this.spriteSet = p_i50607_1_;
		}

		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			FlowerPollenParticle portalparticle = new FlowerPollenParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet);
			return portalparticle;
		}
	}
}