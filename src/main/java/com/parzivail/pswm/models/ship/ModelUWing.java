package com.parzivail.pswm.models.ship;

import com.parzivail.pswm.vehicle.VehicUWing;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * U-Wing - Weaston
 * Created using P-Tabula 4.1.1
 */
public class ModelUWing extends ModelBase
{
	public ModelRenderer BodyMainParent;
	public ModelRenderer shape2;
	public ModelRenderer shape3;
	public ModelRenderer WingLParent;
	public ModelRenderer WingLParent_9;
	public ModelRenderer WingLParent_10;
	public ModelRenderer WingLParent_11;
	public ModelRenderer WingLParent_12;
	public ModelRenderer WingLParent_13;
	public ModelRenderer WingLParent_14;
	public ModelRenderer WingLParent_15;
	public ModelRenderer WingLParent_16;
	public ModelRenderer WingLParent_17;
	public ModelRenderer WingLParent_18;
	public ModelRenderer WingLParent_19;
	public ModelRenderer WingRParent;
	public ModelRenderer WingLParent_25;
	public ModelRenderer WingLParent_26;
	public ModelRenderer WingLParent_27;
	public ModelRenderer WingLParent_28;
	public ModelRenderer WingLParentmX_29;
	public ModelRenderer WingLParentmX_30;
	public ModelRenderer WingLParent_31;
	public ModelRenderer EngineTopLParent;
	public ModelRenderer EngineBottomLParent;
	public ModelRenderer WingLParent_54;
	public ModelRenderer WingLParent_55;
	public ModelRenderer WingLParent_56;
	public ModelRenderer WingLParent_57;
	public ModelRenderer WingLParent_58;
	public ModelRenderer WingLParent_59;
	public ModelRenderer WingLParent_60;
	public ModelRenderer WingLParent_61;
	public ModelRenderer WingLParent_62;
	public ModelRenderer WingLParent_63;
	public ModelRenderer WingLParent_64;
	public ModelRenderer WingLParent_65;
	public ModelRenderer WingLParent_66;
	public ModelRenderer WingLParent_67;
	public ModelRenderer WingLParent_68;
	public ModelRenderer WingLParent_69;
	public ModelRenderer WingLParent_70;
	public ModelRenderer WingLParent_71;
	public ModelRenderer shape72;
	public ModelRenderer shape73;
	public ModelRenderer WingLParent_74;
	public ModelRenderer WingLParent_75;
	public ModelRenderer WingLParent_76;
	public ModelRenderer WingLParent_77;
	public ModelRenderer WingLParent_78;
	public ModelRenderer WingLParent_79;
	public ModelRenderer WingLParent_80;
	public ModelRenderer EngineTopRParent;
	public ModelRenderer EngineBottomRParent;
	public ModelRenderer WingLParent_103;
	public ModelRenderer WingLParent_104;
	public ModelRenderer WingLParent_5;
	public ModelRenderer WingLParent_6;
	public ModelRenderer WingLParent_7;
	public ModelRenderer WingLParent_8;
	public ModelRenderer WingLParent_21;
	public ModelRenderer WingLParent_22;
	public ModelRenderer WingLParent_23;
	public ModelRenderer WingLParent_24;
	public ModelRenderer WingLParent_33;
	public ModelRenderer WingLParent_34;
	public ModelRenderer WingLParent_35;
	public ModelRenderer WingLParent_36;
	public ModelRenderer WingLParent_37;
	public ModelRenderer WingLParent_38;
	public ModelRenderer WingLParent_39;
	public ModelRenderer WingLParent_40;
	public ModelRenderer WingLParent_41;
	public ModelRenderer WingLParent_42;
	public ModelRenderer WingLParent_44;
	public ModelRenderer WingLParent_45;
	public ModelRenderer WingLParent_46;
	public ModelRenderer WingLParent_47;
	public ModelRenderer WingLParent_48;
	public ModelRenderer WingLParent_49;
	public ModelRenderer WingLParent_50;
	public ModelRenderer WingLParent_51;
	public ModelRenderer WingLParent_52;
	public ModelRenderer WingLParent_53;
	public ModelRenderer WingLParent_82;
	public ModelRenderer WingLParent_83;
	public ModelRenderer WingLParent_84;
	public ModelRenderer WingLParent_85;
	public ModelRenderer WingLParent_86;
	public ModelRenderer WingLParent_87;
	public ModelRenderer WingLParent_88;
	public ModelRenderer WingLParent_89;
	public ModelRenderer WingLParent_90;
	public ModelRenderer WingLParent_91;
	public ModelRenderer WingLParent_93;
	public ModelRenderer WingLParent_94;
	public ModelRenderer WingLParent_95;
	public ModelRenderer WingLParent_96;
	public ModelRenderer WingLParent_97;
	public ModelRenderer WingLParent_98;
	public ModelRenderer WingLParent_99;
	public ModelRenderer WingLParent_100;
	public ModelRenderer WingLParent_101;
	public ModelRenderer WingLParent_102;

