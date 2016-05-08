package starwarsmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelBiped - Either Mojang or a mod author
 * Created using Tabula 4.1.1
 */
public class backpackSnow extends ModelBase {
    public ModelRenderer BackpackParent;
    public ModelRenderer BackpackParent_1;
    public ModelRenderer BackpackParent_2;
    public ModelRenderer BackpackParent_3;
    public ModelRenderer BackpackParent_4;
    public ModelRenderer BackpackParent_5;
    public ModelRenderer BackpackParent_6;
    public ModelRenderer BackpackParent_7;
    public ModelRenderer BackpackParent_8;
    public ModelRenderer BackpackParent_9;

    public backpackSnow() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.BackpackParent_8 = new ModelRenderer(this, 36, 13);
        this.BackpackParent_8.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_8.addBox(1.25F, 0.2F, 1.7F, 1, 1, 3, 0.0F);
        this.setRotateAngle(BackpackParent_8, 0.10471975511965977F, 0.0F, 0.0F);
        this.BackpackParent_9 = new ModelRenderer(this, 24, 23);
        this.BackpackParent_9.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_9.addBox(-2.0F, -2.5F, 4.3F, 1, 1, 1, 0.0F);
        this.BackpackParent_5 = new ModelRenderer(this, 39, 0);
        this.BackpackParent_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_5.addBox(-3.0F, -3.0F, 3.0F, 3, 2, 2, 0.0F);
        this.BackpackParent_6 = new ModelRenderer(this, 51, 0);
        this.BackpackParent_6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_6.addBox(0.75F, -3.0F, 1.5F, 2, 5, 3, 0.0F);
        this.setRotateAngle(BackpackParent_6, 0.10471975511965977F, 0.0F, 0.0F);
        this.BackpackParent_1 = new ModelRenderer(this, 0, 14);
        this.BackpackParent_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_1.addBox(-4.0F, -6.0F, 2.0F, 8, 2, 2, 0.0F);
        this.BackpackParent_4 = new ModelRenderer(this, 32, 0);
        this.BackpackParent_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_4.addBox(-3.0F, -3.0F, 3.0F, 1, 4, 2, 0.0F);
        this.BackpackParent = new ModelRenderer(this, 0, 0);
        this.BackpackParent.setRotationPoint(0.0F, 6.4F, 0.0F);
        this.BackpackParent.addBox(-4.0F, 2.0F, 1.8F, 8, 2, 3, 0.0F);
        this.BackpackParent_2 = new ModelRenderer(this, 0, 21);
        this.BackpackParent_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_2.addBox(-3.5F, -5.0F, 1.2F, 7, 8, 3, 0.0F);
        this.setRotateAngle(BackpackParent_2, 0.10471975511965977F, 0.0F, 0.0F);
        this.BackpackParent_7 = new ModelRenderer(this, 25, 10);
        this.BackpackParent_7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_7.addBox(1.25F, -2.5F, 1.7F, 1, 2, 3, 0.0F);
        this.setRotateAngle(BackpackParent_7, 0.10471975511965977F, 0.0F, 0.0F);
        this.BackpackParent_3 = new ModelRenderer(this, 24, 0);
        this.BackpackParent_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.BackpackParent_3.addBox(-1.0F, -3.0F, 3.0F, 1, 4, 2, 0.0F);
        this.BackpackParent.addChild(this.BackpackParent_8);
        this.BackpackParent.addChild(this.BackpackParent_9);
        this.BackpackParent.addChild(this.BackpackParent_5);
        this.BackpackParent.addChild(this.BackpackParent_6);
        this.BackpackParent.addChild(this.BackpackParent_1);
        this.BackpackParent.addChild(this.BackpackParent_4);
        this.BackpackParent.addChild(this.BackpackParent_2);
        this.BackpackParent.addChild(this.BackpackParent_7);
        this.BackpackParent.addChild(this.BackpackParent_3);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.BackpackParent.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
