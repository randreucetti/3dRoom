/* Ross Andreucetti
 * 58432481
 * CASE 4
 */

import java.awt.GraphicsConfiguration;		//imports the packages we'll need
import java.awt.event.*;

import com.sun.j3d.utils.behaviors.mouse.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;


class CA417 extends MouseAdapter
{
		//for the universe
		SimpleUniverse universe;
		GraphicsConfiguration config;
		Canvas3D canvas;
		BranchGroup group;	//group which holds everything, table, jug lamp...
		TransformGroup mainTG;	//used for transforms on these objects
		Transform3D mainT3D;
		
		JFrame frame;		//frame scene will appear in
		JPanel panel;	//panel for our buttons
		
		JButton mainLightButton;	//buttons
		JButton lampButton;
		JButton fanButton;
		JButton tvButton;
		
		boolean mainLightOn;	//booleans for the buttons
		boolean lampOn;
		boolean fanOn;
		boolean tvOn;
		
		
		//the models
		Table table;
		Jug jug;
		Lamp lamp;
		Room room;
		Picture picture;
		Couch couch;
		Tv tv;
		Fan fan;
		//lights
		Color3f light1Color;
		BoundingSphere bounds;
		Vector3f light1Direction;
		DirectionalLight light1;
		//viewpoint
		ViewingPlatform vp;
		TransformGroup View_TransformGroup;
		Transform3D View_Transform3D;
		//view x y z values, will be modified in assignment 2
		float distance = 12.0f;
		float viewX = 0.0f;
		float viewY = 0.0f;
		//this method creates the universe
		void createUniverse()
		{
			config = SimpleUniverse.getPreferredConfiguration();
			canvas = new Canvas3D(config);
			universe = new SimpleUniverse(canvas);
			universe.getViewingPlatform().setNominalViewingTransform();
			group = new BranchGroup();	
			bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 50.0);
			mainT3D = new Transform3D();
			mainTG = new TransformGroup(); 
			mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		}
		//next, add directional white light so we can see
		void addLight()
		{
			light1Color = new Color3f(2.1f, 2.1f, 2.1f);
			light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
			light1 = new DirectionalLight(light1Color, light1Direction);
			light1.setInfluencingBounds(bounds);
			light1.setCapability(Light.ALLOW_COLOR_READ);
			light1.setCapability(Light.ALLOW_COLOR_WRITE);
			group.addChild(light1);
		}
		//point of view
		void setUpViewingPlatform()	//where our point of view will be
		{
			vp = universe.getViewingPlatform();
			View_TransformGroup = vp.getMultiTransformGroup().getTransformGroup(0);
			View_Transform3D = new Transform3D();
			View_Transform3D.setTranslation(new Vector3f(viewX, viewY, distance));  //remember, distance here has been predefined
			View_TransformGroup.setTransform(View_Transform3D); 
		}
		//adding models
		void addModels()	
		{
			table = new Table();
			jug = new Jug();
			lamp = new Lamp();
			room = new Room();
			picture = new Picture();
			couch = new Couch();
			tv = new Tv();
			fan = new Fan();
			
			mainTG.addChild(table.getTable());//adds items to transform group
			mainTG.addChild(jug.getJug());
			mainTG.addChild(lamp.getLamp());
			mainTG.addChild(room.getRoom());
			mainTG.addChild(picture.getPicture());
			mainTG.addChild(couch.getCouch());
			mainTG.addChild(tv.getTv());
			mainTG.addChild(fan.getFan());
			mainT3D.rotX(Math.PI/12);	//rotates for a cleared view
			mainTG.setTransform(mainT3D);
		}	
		void createGUI()		//creates the frame the scene wwill appear in
		{
			frame = new JFrame();
			panel = new JPanel();
			
			mainLightButton = new JButton("Main Light");
			lampButton = new JButton("Lamp");
			fanButton = new JButton("Fan");
			tvButton = new JButton("TV");
			
			mainLightButton.addActionListener(new mainLightListener());	//listeners for the buttons
			lampButton.addActionListener(new lampListener());
			fanButton.addActionListener(new fanListener());
			tvButton.addActionListener(new tvListener());
			mainLightOn = true;
			lampOn = false;
			fanOn = false;
			tvOn = false;
			
			panel.add(mainLightButton);
			panel.add(lampButton);
			panel.add(fanButton);
			panel.add(tvButton);
					
			frame.add("North",panel);
			frame.add("Center",canvas);
			frame.setSize(800, 800);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		class mainLightListener implements ActionListener	//turns on and off mainlight
		{
			public void actionPerformed(ActionEvent e)
			{
				if(mainLightOn)
				{
					mainLightOn = false;
					light1.setColor(new Color3f(0f,0f,0f));
				}
				else
				{
					mainLightOn = true;
					light1.setColor(new Color3f(2.1f, 2.1f, 2.1f));
				}
			}
		}
		
		class lampListener implements ActionListener	//on/off lamp
		{
			public void actionPerformed(ActionEvent e)
			{
				if(lampOn)
				{
					lampOn = false;
					lamp.deActivate();
				}
				else
				{
					lampOn = true;
					lamp.activate();
				}
			}
		}
		
		class fanListener implements ActionListener		//on/off fan
		{
			public void actionPerformed(ActionEvent e)
			{
				if(fanOn)
				{
					fanOn = false;
					fan.deActivate();
				}
				else
				{
					fanOn = true;
					fan.activate();
				}
			}
		}
		
		class tvListener implements ActionListener		//on/ off tv (put the sound on :) )
		{
			public void actionPerformed(ActionEvent e)
			{
				if(tvOn)
				{
					tvOn = false;
					tv.deActivate();
				}
				else
				{
					tvOn = true;
					tv.activate();
				}
			}
		}
		void addRotation()	//built in functions to allow rotation, panning etc.
		{
			MouseRotate behavior = new MouseRotate();
			behavior.setTransformGroup(mainTG);
			mainTG.addChild(behavior);
			behavior.setSchedulingBounds(bounds);
		}
		void addZoom()
		{
			MouseWheelZoom behavior = new MouseWheelZoom();
			behavior.setTransformGroup(mainTG);
			mainTG.addChild(behavior);
			behavior.setSchedulingBounds(bounds);
		}
		void addTranslation()
		{
			MouseTranslate behavior = new MouseTranslate();
			behavior.setTransformGroup(mainTG);
			mainTG.addChild(behavior);
			behavior.setSchedulingBounds(bounds);
		}
		
		void go()	//calls our previous methods
		{
			createUniverse();
			addLight();
			addModels();
			setUpViewingPlatform();
			createGUI();
			//adding things to the group
			group.addChild(mainTG);
			addRotation();
			addZoom();
			addTranslation();
			// add the group of objects to the Universe
			universe.addBranchGraph(group);	
			
		}
		public static void main(String [] args)	//main class
		{
			CA417 c = new CA417();
			c.go();
		}		
}