	public ModelUWing()
	{
		this.textureWidth = 1024;
		this.textureHeight = 1024;
		this.WingLParent_99 = new ModelRenderer(this, 0, 958);
		this.WingLParent_99.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_99.addBox(20.6F, -2.0F, 33.6F, 8, 2, 25, 0.0F);
		this.WingLParent_6 = new ModelRenderer(this, 932, 0);
		this.WingLParent_6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_6.addBox(1.5F, -1.1F, -43.4F, 8, 2, 37, 0.0F);
		this.WingLParent_67 = new ModelRenderer(this, 730, 78);
		this.WingLParent_67.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_67.addBox(-0.8F, -6.01F, 10.32F, 6, 6, 9, 0.0F);
		this.shape3 = new ModelRenderer(this, 0, 144);
		this.shape3.setRotationPoint(1.0F, -2.18F, 0.0F);
		this.shape3.addBox(-17.07F, -0.55F, -33.0F, 10, 2, 74, 0.0F);
		this.setRotateAngle(shape3, 0.0F, 0.0F, -0.24434609527920614F);
		this.WingLParent_102 = new ModelRenderer(this, 590, 144);
		this.WingLParent_102.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_102.addBox(20.6F, -8.0F, 33.7F, 8, 7, 14, 0.0F);
		this.EngineTopRParent = new ModelRenderer(this, 68, 790);
		this.EngineTopRParent.setRotationPoint(-49.5F, 0.0F, 0.0F);
		this.EngineTopRParent.addBox(19.6F, -9.0F, 23.6F, 10, 2, 23, 0.0F);
		this.WingLParent_91 = new ModelRenderer(this, 544, 144);
		this.WingLParent_91.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_91.addBox(20.6F, -8.0F, 33.7F, 8, 7, 14, 0.0F);
		this.WingLParent_93 = new ModelRenderer(this, 0, 902);
		this.WingLParent_93.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_93.addBox(19.6F, -1.0F, 23.6F, 10, 2, 23, 0.0F);
		this.shape2 = new ModelRenderer(this, 762, 0);
		this.shape2.setRotationPoint(-1.0F, -2.18F, 0.0F);
		this.shape2.addBox(7.07F, -0.55F, -33.0F, 10, 2, 74, 0.0F);
		this.setRotateAngle(shape2, 0.0F, 0.0F, 0.24434609527920614F);
		this.WingLParent_78 = new ModelRenderer(this, 154, 641);
		this.WingLParent_78.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_78.addBox(6.0F, -2.52F, -43.33F, 1, 1, 6, 0.0F);
		this.WingLParent_65 = new ModelRenderer(this, 120, 451);
		this.WingLParent_65.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_65.addBox(-0.8F, -6.11F, 1.55F, 6, 1, 18, 0.0F);
		this.BodyMainParent = new ModelRenderer(this, 588, 0);
		this.BodyMainParent.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.BodyMainParent.addBox(-6.0F, -1.0F, -33.0F, 12, 2, 74, 0.0F);
		this.WingLParent_48 = new ModelRenderer(this, 128, 980);
		this.WingLParent_48.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_48.addBox(23.6F, -5.2F, 24.1F, 2, 2, 17, 0.0F);
		this.WingLParent_75 = new ModelRenderer(this, 340, 144);
		this.WingLParent_75.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_75.addBox(-0.52F, -0.07F, -33.33F, 7, 2, 19, 0.0F);
		this.EngineBottomLParent = new ModelRenderer(this, 68, 734);
		this.EngineBottomLParent.setRotationPoint(0.0F, 12.8F, 0.0F);
		this.EngineBottomLParent.addBox(19.6F, -9.0F, 23.6F, 10, 2, 23, 0.0F);
		this.WingLParent_98 = new ModelRenderer(this, 0, 929);
		this.WingLParent_98.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_98.addBox(20.6F, -8.0F, 33.6F, 8, 2, 25, 0.0F);
		this.WingLParent_86 = new ModelRenderer(this, 128, 1001);
		this.WingLParent_86.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_86.addBox(23.6F, -5.2F, 24.1F, 2, 2, 17, 0.0F);
		this.WingLParent_77 = new ModelRenderer(this, 680, 144);
		this.WingLParent_77.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_77.addBox(5.5F, -3.02F, -38.33F, 2, 2, 19, 0.0F);
		this.WingLParent_22 = new ModelRenderer(this, 932, 41);
		this.WingLParent_22.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_22.addBox(1.4F, -1.1F, -43.4F, 8, 2, 37, 0.0F);
		this.WingLParent_16 = new ModelRenderer(this, 136, 386);
		this.WingLParent_16.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_16.addBox(1.4F, -1.81F, 34.77F, 1, 7, 7, 0.0F);
		this.WingLParent_71 = new ModelRenderer(this, 904, 78);
		this.WingLParent_71.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_71.addBox(-5.2F, -3.21F, 8.02F, 6, 2, 3, 0.0F);
		this.WingLParent_53 = new ModelRenderer(this, 498, 144);
		this.WingLParent_53.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_53.addBox(20.6F, -8.0F, 33.7F, 8, 7, 14, 0.0F);
		this.WingLParent_24 = new ModelRenderer(this, 0, 987);
		this.WingLParent_24.setRotationPoint(-1.71F, 0.0F, -3.65F);
		this.WingLParent_24.addBox(1.5F, -1.1F, -6.4F, 8, 2, 21, 0.0F);
		this.setRotateAngle(WingLParent_24, 0.0F, -0.3490658503988659F, 0.0F);
		this.WingLParent_46 = new ModelRenderer(this, 774, 169);
		this.WingLParent_46.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_46.addBox(27.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.WingLParent_7 = new ModelRenderer(this, 94, 598);
		this.WingLParent_7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_7.addBox(0.6F, -1.1F, -68.4F, 5, 2, 31, 0.0F);
		this.WingLParent_19 = new ModelRenderer(this, 136, 434);
		this.WingLParent_19.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_19.addBox(-4.4F, -1.81F, 34.77F, 1, 7, 7, 0.0F);
		this.WingLParent_82 = new ModelRenderer(this, 0, 846);
		this.WingLParent_82.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_82.addBox(19.6F, -1.0F, 23.6F, 10, 2, 23, 0.0F);
		this.WingLParent_89 = new ModelRenderer(this, 394, 169);
		this.WingLParent_89.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_89.addBox(20.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.WingLParent_51 = new ModelRenderer(this, 282, 169);
		this.WingLParent_51.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_51.addBox(20.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.WingLParent_44 = new ModelRenderer(this, 0, 790);
		this.WingLParent_44.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_44.addBox(19.6F, -1.0F, 23.6F, 10, 2, 23, 0.0F);
		this.WingLParent_83 = new ModelRenderer(this, 826, 169);
		this.WingLParent_83.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_83.addBox(19.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.WingLParent_36 = new ModelRenderer(this, 68, 902);
		this.WingLParent_36.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_36.addBox(21.6F, -4.2F, 24.1F, 6, 1, 23, 0.0F);
		this.WingLParent_42 = new ModelRenderer(this, 120, 532);
		this.WingLParent_42.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_42.addBox(20.6F, -8.0F, 33.7F, 8, 7, 14, 0.0F);
		this.WingLParent_13 = new ModelRenderer(this, 904, 96);
		this.WingLParent_13.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_13.addBox(-6.2F, -1.81F, 34.77F, 1, 7, 7, 0.0F);
		this.shape72 = new ModelRenderer(this, 588, 78);
		this.shape72.setRotationPoint(-1.0F, 7.12F, 0.0F);
		this.shape72.addBox(7.07F, -0.45F, -33.0F, 10, 2, 60, 0.0F);
		this.setRotateAngle(shape72, 0.0F, 0.0F, -0.24434609527920614F);
		this.WingLParent_49 = new ModelRenderer(this, 0, 817);
		this.WingLParent_49.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_49.addBox(20.6F, -8.0F, 33.6F, 8, 2, 25, 0.0F);
		this.EngineBottomRParent = new ModelRenderer(this, 68, 846);
		this.EngineBottomRParent.setRotationPoint(-49.5F, 12.8F, 0.0F);
		this.EngineBottomRParent.addBox(19.6F, -9.0F, 23.6F, 10, 2, 23, 0.0F);
		this.WingLParent_35 = new ModelRenderer(this, 670, 169);
		this.WingLParent_35.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_35.addBox(27.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.WingLParent_39 = new ModelRenderer(this, 68, 761);
		this.WingLParent_39.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_39.addBox(20.6F, -2.0F, 33.6F, 8, 2, 25, 0.0F);
		this.WingLParent_90 = new ModelRenderer(this, 450, 169);
		this.WingLParent_90.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_90.addBox(26.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.WingLParent_74 = new ModelRenderer(this, 286, 144);
		this.WingLParent_74.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_74.addBox(-6.5F, -0.07F, -33.33F, 7, 2, 19, 0.0F);
		this.WingLParent_17 = new ModelRenderer(this, 136, 402);
		this.WingLParent_17.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_17.addBox(-0.6F, -1.81F, 34.77F, 1, 7, 7, 0.0F);
		this.WingLParent_58 = new ModelRenderer(this, 154, 402);
		this.WingLParent_58.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_58.addBox(-0.8F, -6.11F, 1.42F, 6, 6, 1, 0.0F);
		this.WingLParent_26 = new ModelRenderer(this, 0, 666);
		this.WingLParent_26.setRotationPoint(0.0F, 7.0F, 0.0F);
		this.WingLParent_26.addBox(-11.0F, -10.8F, -1.3F, 11, 2, 29, 0.0F);
		this.setRotateAngle(WingLParent_26, 0.0F, 0.0F, 2.0943951023931953F);
		this.WingLParentmX_29 = new ModelRenderer(this, 0, 513);
		this.WingLParentmX_29.setRotationPoint(10.0F, 20.87F, 0.0F);
		this.WingLParentmX_29.addBox(18.0F, -10.5F, -23.3F, 8, 9, 51, 0.0F);
		this.setRotateAngle(WingLParentmX_29, 0.0F, 0.0F, -2.0943951023931953F);
		this.WingLParent_87 = new ModelRenderer(this, 0, 873);
		this.WingLParent_87.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_87.addBox(20.6F, -8.0F, 33.6F, 8, 2, 25, 0.0F);
		this.WingLParent_40 = new ModelRenderer(this, 170, 169);
		this.WingLParent_40.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_40.addBox(20.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.WingLParent_50 = new ModelRenderer(this, 68, 817);
		this.WingLParent_50.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_50.addBox(20.6F, -2.0F, 33.6F, 8, 2, 25, 0.0F);
		this.WingLParent_63 = new ModelRenderer(this, 730, 117);
		this.WingLParent_63.setRotationPoint(0.0F, -6.39F, 0.0F);
		this.WingLParent_63.addBox(-5.2F, -8.93F, 17.4F, 1, 5, 11, 0.0F);
		this.setRotateAngle(WingLParent_63, -0.4886921905584123F, 0.0F, 0.0F);
		this.WingLParent_28 = new ModelRenderer(this, 104, 575);
		this.WingLParent_28.setRotationPoint(0.0F, 9.96F, 0.29F);
		this.WingLParent_28.addBox(-6.0F, -0.52F, -33.4F, 12, 2, 19, 0.0F);
		this.setRotateAngle(WingLParent_28, -0.136659280431156F, 0.0F, 0.0F);
		this.WingLParent_5 = new ModelRenderer(this, 0, 222);
		this.WingLParent_5.setRotationPoint(-4.0F, 0.0F, 7.0F);
		this.WingLParent_5.addBox(13.03F, -1.1F, -126.9F, 5, 2, 78, 0.0F);
		this.setRotateAngle(WingLParent_5, 0.0F, 0.091106186954104F, 0.0F);
		this.WingLParent_41 = new ModelRenderer(this, 226, 169);
		this.WingLParent_41.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_41.addBox(26.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.WingLParent_9 = new ModelRenderer(this, 394, 144);
		this.WingLParent_9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_9.addBox(15.2F, 0.1F, 26.0F, 7, 5, 18, 0.0F);
		this.WingLParent_95 = new ModelRenderer(this, 170, 200);
		this.WingLParent_95.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_95.addBox(27.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.WingLParent_23 = new ModelRenderer(this, 0, 699);
		this.WingLParent_23.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_23.addBox(0.4F, -1.1F, -71.4F, 5, 2, 31, 0.0F);
		this.WingLParent_54 = new ModelRenderer(this, 446, 144);
		this.WingLParent_54.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_54.addBox(-22.3F, 0.1F, 26.0F, 7, 5, 18, 0.0F);
		this.WingLParent_11 = new ModelRenderer(this, 120, 513);
		this.WingLParent_11.setRotationPoint(0.0F, -6.39F, 0.0F);
		this.WingLParent_11.addBox(-5.0F, -8.71F, 17.4F, 10, 5, 12, 0.0F);
		this.setRotateAngle(WingLParent_11, -0.4886921905584123F, 0.0F, 0.0F);
		this.WingLParent_25 = new ModelRenderer(this, 228, 144);
		this.WingLParent_25.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_25.addBox(-5.0F, -5.91F, 1.47F, 10, 5, 18, 0.0F);
		this.WingLParent_57 = new ModelRenderer(this, 154, 386);
		this.WingLParent_57.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_57.addBox(-5.2F, -6.11F, 1.42F, 6, 6, 1, 0.0F);
		this.WingLParent_85 = new ModelRenderer(this, 68, 954);
		this.WingLParent_85.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_85.addBox(21.6F, -4.2F, 24.1F, 6, 1, 23, 0.0F);
		this.WingLParent_94 = new ModelRenderer(this, 930, 169);
		this.WingLParent_94.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_94.addBox(19.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.WingLParent_104 = new ModelRenderer(this, 932, 120);
		this.WingLParent_104.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_104.addBox(-10.52F, -4.57F, 21.67F, 21, 2, 19, 0.0F);
		this.WingLParent_60 = new ModelRenderer(this, 932, 82);
		this.WingLParent_60.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_60.addBox(-5.2F, -1.61F, -12.98F, 6, 1, 35, 0.0F);
		this.WingLParent_84 = new ModelRenderer(this, 878, 169);
		this.WingLParent_84.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_84.addBox(27.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.WingLParent_14 = new ModelRenderer(this, 904, 112);
		this.WingLParent_14.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_14.addBox(5.2F, -1.81F, 34.77F, 1, 7, 7, 0.0F);
		this.WingLParent_64 = new ModelRenderer(this, 120, 490);
		this.WingLParent_64.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_64.addBox(-5.2F, -6.11F, 1.55F, 5, 1, 18, 0.0F);
		this.WingLParent_97 = new ModelRenderer(this, 724, 144);
		this.WingLParent_97.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_97.addBox(23.6F, -5.2F, 24.1F, 2, 2, 17, 0.0F);
		this.WingLParent_34 = new ModelRenderer(this, 618, 169);
		this.WingLParent_34.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_34.addBox(19.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.shape73 = new ModelRenderer(this, 762, 78);
		this.shape73.setRotationPoint(1.0F, 7.22F, 0.0F);
		this.shape73.addBox(-17.07F, -0.55F, -33.0F, 10, 2, 60, 0.0F);
		this.setRotateAngle(shape73, 0.0F, 0.0F, 0.24434609527920614F);
		this.WingLParent_15 = new ModelRenderer(this, 904, 128);
		this.WingLParent_15.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_15.addBox(3.3F, -1.81F, 34.77F, 1, 7, 7, 0.0F);
		this.EngineTopLParent = new ModelRenderer(this, 74, 699);
		this.EngineTopLParent.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.EngineTopLParent.addBox(19.6F, -9.0F, 23.6F, 10, 2, 23, 0.0F);
		this.WingLParent_21 = new ModelRenderer(this, 0, 304);
		this.WingLParent_21.setRotationPoint(-4.2F, 0.0F, 6.0F);
		this.WingLParent_21.addBox(13.03F, -1.1F, -125.9F, 5, 2, 78, 0.0F);
		this.setRotateAngle(WingLParent_21, 0.0F, 0.091106186954104F, 0.0F);
		this.WingLParentmX_30 = new ModelRenderer(this, 82, 666);
		this.WingLParentmX_30.setRotationPoint(11.0F, 26.0F, 0.0F);
		this.WingLParentmX_30.addBox(22.0F, -10.8F, -1.3F, 11, 2, 29, 0.0F);
		this.setRotateAngle(WingLParentmX_30, 0.0F, 0.0F, -2.0943951023931953F);
		this.WingLParent_56 = new ModelRenderer(this, 128, 928);
		this.WingLParent_56.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_56.addBox(4.2F, -4.9F, -16.1F, 1, 1, 19, 0.0F);
		this.setRotateAngle(WingLParent_56, 0.2792526803190927F, 0.0F, 0.0F);
		this.WingLParent_88 = new ModelRenderer(this, 68, 873);
		this.WingLParent_88.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_88.addBox(20.6F, -2.0F, 33.6F, 8, 2, 25, 0.0F);
		this.WingLParent_10 = new ModelRenderer(this, 170, 144);
		this.WingLParent_10.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_10.addBox(-5.0F, -4.7F, -15.1F, 10, 5, 18, 0.0F);
		this.setRotateAngle(WingLParent_10, 0.2792526803190927F, 0.0F, 0.0F);
		this.WingLParent_47 = new ModelRenderer(this, 68, 928);
		this.WingLParent_47.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_47.addBox(21.6F, -4.2F, 24.1F, 6, 1, 23, 0.0F);
		this.WingLParent_52 = new ModelRenderer(this, 338, 169);
		this.WingLParent_52.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_52.addBox(26.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.WingLParent_62 = new ModelRenderer(this, 120, 555);
		this.WingLParent_62.setRotationPoint(0.0F, -6.39F, 0.0F);
		this.WingLParent_62.addBox(-4.8F, -8.93F, 17.38F, 10, 5, 11, 0.0F);
		this.setRotateAngle(WingLParent_62, -0.4886921905584123F, 0.0F, 0.0F);
		this.WingLParent_100 = new ModelRenderer(this, 506, 169);
		this.WingLParent_100.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_100.addBox(20.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.WingLParent_66 = new ModelRenderer(this, 154, 418);
		this.WingLParent_66.setRotationPoint(0.0F, 0.6F, 7.0F);
		this.WingLParent_66.addBox(-0.8F, -6.11F, 1.42F, 6, 6, 1, 0.0F);
		this.setRotateAngle(WingLParent_66, 0.3141592653589793F, 0.0F, 0.0F);
		this.WingLParent_55 = new ModelRenderer(this, 128, 902);
		this.WingLParent_55.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_55.addBox(-5.2F, -4.9F, -16.1F, 1, 1, 19, 0.0F);
		this.setRotateAngle(WingLParent_55, 0.2792526803190927F, 0.0F, 0.0F);
		this.WingLParent_70 = new ModelRenderer(this, 154, 434);
		this.WingLParent_70.setRotationPoint(0.0F, 0.6F, 7.0F);
		this.WingLParent_70.addBox(-5.2F, -6.11F, 1.42F, 6, 6, 1, 0.0F);
		this.setRotateAngle(WingLParent_70, 0.3141592653589793F, 0.0F, 0.0F);
		this.WingLParent_76 = new ModelRenderer(this, 636, 144);
		this.WingLParent_76.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_76.addBox(-7.5F, -3.02F, -38.33F, 2, 2, 19, 0.0F);
		this.WingLParent_38 = new ModelRenderer(this, 0, 761);
		this.WingLParent_38.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_38.addBox(20.6F, -8.0F, 33.6F, 8, 2, 25, 0.0F);
		this.WingLParent = new ModelRenderer(this, 0, 0);
		this.WingLParent.setRotationPoint(19.0F, 2.5F, 22.2F);
		this.WingLParent.addBox(-3.5F, -1.1F, -121.0F, 6, 2, 140, 0.0F);
		this.WingLParent_31 = new ModelRenderer(this, 0, 386);
		this.WingLParent_31.setRotationPoint(-2.0F, 7.0F, 0.0F);
		this.WingLParent_31.addBox(-4.0F, -4.3F, -24.3F, 12, 8, 55, 0.0F);
		this.WingLParent_27 = new ModelRenderer(this, 0, 451);
		this.WingLParent_27.setRotationPoint(-2.0F, 7.0F, 0.0F);
		this.WingLParent_27.addBox(-10.0F, -10.5F, -23.3F, 8, 9, 51, 0.0F);
		this.setRotateAngle(WingLParent_27, 0.0F, 0.0F, 2.0943951023931953F);
		this.WingLParent_61 = new ModelRenderer(this, 730, 112);
		this.WingLParent_61.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_61.addBox(-5.2F, -4.9F, -16.6F, 10, 1, 2, 0.0F);
		this.setRotateAngle(WingLParent_61, 0.2792526803190927F, 0.0F, 0.0F);
		this.WingRParent = new ModelRenderer(this, 294, 0);
		this.WingRParent.setRotationPoint(-19.2F, 2.3F, 22.2F);
		this.WingRParent.addBox(-3.7F, -1.1F, -121.0F, 6, 2, 140, 0.0F);
		this.setRotateAngle(WingRParent, 0.0F, 0.0F, -3.141592653589793F);
		this.WingLParent_33 = new ModelRenderer(this, 0, 734);
		this.WingLParent_33.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_33.addBox(19.6F, -1.0F, 23.6F, 10, 2, 23, 0.0F);
		this.WingLParent_103 = new ModelRenderer(this, 0, 575);
		this.WingLParent_103.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_103.addBox(-15.52F, -3.07F, 21.67F, 32, 2, 19, 0.0F);
		this.WingLParent_69 = new ModelRenderer(this, 730, 95);
		this.WingLParent_69.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_69.addBox(-5.2F, -6.01F, 10.32F, 6, 6, 9, 0.0F);
		this.WingLParent_96 = new ModelRenderer(this, 68, 980);
		this.WingLParent_96.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_96.addBox(21.6F, -4.2F, 24.1F, 6, 1, 23, 0.0F);
		this.WingLParent_12 = new ModelRenderer(this, 120, 472);
		this.WingLParent_12.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_12.addBox(-6.0F, -1.31F, 29.27F, 12, 5, 11, 0.0F);
		this.WingLParent_37 = new ModelRenderer(this, 128, 954);
		this.WingLParent_37.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_37.addBox(23.6F, -5.2F, 24.1F, 2, 2, 17, 0.0F);
		this.WingLParent_45 = new ModelRenderer(this, 722, 169);
		this.WingLParent_45.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_45.addBox(19.6F, -7.0F, 23.6F, 2, 6, 23, 0.0F);
		this.WingLParent_59 = new ModelRenderer(this, 0, 598);
		this.WingLParent_59.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_59.addBox(-0.8F, -1.61F, -12.98F, 6, 1, 40, 0.0F);
		this.WingLParent_79 = new ModelRenderer(this, 904, 85);
		this.WingLParent_79.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_79.addBox(-7.0F, -2.52F, -43.33F, 1, 1, 8, 0.0F);
		this.WingLParent_68 = new ModelRenderer(this, 730, 135);
		this.WingLParent_68.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_68.addBox(-0.8F, -3.21F, 8.02F, 6, 2, 3, 0.0F);
		this.WingLParent_80 = new ModelRenderer(this, 0, 641);
		this.WingLParent_80.setRotationPoint(0.0F, 4.96F, 0.33F);
		this.WingLParent_80.addBox(-13.5F, -4.02F, -32.33F, 27, 4, 19, 0.0F);
		this.WingLParent_18 = new ModelRenderer(this, 136, 418);
		this.WingLParent_18.setRotationPoint(0.0F, -0.6F, 0.0F);
		this.WingLParent_18.addBox(-2.6F, -1.81F, 34.77F, 1, 7, 7, 0.0F);
		this.WingLParent_8 = new ModelRenderer(this, 94, 641);
		this.WingLParent_8.setRotationPoint(-1.61F, 0.0F, -3.65F);
		this.WingLParent_8.addBox(1.5F, -1.1F, -6.4F, 8, 2, 21, 0.0F);
		this.setRotateAngle(WingLParent_8, 0.0F, -0.3490658503988659F, 0.0F);
		this.WingLParent_101 = new ModelRenderer(this, 562, 169);
		this.WingLParent_101.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.WingLParent_101.addBox(26.6F, -6.0F, 33.6F, 2, 4, 25, 0.0F);
		this.EngineBottomRParent.addChild(this.WingLParent_99);
		this.WingLParent.addChild(this.WingLParent_6);
		this.BodyMainParent.addChild(this.WingLParent_67);
		this.BodyMainParent.addChild(this.shape3);
		this.EngineBottomRParent.addChild(this.WingLParent_102);
		this.BodyMainParent.addChild(this.EngineTopRParent);
		this.EngineTopRParent.addChild(this.WingLParent_91);
		this.EngineBottomRParent.addChild(this.WingLParent_93);
		this.BodyMainParent.addChild(this.shape2);
		this.BodyMainParent.addChild(this.WingLParent_78);
		this.BodyMainParent.addChild(this.WingLParent_65);
		this.EngineBottomLParent.addChild(this.WingLParent_48);
		this.BodyMainParent.addChild(this.WingLParent_75);
		this.BodyMainParent.addChild(this.EngineBottomLParent);
		this.EngineBottomRParent.addChild(this.WingLParent_98);
		this.EngineTopRParent.addChild(this.WingLParent_86);
		this.BodyMainParent.addChild(this.WingLParent_77);
		this.WingRParent.addChild(this.WingLParent_22);
		this.BodyMainParent.addChild(this.WingLParent_16);
		this.BodyMainParent.addChild(this.WingLParent_71);
		this.EngineBottomLParent.addChild(this.WingLParent_53);
		this.WingRParent.addChild(this.WingLParent_24);
		this.EngineBottomLParent.addChild(this.WingLParent_46);
		this.WingLParent.addChild(this.WingLParent_7);
		this.BodyMainParent.addChild(this.WingLParent_19);
		this.EngineTopRParent.addChild(this.WingLParent_82);
		this.EngineTopRParent.addChild(this.WingLParent_89);
		this.EngineBottomLParent.addChild(this.WingLParent_51);
		this.EngineBottomLParent.addChild(this.WingLParent_44);
		this.EngineTopRParent.addChild(this.WingLParent_83);
		this.EngineTopLParent.addChild(this.WingLParent_36);
		this.EngineTopLParent.addChild(this.WingLParent_42);
		this.BodyMainParent.addChild(this.WingLParent_13);
		this.BodyMainParent.addChild(this.shape72);
		this.EngineBottomLParent.addChild(this.WingLParent_49);
		this.BodyMainParent.addChild(this.EngineBottomRParent);
		this.EngineTopLParent.addChild(this.WingLParent_35);
		this.EngineTopLParent.addChild(this.WingLParent_39);
		this.EngineTopRParent.addChild(this.WingLParent_90);
		this.BodyMainParent.addChild(this.WingLParent_74);
		this.BodyMainParent.addChild(this.WingLParent_17);
		this.BodyMainParent.addChild(this.WingLParent_58);
		this.BodyMainParent.addChild(this.WingLParent_26);
		this.BodyMainParent.addChild(this.WingLParentmX_29);
		this.EngineTopRParent.addChild(this.WingLParent_87);
		this.EngineTopLParent.addChild(this.WingLParent_40);
		this.EngineBottomLParent.addChild(this.WingLParent_50);
		this.BodyMainParent.addChild(this.WingLParent_63);
		this.BodyMainParent.addChild(this.WingLParent_28);
		this.WingLParent.addChild(this.WingLParent_5);
		this.EngineTopLParent.addChild(this.WingLParent_41);
		this.BodyMainParent.addChild(this.WingLParent_9);
		this.EngineBottomRParent.addChild(this.WingLParent_95);
		this.WingRParent.addChild(this.WingLParent_23);
		this.BodyMainParent.addChild(this.WingLParent_54);
		this.BodyMainParent.addChild(this.WingLParent_11);
		this.BodyMainParent.addChild(this.WingLParent_25);
		this.BodyMainParent.addChild(this.WingLParent_57);
		this.EngineTopRParent.addChild(this.WingLParent_85);
		this.EngineBottomRParent.addChild(this.WingLParent_94);
		this.BodyMainParent.addChild(this.WingLParent_104);
		this.BodyMainParent.addChild(this.WingLParent_60);
		this.EngineTopRParent.addChild(this.WingLParent_84);
		this.BodyMainParent.addChild(this.WingLParent_14);
		this.BodyMainParent.addChild(this.WingLParent_64);
		this.EngineBottomRParent.addChild(this.WingLParent_97);
		this.EngineTopLParent.addChild(this.WingLParent_34);
		this.BodyMainParent.addChild(this.shape73);
		this.BodyMainParent.addChild(this.WingLParent_15);
		this.BodyMainParent.addChild(this.EngineTopLParent);
		this.WingRParent.addChild(this.WingLParent_21);
		this.BodyMainParent.addChild(this.WingLParentmX_30);
		this.BodyMainParent.addChild(this.WingLParent_56);
		this.EngineTopRParent.addChild(this.WingLParent_88);
		this.BodyMainParent.addChild(this.WingLParent_10);
		this.EngineBottomLParent.addChild(this.WingLParent_47);
		this.EngineBottomLParent.addChild(this.WingLParent_52);
		this.BodyMainParent.addChild(this.WingLParent_62);
		this.EngineBottomRParent.addChild(this.WingLParent_100);
		this.BodyMainParent.addChild(this.WingLParent_66);
		this.BodyMainParent.addChild(this.WingLParent_55);
		this.BodyMainParent.addChild(this.WingLParent_70);
		this.BodyMainParent.addChild(this.WingLParent_76);
		this.EngineTopLParent.addChild(this.WingLParent_38);
		this.BodyMainParent.addChild(this.WingLParent);
		this.BodyMainParent.addChild(this.WingLParent_31);
		this.BodyMainParent.addChild(this.WingLParent_27);
		this.BodyMainParent.addChild(this.WingLParent_61);
		this.BodyMainParent.addChild(this.WingRParent);
		this.EngineTopLParent.addChild(this.WingLParent_33);
		this.BodyMainParent.addChild(this.WingLParent_103);
		this.BodyMainParent.addChild(this.WingLParent_69);
		this.EngineBottomRParent.addChild(this.WingLParent_96);
		this.BodyMainParent.addChild(this.WingLParent_12);
		this.EngineTopLParent.addChild(this.WingLParent_37);
		this.EngineBottomLParent.addChild(this.WingLParent_45);
		this.BodyMainParent.addChild(this.WingLParent_59);
		this.BodyMainParent.addChild(this.WingLParent_79);
		this.BodyMainParent.addChild(this.WingLParent_68);
		this.BodyMainParent.addChild(this.WingLParent_80);
		this.BodyMainParent.addChild(this.WingLParent_18);
		this.WingLParent.addChild(this.WingLParent_8);
		this.EngineBottomRParent.addChild(this.WingLParent_101);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float pt, float f5)
	{
		GL11.glPushMatrix();
		if (entity instanceof VehicUWing)
		{
			VehicUWing uWing = (VehicUWing)entity;

			this.WingLParent.rotateAngleY = -uWing.sFoilOpenAngle + (uWing.sFoilOpenMaxAngle / uWing.sFoilOpenTicks) * pt * -uWing.sFoilDirection;
			this.WingRParent.rotateAngleY = -uWing.sFoilOpenAngle + (uWing.sFoilOpenMaxAngle / uWing.sFoilOpenTicks) * pt * -uWing.sFoilDirection;
		}
		this.BodyMainParent.render(f5);
		GL11.glPopMatrix();
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}