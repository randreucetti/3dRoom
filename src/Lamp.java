/* Ross Andreucetti
 * 58432481
 * CASE 4
 */
import javax.media.j3d.*;
import javax.vecmath.*;


import com.sun.j3d.utils.geometry.*;


public class Lamp 
{
	Transform3D lampTransform; //used to transform lamp parts
	TransformGroup lamp;  //a single group for the entire lamp
	PointLight p;
	
	public Lamp()
	{
		lampTransform =  new Transform3D();
		lamp = new TransformGroup();
	}
	public TransformGroup getLamp()
	{
		Appearance baseApp = new Appearance();		//appearance for base of lamp, shiny pink polished look
		Material baseMat = new Material();
		baseMat.setDiffuseColor(new Color3f(.5f,0f,.5f));
		baseMat.setSpecularColor(new Color3f(2f,0.72f,0.7f));
		baseMat.setShininess(46f);
		baseApp.setMaterial(baseMat); 
		
		//base of the lamp cone
		Cone base = new Cone(.35f,.5f);
		base.setAppearance(baseApp);
		TransformGroup baseTG = new TransformGroup();
		baseTG.addChild(base);
		lampTransform.setTranslation(new Vector3f(.8f,.35f,0f));
		baseTG.setTransform(lampTransform);
		lamp.addChild(baseTG);
		
		//main body of lamp sphere
		Sphere body = new Sphere(.4f);
		body.setAppearance(baseApp);
		TransformGroup bodyTG = new TransformGroup();
		bodyTG.addChild(body);
		lampTransform.setTranslation(new Vector3f(.8f,.5f,0f));
		bodyTG.setTransform(lampTransform);
		lamp.addChild(bodyTG);
		
		//neck of lamp
		Cylinder neck = new Cylinder(.11f,.2f);
		neck.setAppearance(baseApp);
		TransformGroup neckTG = new TransformGroup();
		neckTG.addChild(neck);
		lampTransform.setTranslation(new Vector3f(.8f,.95f,0f));
		neckTG.setTransform(lampTransform);
		lamp.addChild(neckTG);
		
		Appearance bulbApp = new Appearance();		//appearance for body of bulb, glass
		Material bulbMat = new Material();
		bulbMat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		bulbMat.setSpecularColor(new Color3f(.9f,0.9f,.9f));
		bulbMat.setShininess(.2f);
		PolygonAttributes pa = new PolygonAttributes();
	    pa.setCullFace(PolygonAttributes.CULL_NONE);
	    //bulbApp.setPolygonAttributes(pa);
		TransparencyAttributes taBulb = new TransparencyAttributes();	//slightly transparent
		taBulb.setTransparencyMode (TransparencyAttributes.BLENDED);
		taBulb.setTransparency (.5f);
		bulbApp.setTransparencyAttributes (taBulb);
		bulbApp.setMaterial(bulbMat); 
		
		//bulbbody
		Sphere bulbBody = new Sphere(.18f);	//main part of bulb
		bulbBody.setAppearance(bulbApp);
		TransformGroup bulbBodyTG = new TransformGroup();
		bulbBodyTG.addChild(bulbBody);
		lampTransform.setTranslation(new Vector3f(.8f,1.25f,0f));
		bulbBodyTG.setTransform(lampTransform);
		lamp.addChild(bulbBodyTG);
		
		Appearance bulbNeckApp = new Appearance();		//appearance for neck of bulb, copper
		Material bulbNeckMat = new Material();
		bulbNeckMat.setDiffuseColor(new Color3f(.30f,.1f,0f));
		bulbNeckMat.setSpecularColor(new Color3f(.75f,0.3f,0f));
		bulbNeckMat.setShininess(10f);
		bulbNeckApp.setMaterial(bulbNeckMat); 
		
		//base of bulb
		Cylinder bulbBase = new Cylinder(.07f,.15f);
		bulbBase.setAppearance(bulbNeckApp);
		TransformGroup bulbBaseTG = new TransformGroup();
		bulbBaseTG.addChild(bulbBase);
		lampTransform.setTranslation(new Vector3f(.8f,1.1f,0f));
		bulbBaseTG.setTransform(lampTransform);
		lamp.addChild(bulbBaseTG);
		
		
		//quadarray lampshade
		QuadArray shade = new QuadArray(16,QuadArray.COORDINATES);	//shade is made up of 4 quadsa
		
		Point3f p1 = new Point3f(0f,0f,0f);
		Point3f p2 = new Point3f(1f,0f,0f);
		Point3f p3 = new Point3f(.8f,.8f,.2f);
		Point3f p4 = new Point3f(.2f,.8f,.2f);
		Point3f p5 = new Point3f(0f,0f,1f);
		Point3f p6 = new Point3f(1f,0f,1f);
		Point3f p7 = new Point3f(.8f,.8f,.8f);
		Point3f p8 = new Point3f(.2f,.8f,.8f);
		
		shade.setCoordinate(0,p1);
		shade.setCoordinate(1,p2);
		shade.setCoordinate(2,p3);
		shade.setCoordinate(3,p4);
		
		shade.setCoordinate(4,p3);
		shade.setCoordinate(5,p2);
		shade.setCoordinate(6,p6);
		shade.setCoordinate(7,p7);
		
		shade.setCoordinate(8,p6);
		shade.setCoordinate(9,p7);
		shade.setCoordinate(10,p8);
		shade.setCoordinate(11,p5);
		
		shade.setCoordinate(12,p8);
		shade.setCoordinate(13,p4);
		shade.setCoordinate(14,p1);
		shade.setCoordinate(15,p5);
		
		GeometryInfo gi = new GeometryInfo(shade);	//normals need to be calculated for shading
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		GeometryArray shadeResult = gi.getGeometryArray();
	
		Appearance shadeApp = new Appearance();		//appearance for the shade, green
		Material shadeMat = new Material();
		shadeMat.setDiffuseColor(new Color3f(.1f,.1f,0f));
		shadeMat.setSpecularColor(new Color3f(0f,0.75f,.3f));
		shadeMat.setShininess(10f);
		shadeApp.setMaterial(shadeMat); 
	    shadeApp.setPolygonAttributes(pa);
		TransparencyAttributes taShade = new TransparencyAttributes();	//slightly transparent for a canvas feel
		taShade.setTransparencyMode(TransparencyAttributes.BLENDED);
		taShade.setTransparency (.2f);
		shadeApp.setTransparencyAttributes (taShade);
		
		TransformGroup shadeTG = new TransformGroup();	
		shadeTG.addChild(new Shape3D(shadeResult,shadeApp));
		lampTransform.setTranslation(new Vector3f(.3f,1f,-.5f));
		shadeTG.setTransform(lampTransform);
		lamp.addChild(shadeTG);
		
		lampTransform.setTranslation(new Vector3f(5.5f,0f,-5.5f));
		lamp.setTransform(lampTransform);
		
		p = new PointLight(new Color3f (1.0f, 1.0f, 1.0f),new Point3f(.8f,1.4f,0f),new Point3f (.5f, 0f,0f));
		p.setInfluencingBounds(new BoundingSphere());
		p.setCapability(PointLight.ALLOW_POSITION_WRITE);
		p.setCapability(PointLight.ALLOW_COLOR_WRITE);
		TransformGroup pTG = new TransformGroup();
		pTG.addChild(p);
		lamp.addChild(pTG);
		
		return lamp;
	}
	public void activate()
	{
		p.setColor(new Color3f (1.0f, 1.0f, 1.0f));
	}
	public void deActivate()
	{
		p.setColor(new Color3f (0f,0f,0f));
	}
}
