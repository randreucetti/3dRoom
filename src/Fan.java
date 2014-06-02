/* Ross Andreucetti
 * 58432481
 * CASE 4
 */

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;


public class Fan //our roof fan
{
	Transform3D fanTransform;
	TransformGroup fan;
	RotationInterpolator ri;	//makes the fan spin
	
	public Fan()
	{
		fanTransform = new Transform3D();
		fan = new TransformGroup();
	}
	
	public TransformGroup getFan()
	{
		Appearance fanApp = new Appearance();
		TextureLoader fanLoader = new TextureLoader (getClass().getResource("img/beech.jpg"), null);	//loads in our image
		Texture fanTex = fanLoader.getTexture ();
		TextureAttributes fanTa = new TextureAttributes ();
		fanTa.setTextureMode(TextureAttributes.MODULATE);
		fanTa.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
		Material fanMat = new Material();		//sets a material also to allow shadings
		fanMat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		fanMat.setSpecularColor(new Color3f(.1f,0.1f,.1f));
		fanMat.setShininess(.2f);
		fanApp.setMaterial(fanMat);
		fanApp.setTextureAttributes(fanTa);
		fanApp.setTexture(fanTex);
		
		Cylinder c1 = new Cylinder(.1f,1f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,fanApp);	//top of fan
		TransformGroup c1Tg = new TransformGroup();
		c1Tg.addChild(c1);
		fanTransform.setTranslation(new Vector3f(0f,8.1f,0f));
		c1Tg.setTransform(fanTransform);
		fan.addChild(c1Tg);
		
		Cylinder c2 = new Cylinder(.7f,.2f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,fanApp);	//main plate
		TransformGroup c2Tg = new TransformGroup();
		c2Tg.addChild(c2);
		fanTransform.setTranslation(new Vector3f(0f,7.5f,0f));
		c2Tg.setTransform(fanTransform);
		fan.addChild(c2Tg);
		
		Box b1 = new Box(1.5f,.05f,.3f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,fanApp);	//all the arms
		TransformGroup b1Tg = new TransformGroup();
		b1Tg.addChild(b1);
		fanTransform.rotX(.78);
		fanTransform.setTranslation(new Vector3f(2f,7.5f,0f));
		b1Tg.setTransform(fanTransform);
		fan.addChild(b1Tg);
		
		Box b2 = new Box(1.5f,.05f,.3f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,fanApp);
		TransformGroup b2Tg = new TransformGroup();
		b2Tg.addChild(b2);
		fanTransform.rotX(2.44);
		fanTransform.setTranslation(new Vector3f(-2f,7.5f,0f));
		b2Tg.setTransform(fanTransform);
		fan.addChild(b2Tg);
		
		Box b3 = new Box(.3f,.05f,1.5f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,fanApp);
		TransformGroup b3Tg = new TransformGroup();
		b3Tg.addChild(b3);
		fanTransform.rotZ(.78);
		fanTransform.setTranslation(new Vector3f(0f,7.5f,-2f));
		b3Tg.setTransform(fanTransform);
		fan.addChild(b3Tg);
		
		Box b4 = new Box(.3f,.05f,1.5f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,fanApp);
		TransformGroup b4Tg = new TransformGroup();
		b4Tg.addChild(b4);
		fanTransform.rotZ(.78);
		fanTransform.setTranslation(new Vector3f(0f,7.5f,2f));
		b4Tg.setTransform(fanTransform);
		fan.addChild(b4Tg);
		
		fan.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	//sets up our spining
		Alpha spinAlpha = new Alpha(-1,0);
		ri = new RotationInterpolator(spinAlpha, fan);
		BoundingSphere zone = new BoundingSphere();
		ri.setSchedulingBounds(zone);
		fan.addChild(ri);
		
		return fan;
	}
	public void activate()	//moves our fan
	{
		ri.setAlpha(new Alpha(-1,4000));	//starts the fan
	}
	public void deActivate()	//stops fan
	{
		ri.setAlpha(new Alpha(-1,0));
	}
}
