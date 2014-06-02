/* Ross Andreucetti
 * 58432481
 * CASE 4
 */

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;


public class Picture //our picture, cafe terrace at night by Vincent van Gogh
{
	Transform3D pictureTransform;	
	TransformGroup picture;
	public Picture()
	{
		pictureTransform = new Transform3D();
		picture = new TransformGroup();
	}
	@SuppressWarnings("deprecation")
	public TransformGroup getPicture()
	{
		Appearance frameApp = new Appearance();	//appearance of our frame
		TextureLoader frameLoader = new TextureLoader (getClass().getResource("img/Oak.jpg"), null);	//loads in our image
		Texture frameTex = frameLoader.getTexture ();
		TextureAttributes frameTa = new TextureAttributes ();
		Material frameMat = new Material();		//sets a material also to allow shadings
		frameMat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		frameMat.setSpecularColor(new Color3f(.1f,0.1f,.1f));
		frameMat.setShininess(.2f);
		frameTa.setTextureMode(TextureAttributes.MODULATE);
		frameApp.setMaterial(frameMat);
		frameApp.setTextureAttributes(frameTa);
		frameApp.setTexture(frameTex);
		
		//frame made up of boxes
		
		Box top = new Box(2f,.1f,.05f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,frameApp);
		TransformGroup topTg = new TransformGroup();
		topTg.addChild(top);
		pictureTransform.setTranslation(new Vector3f(0f,7f,-7.5f));
		topTg.setTransform(pictureTransform);
		picture.addChild(topTg);
		
		Box bottom = new Box(2f,.1f,.05f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,frameApp);
		TransformGroup bottomTg = new TransformGroup();
		bottomTg.addChild(bottom);
		pictureTransform.setTranslation(new Vector3f(0f,2f,-7.5f));
		bottomTg.setTransform(pictureTransform);
		picture.addChild(bottomTg);
		
		Box left = new Box(.1f,2.4f,.05f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,frameApp);
		TransformGroup leftTg = new TransformGroup();
		leftTg.addChild(left);
		pictureTransform.setTranslation(new Vector3f(-1.9f,4.5f,-7.5f));
		leftTg.setTransform(pictureTransform);
		picture.addChild(leftTg);
		
		Box right = new Box(.1f,2.4f,.05f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,frameApp);
		TransformGroup rightTg = new TransformGroup();
		rightTg.addChild(right);
		pictureTransform.setTranslation(new Vector3f(1.9f,4.5f,-7.5f));
		rightTg.setTransform(pictureTransform);
		picture.addChild(rightTg);
		
		//pic itself is a quadarray
		
		QuadArray pic = new QuadArray(4,QuadArray.COORDINATES| GeometryArray.TEXTURE_COORDINATE_2);
		
		pic.setCoordinate(0,new Point3f(0f,0f,0f));
		pic.setCoordinate(1,new Point3f(3.6f,0f,0f));
		pic.setCoordinate(2,new Point3f(3.6f,4.8f,0f));
		pic.setCoordinate(3,new Point3f(0f,4.8f,0f));
		pic.setTextureCoordinate(0,new Point2f(0f,0f));
		pic.setTextureCoordinate(1,new Point2f(1f,0f));
		pic.setTextureCoordinate(2,new Point2f(1f,1f));
		pic.setTextureCoordinate(3,new Point2f(0f,1f));
		
		GeometryInfo picGI = new GeometryInfo(pic);	//normals need to be calculated for shading
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(picGI);
		GeometryArray picResult = picGI.getGeometryArray();
		
		Appearance picApp = new Appearance();
		TextureLoader picLoader = new TextureLoader (getClass().getResource("img/picture.jpg"), null);	//loads in our image
		Texture picTex = picLoader.getTexture ();
		TextureAttributes picTa = new TextureAttributes ();
		picTa.setTextureMode(TextureAttributes.MODULATE);
		picTa.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
		Material picMat = new Material();		//sets a material also to allow shadings
		picMat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		picMat.setSpecularColor(new Color3f(.1f,0.1f,.1f));
		picMat.setShininess(.2f);
		picApp.setMaterial(picMat);
		picApp.setTextureAttributes(picTa);
		picApp.setTexture(picTex);
		
		TransformGroup picTG = new TransformGroup();
		picTG.addChild(new Shape3D(picResult,picApp));
		pictureTransform.setTranslation(new Vector3f(-1.8f,2.1f,-7.45f));
		picTG.setTransform(pictureTransform);
		picture.addChild(picTG);
		
		return picture;
	}
}
