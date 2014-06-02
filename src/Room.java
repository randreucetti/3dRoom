/* Ross Andreucetti
 * 58432481
 * CASE 4
 */

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.image.TextureLoader;


public class Room //our room
{
	Transform3D roomTransform;	
	TransformGroup room;
	public Room()
	{
		roomTransform = new Transform3D();
		room = new TransformGroup();
	}
	@SuppressWarnings("deprecation")
	public TransformGroup getRoom()
	{
		
		Point3f p1 = new Point3f(0f,0f,0f);	//points of our room
		Point3f p2 = new Point3f(0f,0f,15f);
		Point3f p3 = new Point3f(15,0f,15f);
		Point3f p4 = new Point3f(15f,0f,0f);
		Point3f p5 = new Point3f(0f,12f,0f);
		Point3f p6 = new Point3f(0f,12f,15f);
		Point3f p7 = new Point3f(15f,12f,15f);
		Point3f p8 = new Point3f(15f,12f,0f);
		
		
		//quad arry for our floor
		
		
		QuadArray floor = new QuadArray(4,QuadArray.COORDINATES| GeometryArray.TEXTURE_COORDINATE_2);
		floor.setCoordinate(0,p1);
		floor.setCoordinate(1,p2);
		floor.setCoordinate(2,p3);
		floor.setCoordinate(3,p4);
		floor.setTextureCoordinate(0,new Point2f(0f,0f));
		floor.setTextureCoordinate(1,new Point2f(8f,0f));
		floor.setTextureCoordinate(2,new Point2f(8f,8f));
		floor.setTextureCoordinate(3,new Point2f(0f,8f));
		
		GeometryInfo floorGI = new GeometryInfo(floor);	//normals need to be calculated for shading
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(floorGI);
		GeometryArray floorResult = floorGI.getGeometryArray();
		
		
		//carpet texture for floor
		Appearance floorApp = new Appearance();
		TextureLoader floorLoader = new TextureLoader (getClass().getResource("img/carpet.jpg"), null);	//loads in our image
		Texture floorTex = floorLoader.getTexture ();
		TextureAttributes floorTa = new TextureAttributes ();
		floorTa.setTextureMode(TextureAttributes.MODULATE);
		Material roomMat = new Material();
		roomMat.setDiffuseColor(new Color3f(.30f,.3f,.3f));
		roomMat.setSpecularColor(new Color3f(.1f,0.1f,.1f));
		roomMat.setShininess(.2f);
		floorApp.setMaterial(roomMat);
		floorApp.setTextureAttributes(floorTa);
		floorApp.setTexture(floorTex);
		
		TransformGroup floorTG = new TransformGroup();
		floorTG.addChild(new Shape3D(floorResult,floorApp));
		roomTransform.setTranslation(new Vector3f(-7.5f,-3.4f,-7.5f));
		floorTG.setTransform(roomTransform);
		room.addChild(floorTG);
		//for our walls
		
		QuadArray walls = new QuadArray(16,QuadArray.COORDINATES| GeometryArray.TEXTURE_COORDINATE_2);
		
		walls.setCoordinate(0,p5);
		walls.setCoordinate(1,p1);
		walls.setCoordinate(2,p4);
		walls.setCoordinate(3,p8);
		walls.setTextureCoordinate(0,new Point2f(0f,0f));
		walls.setTextureCoordinate(1,new Point2f(2f,0f));
		walls.setTextureCoordinate(2,new Point2f(2f,2f));
		walls.setTextureCoordinate(3,new Point2f(0f,2f));
		
		walls.setCoordinate(4,p7);
		walls.setCoordinate(5,p8);
		walls.setCoordinate(6,p4);
		walls.setCoordinate(7,p3);
		walls.setTextureCoordinate(4,new Point2f(0f,0f));
		walls.setTextureCoordinate(5,new Point2f(2f,0f));
		walls.setTextureCoordinate(6,new Point2f(2f,2f));
		walls.setTextureCoordinate(7,new Point2f(0f,2f));
		
		walls.setCoordinate(8,p2);
		walls.setCoordinate(9,p6);
		walls.setCoordinate(10,p7);
		walls.setCoordinate(11,p3);
		walls.setTextureCoordinate(8,new Point2f(0f,0f));
		walls.setTextureCoordinate(9,new Point2f(2f,0f));
		walls.setTextureCoordinate(10,new Point2f(2f,2f));
		walls.setTextureCoordinate(11,new Point2f(0f,2f));
		
		walls.setCoordinate(12,p2);
		walls.setCoordinate(13,p1);
		walls.setCoordinate(14,p5);
		walls.setCoordinate(15,p6);
		walls.setTextureCoordinate(12,new Point2f(0f,0f));
		walls.setTextureCoordinate(13,new Point2f(2f,0f));
		walls.setTextureCoordinate(14,new Point2f(2f,2f));
		walls.setTextureCoordinate(15,new Point2f(0f,2f));
		
		GeometryInfo wallsGI = new GeometryInfo(walls);	//normals need to be calculated for shading
		ng.generateNormals(wallsGI);
		GeometryArray wallsResult = wallsGI.getGeometryArray();
		
		Appearance wallApp = new Appearance();
		TextureLoader wallLoader = new TextureLoader (getClass().getResource("img/wallpaper.jpg"), null);	//loads in our image
		Texture wallTex = wallLoader.getTexture ();
		TextureAttributes wallTa = new TextureAttributes ();
		wallTa.setTextureMode(TextureAttributes.MODULATE);
		wallApp.setMaterial(roomMat);
		wallApp.setTextureAttributes(wallTa);
		wallApp.setTexture(wallTex);
		
		TransformGroup wallsTG = new TransformGroup();
		wallsTG.addChild(new Shape3D(wallsResult,wallApp));
		wallsTG.setTransform(roomTransform);
		room.addChild(wallsTG);
		
		//our ceiling
		QuadArray ceiling = new QuadArray(4,QuadArray.COORDINATES| GeometryArray.TEXTURE_COORDINATE_2);
		
		ceiling.setCoordinate(0,p8);
		ceiling.setCoordinate(1,p7);
		ceiling.setCoordinate(2,p6);
		ceiling.setCoordinate(3,p5);
		ceiling.setTextureCoordinate(0,new Point2f(0f,0f));
		ceiling.setTextureCoordinate(1,new Point2f(8f,0f));
		ceiling.setTextureCoordinate(2,new Point2f(8f,8f));
		ceiling.setTextureCoordinate(3,new Point2f(0f,8f));
		
		GeometryInfo ceilingGI = new GeometryInfo(ceiling);	//normals need to be calculated for shading
		ng.generateNormals(ceilingGI);
		GeometryArray ceilingResult = ceilingGI.getGeometryArray();
		
		Appearance ceilingApp = new Appearance();
		TextureLoader ceilingLoader = new TextureLoader (getClass().getResource("img/ceiling.jpg"), null);	//loads in our image
		Texture ceilingTex = ceilingLoader.getTexture ();
		TextureAttributes ceilingTa = new TextureAttributes ();
		ceilingTa.setTextureMode(TextureAttributes.MODULATE);
		ceilingApp.setTexture(ceilingTex);
		ceilingApp.setTextureAttributes(ceilingTa);
		ceilingApp.setMaterial(roomMat);
		
		TransformGroup ceilingTG = new TransformGroup();
		ceilingTG.addChild(new Shape3D(ceilingResult,ceilingApp));
		ceilingTG.setTransform(roomTransform);
		room.addChild(ceilingTG);
		
		
		return room;
	}
}
