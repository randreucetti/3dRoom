/* Ross Andreucetti
 * 58432481
 * CASE 4
 */

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;


public class Couch 	//leather couch, fairly simple
{
	Transform3D couchTransform;
	TransformGroup couch;
	
	public Couch()
	{
		couchTransform = new Transform3D();
		couch = new TransformGroup();
	}
	
	public TransformGroup getCouch()
	{
		Appearance couchApp = new Appearance();
		TextureLoader couchLoader = new TextureLoader (getClass().getResource("img/leather.jpg"), null);	//loads in our image
		Texture couchTex = couchLoader.getTexture ();
		TextureAttributes couchTa = new TextureAttributes ();
		Material couchMat = new Material();		//sets a material also to allow shadings
		couchMat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		couchMat.setSpecularColor(new Color3f(.1f,0.1f,.1f));
		couchMat.setShininess(.1f);
		couchTa.setTextureMode(TextureAttributes.MODULATE);
		couchApp.setMaterial(couchMat);
		couchApp.setTextureAttributes(couchTa);
		couchApp.setTexture(couchTex);
		
		Box base = new Box(1f,.7f,5f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,couchApp);	//base of couch
		TransformGroup baseTg = new TransformGroup();
		baseTg.addChild(base);
		couchTransform.setTranslation(new Vector3f(-5.5f,-2.7f,-2f));
		baseTg.setTransform(couchTransform);
		couch.addChild(baseTg);
		//back of it
		Box back = new Box(.5f,1.6f,5f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,couchApp);
		TransformGroup backTg = new TransformGroup();
		backTg.addChild(back);
		couchTransform.setTranslation(new Vector3f(-7f,-1.8f,-2f));
		backTg.setTransform(couchTransform);
		couch.addChild(backTg);
		//long headrest
		
		Cylinder headRest = new Cylinder(.7f,10f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,couchApp);
		TransformGroup headTg = new TransformGroup();
		headTg.addChild(headRest);
		couchTransform.rotX(1.56);
		couchTransform.setTranslation(new Vector3f(-6.8f,-.2f,-2f));
		headTg.setTransform(couchTransform);
		couch.addChild(headTg);
		//arms
		Cylinder leftArm = new Cylinder(.7f,2f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,couchApp);
		TransformGroup leftTg = new TransformGroup();
		leftTg.addChild(leftArm);
		couchTransform.rotZ(1.56);
		couchTransform.setTranslation(new Vector3f(-5.5f,-1.5f,2.5f));
		leftTg.setTransform(couchTransform);
		couch.addChild(leftTg);
		
		Cylinder rightArm = new Cylinder(.7f,2f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,couchApp);
		TransformGroup rightTg = new TransformGroup();
		rightTg.addChild(rightArm);
		couchTransform.rotZ(1.56);
		couchTransform.setTranslation(new Vector3f(-5.5f,-1.5f,-6.5f));
		rightTg.setTransform(couchTransform);
		couch.addChild(rightTg);
		
		return couch;
	}
}
