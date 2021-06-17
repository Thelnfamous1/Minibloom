package com.infamous.minibloom.client;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.infamous.minibloom.entities.MinibloomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MinibloomModel<T extends MinibloomEntity> extends EntityModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer earLeft;
	private final ModelRenderer earRight;
	private final ModelRenderer body;
	private final ModelRenderer tail;

	private final ModelRenderer standingFrontRightLeg;
	private final ModelRenderer standingFrontLeftLeg;
	private final ModelRenderer standingBackRightLeg;
	private final ModelRenderer standingBackLeftLeg;

	private final ModelRenderer sleepingFrontRightLeg;
	private final ModelRenderer sleepingFrontLeftLeg;
	private final ModelRenderer sleepingBackRightLeg;
	private final ModelRenderer sleepingBackLeftLeg;

	private final ModelRenderer sittingFrontRightLeg;
	private final ModelRenderer sittingFrontLeftLeg;
	private final ModelRenderer sittingBackRightLeg;
	private final ModelRenderer sittingBackLeftLeg;

	private final float originalHeadRotationX;
	private final float originalHeadRotationY;
	private final float originalHeadRotationZ;

	private final float originalBodyRotationX;
	private final float originalBodyRotationY;
	private final float originalBodyRotationZ;

	private final float originalTailRotationX;
	private final float originalTailRotationY;
	private final float originalTailRotationZ;



	public MinibloomModel() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 14.0F, -5.0F);
		head.setTextureOffset(0, 19).addBox(-6.0F, -5.0F, -7.0F, 12.0F, 9.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(32, 0).addBox(-5.0F, -1.0F, -10.0F, 10.0F, 5.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(4, 4).addBox(5.5F, -7.0F, -6.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-6.5F, -7.0F, -6.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(42, 50).addBox(-4.0F, -11.0F, -3.0F, 8.0F, 6.0F, 0.0F, 0.0F, true);
		head.setTextureOffset(42, 42).addBox(0.0F, -11.0F, -7.0F, 0.0F, 6.0F, 8.0F, 0.0F, true);

		originalHeadRotationX = head.rotateAngleX;
		originalHeadRotationY = head.rotateAngleY;
		originalHeadRotationZ = head.rotateAngleZ;

		earLeft = new ModelRenderer(this);
		earLeft.setRotationPoint(-5.9F, -2.0F, -2.0F);
		head.addChild(earLeft);
		setRotationAngle(earLeft, 0.0F, 0.0F, -0.6109F);
		earLeft.setTextureOffset(42, 35).addBox(-4.314F, -1.4585F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, false);

		earRight = new ModelRenderer(this);
		earRight.setRotationPoint(5.6F, -2.0F, -2.0F);
		head.addChild(earRight);
		setRotationAngle(earRight, 0.0F, 0.0F, 0.5672F);
		earRight.setTextureOffset(44, 14).addBox(-0.706F, -1.7359F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 13.4167F, 2.1667F);
		body.setTextureOffset(40, 34).addBox(3.0F, -6.4167F, -5.1667F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(42, 50).addBox(-1.0F, -6.4167F, -1.1667F, 8.0F, 6.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(42, 50).addBox(-7.0F, -6.4167F, 2.8333F, 8.0F, 6.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(40, 34).addBox(-3.0F, -6.4167F, -1.1667F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-5.0F, -0.4167F, -7.1667F, 10.0F, 7.0F, 12.0F, 0.0F, false);

		originalBodyRotationX = body.rotateAngleX;
		originalBodyRotationY = body.rotateAngleY;
		originalBodyRotationZ = body.rotateAngleZ;

		tail = new ModelRenderer(this);
		tail.setRotationPoint(-0.5F, 0.5833F, 2.8333F);
		body.addChild(tail);
		setRotationAngle(tail, -1.2217F, 0.0F, 0.0F);
		tail.setTextureOffset(25, 19).addBox(-1.0F, -1.2817F, -0.5977F, 3.0F, 0.0F, 8.0F, 0.0F, false);

		originalTailRotationX = tail.rotateAngleX;
		originalTailRotationY = tail.rotateAngleY;
		originalTailRotationZ = tail.rotateAngleZ;

		// STANDING LEGS
		standingFrontRightLeg = new ModelRenderer(this);
		standingFrontRightLeg.setRotationPoint(3.0F, 20.0F, -3.0F);
		standingFrontRightLeg.setTextureOffset(16, 43)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		standingFrontLeftLeg = new ModelRenderer(this);
		standingFrontLeftLeg.setRotationPoint(-3.0F, 20.0F, -3.0F);
		standingFrontLeftLeg.setTextureOffset(0, 43)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		standingBackRightLeg = new ModelRenderer(this);
		standingBackRightLeg.setRotationPoint(3.0F, 20.0F, 5.0F);
		standingBackRightLeg.setTextureOffset(42, 27)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		standingBackLeftLeg = new ModelRenderer(this);
		standingBackLeftLeg.setRotationPoint(-3.0F, 20.0F, 5.0F);
		standingBackLeftLeg.setTextureOffset(39, 19)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		// SLEEPING LEGS
		sleepingFrontRightLeg = new ModelRenderer(this);
		sleepingFrontRightLeg.setRotationPoint(3.0F, 20.0F, -3.0F);
		sleepingFrontRightLeg.setTextureOffset(16, 43)
				.addBox(3.0F, -0.3F, 0.2F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		sleepingFrontLeftLeg = new ModelRenderer(this);
		sleepingFrontLeftLeg.setRotationPoint(-3.0F, 20.0F, -3.0F);
		sleepingFrontLeftLeg.setTextureOffset(0, 43)
				.addBox(-3.0F, 0.5F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		sleepingBackRightLeg = new ModelRenderer(this);
		sleepingBackRightLeg.setRotationPoint(3.0F, 20.0F, 5.0F);
		sleepingBackRightLeg.setTextureOffset(42, 27)
				.addBox(-1.6F, -0.3F, 1.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		sleepingBackLeftLeg = new ModelRenderer(this);
		sleepingBackLeftLeg.setRotationPoint(-3.0F, 20.0F, 5.0F);
		sleepingBackLeftLeg.setTextureOffset(39, 19)
				.addBox(-4.5F, 0.5F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		// SITTING LEGS
		sittingFrontRightLeg = new ModelRenderer(this);
		sittingFrontRightLeg.setRotationPoint(3.0F, 20.0F, -3.0F);
		setRotationAngle(sittingFrontRightLeg, -0.2618F, 0.0F, 0.0F);
		sittingFrontRightLeg.setTextureOffset(16, 43)
				.addBox(-1.9F, -0.8F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		sittingFrontLeftLeg = new ModelRenderer(this);
		sittingFrontLeftLeg.setRotationPoint(-3.0F, 20.0F, -3.0F);
		setRotationAngle(sittingFrontLeftLeg, -0.2618F, 0.0F, 0.0F);
		sittingFrontLeftLeg.setTextureOffset(0, 43)
				.addBox(-2.1F, -0.8F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		sittingBackRightLeg = new ModelRenderer(this);
		sittingBackRightLeg.setRotationPoint(3.0F, 20.0F, 5.0F);
		setRotationAngle(sittingBackRightLeg, 0.0F, 0.0F, -1.5708F);
		sittingBackRightLeg.setTextureOffset(42, 27)
				.addBox(-4.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		sittingBackLeftLeg = new ModelRenderer(this);
		sittingBackLeftLeg.setRotationPoint(-3.0F, 28.0F, 5.0F);
		setRotationAngle(sittingBackLeftLeg, 0.0F, 0.0F, 1.5708F);
		sittingBackLeftLeg.setTextureOffset(39, 19)
				.addBox(-8.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		this.standingBackRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.standingBackLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.standingFrontRightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.standingFrontLeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
		resetRotationPoints();
		resetRotationAngles();
		showStandingLegs();

		if(entityIn.isSleeping()){
			showSleepingLegs();
			head.setRotationPoint(3.0F, 19.2F, -4.0F);
			body.setRotationPoint(0.0F, 17.4167F, 2.1667F);

			setRotationAngle(head, 0.0873F, -0.0873F, 0.0F);
			setRotationAngle(body, 0.0F, -0.5236F, 0.0F);
			setRotationAngle(tail, -1.2217F, 0.0F, 0.0F);

		} else if(entityIn.isSitting()){
			showSittingLegs();
			head.setRotationPoint(0.0F, 14.0F, -1.0F);
			body.setRotationPoint(0.0F, 17.4167F, 4.1667F);

			setRotationAngle(body, -0.6545F, 0.0F, 0.0F);
			setRotationAngle(tail, 0.5672F, 0.0F, 0.0F);
		}
	}

	private void showStandingLegs() {
		this.standingFrontRightLeg.showModel = true;
		this.standingFrontLeftLeg.showModel = true;
		this.standingBackRightLeg.showModel = true;
		this.standingBackLeftLeg.showModel = true;

		this.sleepingFrontRightLeg.showModel = false;
		this.sleepingFrontLeftLeg.showModel = false;
		this.sleepingBackRightLeg.showModel = false;
		this.sleepingBackLeftLeg.showModel = false;

		this.sittingFrontRightLeg.showModel = false;
		this.sittingFrontLeftLeg.showModel = false;
		this.sittingBackRightLeg.showModel = false;
		this.sittingBackLeftLeg.showModel = false;
	}

	private void showSleepingLegs() {
		this.standingFrontRightLeg.showModel = false;
		this.standingFrontLeftLeg.showModel = false;
		this.standingBackRightLeg.showModel = false;
		this.standingBackLeftLeg.showModel = false;

		this.sleepingFrontRightLeg.showModel = true;
		this.sleepingFrontLeftLeg.showModel = true;
		this.sleepingBackRightLeg.showModel = true;
		this.sleepingBackLeftLeg.showModel = true;

		this.sittingFrontRightLeg.showModel = false;
		this.sittingFrontLeftLeg.showModel = false;
		this.sittingBackRightLeg.showModel = false;
		this.sittingBackLeftLeg.showModel = false;
	}

	private void showSittingLegs() {
		this.standingFrontRightLeg.showModel = false;
		this.standingFrontLeftLeg.showModel = false;
		this.standingBackRightLeg.showModel = false;
		this.standingBackLeftLeg.showModel = false;

		this.sleepingFrontRightLeg.showModel = false;
		this.sleepingFrontLeftLeg.showModel = false;
		this.sleepingBackRightLeg.showModel = false;
		this.sleepingBackLeftLeg.showModel = false;

		this.sittingFrontRightLeg.showModel = true;
		this.sittingFrontLeftLeg.showModel = true;
		this.sittingBackRightLeg.showModel = true;
		this.sittingBackLeftLeg.showModel = true;
	}

	private void resetRotationPoints() {
		head.setRotationPoint(0.0F, 14.0F, -5.0F);
		body.setRotationPoint(0.0F, 13.4167F, 2.1667F);
		tail.setRotationPoint(-0.5F, 0.5833F, 2.8333F);
	}

	private void resetRotationAngles() {
		setRotationAngle(head, originalHeadRotationX, originalHeadRotationY, originalHeadRotationZ);
		setRotationAngle(body, originalBodyRotationX, originalBodyRotationY, originalBodyRotationZ);
		setRotationAngle(tail, originalTailRotationX, originalTailRotationY, originalTailRotationZ);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);

		standingFrontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		standingFrontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		standingBackRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		standingBackLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);

		sleepingFrontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		sleepingFrontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		sleepingBackRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		sleepingBackLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);

		sittingFrontRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		sittingFrontLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		sittingBackRightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		sittingBackLeftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}