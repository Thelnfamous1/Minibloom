package com.infamous.minibloom.client;// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.infamous.minibloom.entities.MinibloomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MinibloomSleep<T extends MinibloomEntity> extends EntityModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer ear_left;
	private final ModelRenderer ear_right;
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer front_right_leg;
	private final ModelRenderer front_left_leg;
	private final ModelRenderer back_right_leg;
	private final ModelRenderer back_left_leg;

	public MinibloomSleep() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(3.0F, 19.2F, -4.0F);
		setRotationAngle(head, 0.0873F, -0.0873F, 0.0F);
		head.setTextureOffset(0, 19).addBox(-6.0F, -5.0F, -7.0F, 12.0F, 9.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(32, 0).addBox(-5.0F, -1.0F, -10.0F, 10.0F, 5.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(4, 4).addBox(5.5F, -7.0F, -6.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-6.5F, -7.0F, -6.0F, 1.0F, 3.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(42, 50).addBox(-4.0F, -11.0F, -3.0F, 8.0F, 6.0F, 0.0F, 0.0F, true);
		head.setTextureOffset(42, 42).addBox(0.0F, -11.0F, -7.0F, 0.0F, 6.0F, 8.0F, 0.0F, true);

		ear_left = new ModelRenderer(this);
		ear_left.setRotationPoint(-5.9F, -2.0F, -2.0F);
		head.addChild(ear_left);
		setRotationAngle(ear_left, 0.0F, 0.0F, -0.6109F);
		ear_left.setTextureOffset(42, 35).addBox(-4.314F, -1.4585F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, false);

		ear_right = new ModelRenderer(this);
		ear_right.setRotationPoint(5.6F, -2.0F, -2.0F);
		head.addChild(ear_right);
		setRotationAngle(ear_right, 0.0F, 0.0F, 0.5672F);
		ear_right.setTextureOffset(44, 14).addBox(-0.706F, -1.7359F, 0.0F, 6.0F, 4.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 17.4167F, 2.1667F);
		setRotationAngle(body, 0.0F, -0.5236F, 0.0F);
		body.setTextureOffset(40, 34).addBox(3.0F, -6.4167F, -5.1667F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(42, 50).addBox(-1.0F, -6.4167F, -1.1667F, 8.0F, 6.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(42, 50).addBox(-7.0F, -6.4167F, 2.8333F, 8.0F, 6.0F, 0.0F, 0.0F, true);
		body.setTextureOffset(40, 34).addBox(-3.0F, -6.4167F, -1.1667F, 0.0F, 6.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(0, 0).addBox(-5.0F, -0.4167F, -7.1667F, 10.0F, 7.0F, 12.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(-0.5F, 0.5833F, 2.8333F);
		body.addChild(tail);
		setRotationAngle(tail, -1.2217F, 0.0F, 0.0F);
		tail.setTextureOffset(25, 19).addBox(-1.0F, -1.2817F, -0.5977F, 3.0F, 0.0F, 8.0F, 0.0F, false);

		front_right_leg = new ModelRenderer(this);
		front_right_leg.setRotationPoint(3.0F, 20.0F, -3.0F);
		front_right_leg.setTextureOffset(16, 43)
				.addBox(3.0F, -0.3F, 0.2F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		front_left_leg = new ModelRenderer(this);
		front_left_leg.setRotationPoint(-3.0F, 20.0F, -3.0F);
		front_left_leg.setTextureOffset(0, 43)
				.addBox(-3.0F, 0.5F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		back_right_leg = new ModelRenderer(this);
		back_right_leg.setRotationPoint(3.0F, 20.0F, 5.0F);
		back_right_leg.setTextureOffset(42, 27)
				.addBox(-1.6F, -0.3F, 1.0F, 4.0F, 4.0F, 4.0F, 0.0F, true);

		back_left_leg = new ModelRenderer(this);
		back_left_leg.setRotationPoint(-3.0F, 20.0F, 5.0F);
		back_left_leg.setTextureOffset(39, 19)
				.addBox(-4.5F, 0.5F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		front_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		front_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		back_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		back_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}