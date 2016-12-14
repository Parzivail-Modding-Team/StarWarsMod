package starwarsmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * GirderDouble - Weaston
 * Created using P-Tabula 4.1.1
 */
public class ModelGirderDouble extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape5;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape4;
    public ModelRenderer shape6;
    public ModelRenderer shape7;
    public ModelRenderer shape8;

    public ModelGirderDouble() {
        this.textureWidth = 512;
        this.textureHeight = 512;
        this.shape6 = new ModelRenderer(this, 120, 0);
        this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape6.addBox(-1.5F, 14.5F, -8.0F, 3, 1, 16, 0.0F);
        this.shape2 = new ModelRenderer(this, 40, 0);
        this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape2.addBox(-1.5F, 14.5F, -8.0F, 3, 1, 16, 0.0F);
        this.shape4 = new ModelRenderer(this, 0, 39);
        this.shape4.setRotationPoint(0.0F, 0.0F, 0.5F);
        this.shape4.addBox(-0.5F, 4.5F, -14.3F, 1, 2, 16, 0.0F);
        this.setRotateAngle(shape4, 1.3089969389957472F, 0.0F, 0.0F);
        this.shape3 = new ModelRenderer(this, 0, 19);
        this.shape3.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.shape3.addBox(-0.5F, 4.5F, -1.7F, 1, 2, 16, 0.0F);
        this.setRotateAngle(shape3, -1.3089969389957472F, 0.0F, 0.0F);
        this.shape7 = new ModelRenderer(this, 0, 59);
        this.shape7.setRotationPoint(0.0F, 0.0F, -0.5F);
        this.shape7.addBox(-0.5F, 4.5F, -1.7F, 1, 2, 16, 0.0F);
        this.setRotateAngle(shape7, -1.3089969389957472F, 0.0F, 0.0F);
        this.shape5 = new ModelRenderer(this, 80, 0);
        this.shape5.setRotationPoint(-5.0F, 8.5F, 0.0F);
        this.shape5.addBox(-1.5F, -0.5F, -8.0F, 3, 1, 16, 0.0F);
        this.shape1 = new ModelRenderer(this, 0, 0);
        this.shape1.setRotationPoint(5.0F, 8.5F, 0.0F);
        this.shape1.addBox(-1.5F, -0.5F, -8.0F, 3, 1, 16, 0.0F);
        this.shape8 = new ModelRenderer(this, 0, 79);
        this.shape8.setRotationPoint(0.0F, 0.0F, 0.5F);
        this.shape8.addBox(-0.5F, 4.5F, -14.3F, 1, 2, 16, 0.0F);
        this.setRotateAngle(shape8, 1.3089969389957472F, 0.0F, 0.0F);
        this.shape5.addChild(this.shape6);
        this.shape1.addChild(this.shape2);
        this.shape1.addChild(this.shape4);
        this.shape1.addChild(this.shape3);
        this.shape5.addChild(this.shape7);
        this.shape5.addChild(this.shape8);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.shape5.render(f5);
        this.shape1.render(f5);
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