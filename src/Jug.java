/* Ross Andreucetti
 * 58432481
 * CASE 4
 */

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;


public class Jug //our jug class to create a stainless steel jug with lid
{
	Transform3D jugTransform;	//for moving parts of jug
	TransformGroup jug;
	public Jug()
	{
		jugTransform = new Transform3D();
		jug = new TransformGroup();
	}
	public TransformGroup getJug()
	{
		Appearance jugApp = new Appearance();		//appearance for bulk of jug, stainless steel
		TextureLoader loader = new TextureLoader (getClass().getResource("img/steel.jpg"), null);	//imports our texture
		Texture tex = loader.getTexture ( );
		TextureAttributes ta = new TextureAttributes ();
		ta.setTextureMode(TextureAttributes.MODULATE);
		jugApp.setTexture(tex);
		Material jugMat = new Material();
		jugMat.setDiffuseColor(new Color3f(.37f,.37f,.37f));	//appleis a material too
		jugMat.setSpecularColor(new Color3f(.89f,0.89f,.89f));
		jugMat.setShininess(17f);
		jugApp.setTextureAttributes(ta);
		jugApp.setMaterial(jugMat); 
		
		Cone base = new Cone(.5f,.8f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,jugApp);	//cones base of the jug
		TransformGroup baseTG = new TransformGroup();
		baseTG.addChild(base);
		jugTransform.setTranslation(new Vector3f(-.8f,.5f,0f));
		baseTG.setTransform(jugTransform);
		jug.addChild(baseTG);
		
		Cylinder body = new Cylinder(.37f,.8f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,jugApp); //cylinder makes up main part of jug
		TransformGroup bodyTG = new TransformGroup();
		bodyTG.addChild(body);
		jugTransform.setTranslation(new Vector3f(-.8f,.7f,0f));
		bodyTG.setTransform(jugTransform);
		jug.addChild(bodyTG);
		
		Appearance lidApp = new Appearance();		//appearance for lid, dark green plastic
		Material lidMat = new Material();
		lidMat.setDiffuseColor(new Color3f(.0f,.1f,0f));
		lidMat.setSpecularColor(new Color3f(.85f,.85f,.85f));
		lidMat.setShininess(30f);
		lidApp.setMaterial(lidMat); 
		
		Cone lid = new Cone(.37f,.15f);		//lid is a short cone
		lid.setAppearance(lidApp);
		TransformGroup lidTG = new TransformGroup();
		lidTG.addChild(lid);
		jugTransform.setTranslation(new Vector3f(-.8f,1.175f,0f));
		lidTG.setTransform(jugTransform);
		jug.addChild(lidTG);
		
		Sphere knob = new Sphere(.1f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,jugApp);	//steel knob on top
		TransformGroup knobTG = new TransformGroup();
		knobTG.addChild(knob);
		jugTransform.setTranslation(new Vector3f(-.8f,1.28f,0f));
		knobTG.setTransform(jugTransform);
		jug.addChild(knobTG);
		
		TriangleArray spoutGeo = new TriangleArray(6,TriangleArray.COORDINATES);		//spout is a simple triangle array
		Point3f p1 = new Point3f(0f,0f,0f);
		Point3f p2 = new Point3f(.2f,0f,-.1f);
		Point3f p3 = new Point3f(.2f,0f,.1f);
		Point3f p4 = new Point3f(.1f,-.2f,0f);
		
		spoutGeo.setCoordinate(0,p1);
		spoutGeo.setCoordinate(1,p4);
		spoutGeo.setCoordinate(2,p2);
		
		spoutGeo.setCoordinate(3,p1);
		spoutGeo.setCoordinate(4,p4);
		spoutGeo.setCoordinate(5,p3);
		
		GeometryInfo geometryInfo = new GeometryInfo(spoutGeo);		//normals for appearance
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(geometryInfo);
		GeometryArray spoutResult = geometryInfo.getGeometryArray();
		
		TransformGroup spoutTG = new TransformGroup();
		spoutTG.addChild(new Shape3D(spoutResult,jugApp));
		jugTransform.setTranslation(new Vector3f(-1.27f,1.1f,0f));
		spoutTG.setTransform(jugTransform);
		jug.addChild(spoutTG);
		
		//handle 3 parts
		
		Box handle1 = new Box(0.12f,.02f,.03f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,jugApp);	//top part
		TransformGroup handle1TG = new TransformGroup();
		handle1TG.addChild(handle1);
		jugTransform.setTranslation(new Vector3f(-.35f,1f,0f));
		handle1TG.setTransform(jugTransform);
		jug.addChild(handle1TG);
		
		Cylinder handle3 = new Cylinder(0.02f,.45f,Primitive.GENERATE_NORMALS+Primitive.GENERATE_TEXTURE_COORDS,jugApp); //connects the two
		TransformGroup handle3TG = new TransformGroup();
		handle3TG.addChild(handle3);
		handle3TG.setTransform(jugTransform);
		jugTransform.setTranslation(new Vector3f(-.3f,.8f,0f));
		handle3TG.setTransform(jugTransform);
		jug.addChild(handle3TG);
		
		jugTransform.setTranslation(new Vector3f(5.5f,0f,-5.5f));
		jug.setTransform(jugTransform);
		
		return jug;	//returns our completed jug
	}
}
