package com.mycompany.a3;
import java.io.InputStream;

import com.codename1.charts.util.ColorUtil;
import com.codename1.media.MediaManager;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Container;
import com.codename1.ui.CheckBox;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.mycompany.commands.AboutCommand;
import com.mycompany.commands.AccelerateCommand;
//import com.mycompany.commands.BaseCollisionCommand;
import com.mycompany.commands.BrakeCommand;
import com.mycompany.commands.ChangeStrategyCommand;
//import com.mycompany.commands.ClockTickCommand;
//import com.mycompany.commands.DroneCollisionCommand;
//import com.mycompany.commands.EnergyStationCollisionCommand;
import com.mycompany.commands.HelpButtonCommand;
import com.mycompany.commands.LeftTurnCommand;
import com.mycompany.commands.PauseCommand;
import com.mycompany.commands.PositionCommand;
//import com.mycompany.commands.NPRCollisionCommand;
import com.mycompany.commands.QuitCommand;
import com.mycompany.commands.RightTurnCommand;
//import com.mycompany.commands.SoundCommand;
import com.mycompany.commands.SoundCommand;
import com.codename1.media.Media;

//import javafx.scene.media.Media;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private AccelerateCommand ac;
	private LeftTurnCommand left;
	private ChangeStrategyCommand cs;
	private BrakeCommand b;
	private RightTurnCommand right;
	private CheckBox cb;
	private PauseCommand pc;
	private PositionCommand posC;
//	private NPRCollisionCommand nprc;
//	private BaseCollisionCommand bcc;
//	private EnergyStationCollisionCommand escc;
//	private DroneCollisionCommand dcc;
//	private SoundCommand s;
//	private Media m;
	private int mvWidth;
	private int mvHeight;
//	private ClockTickCommand ct;
	public static boolean toggleSound;
	public int elapsedTime;
//	private BGSound bgS;
//	private int mapHeight;
//	private int mapWidth;
//	private boolean soundOn;
	
	// Initialize all objects needed 
	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		this.elapsedTime = 20;
//		this.soundOn = false;
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		ac = new AccelerateCommand(gw, "Accelerate");
		left = new LeftTurnCommand(gw, "Left");
		cs = new ChangeStrategyCommand(gw, "Change Strategies");
		b = new BrakeCommand(gw, "Brake");
		right = new RightTurnCommand(gw, "Right");
		pc = new PauseCommand(gw, "Pause");
		posC = new PositionCommand(gw, "Position");
//		nprc = new NPRCollisionCommand(gw, "NonPlayerRobot Collision");
//		bcc = new BaseCollisionCommand(gw, "Base Collision");
//		escc = new EnergyStationCollisionCommand(gw, "Energy Station Collision");
//		dcc = new DroneCollisionCommand(gw, "Drone Collision");
//		ct = new ClockTickCommand(gw, "Clock Tick");
//		s = new SoundCommand(gw, "Toggle Sound");
		
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);

		topDiv();
		setLeftMenu();
		setRightMenu();
		setBottomMenu();
		
		this.show();
		this.mvWidth = mv.getWidth();
		this.mvHeight = mv.getHeight();
		GameWorld.setMVWidth(mvWidth);
		GameWorld.setMVHeight(mvHeight);
//		BGSound bgS = new BGSound("bgSound.mp3");
//		bgS.play();

		gw.init();
		this.timer();
		gw.createSounds();
		revalidate();
	}
	
//	public void createSounds() {
//		BGSound bgS = new BGSound("bgSound.mp3");
//		bgS.play();
//	}
//	public boolean getSound()

	/*
	 * Adding commands to the Toolbar at the top of page
	 * */
	private void topDiv() {
		Toolbar myToolBar = new Toolbar();
		setToolbar(myToolBar);
		myToolBar.setTitle("Robot Game");
		QuitCommand quit = new QuitCommand("Quit Game");
		myToolBar.addCommandToSideMenu(quit);
		addKeyListener('x', quit);
		cb = new CheckBox();
		SoundCommand s = new SoundCommand(gw, "Toggle Sound");
		cb.setCommand(s);
		myToolBar.addComponentToLeftSideMenu(cb);
		AboutCommand about = new AboutCommand("About");
		myToolBar.addCommandToLeftSideMenu(about);
		HelpButtonCommand helpBtn = new HelpButtonCommand("Help");
		myToolBar.addCommandToRightBar(helpBtn);
	}
	/*
	 * Adding buttons to left side of screen 
	 * */
	private void setLeftMenu() {
		Container leftSide = new Container();
		leftSide.getAllStyles().setMargin(TOP, 100);
		leftSide.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Button accelerateBtn = new Button(ac);
		accelerateBtn = styles(accelerateBtn); 
		addKeyListener('a', ac);
		Button turnLeftBtn = new Button(left);
		turnLeftBtn = styles(turnLeftBtn);
		addKeyListener('l', left);
		Button changeStratBtn = new Button(cs);
		changeStratBtn = styles(changeStratBtn);
		addKeyListener('c', cs);
		leftSide.add(accelerateBtn).add(turnLeftBtn).add(changeStratBtn);
		
		this.add(BorderLayout.WEST, leftSide);
		
	}
	/*
	 * Adding buttons to right side of screen
	 * */
	private void setRightMenu() {
		Container rightSide = new Container();
		rightSide.getAllStyles().setMargin(TOP, 100);
		rightSide.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		Button brakeBtn = new Button(b);
		brakeBtn = styles(brakeBtn);
		addKeyListener('b', b);
		Button turnRightBtn = new Button(right);
		turnRightBtn = styles(turnRightBtn);
		addKeyListener('r', right);
		rightSide.add(brakeBtn);
		rightSide.add(turnRightBtn);
		this.add(BorderLayout.EAST, rightSide);
	}
	/*
	 * Adding buttons to bottom of screen
	 * */
	private void setBottomMenu() {
		Container bottom = new Container();
		bottom.setLayout(new FlowLayout(Component.CENTER));
		Button pauseBtn = new Button(pc);
		pauseBtn = styles(pauseBtn);
		bottom.add(pauseBtn);
		Button posBtn = new Button(posC);
		posBtn = styles(posBtn);
		bottom.add(posBtn);
//		Button nprCollision = new Button(nprc);
//		nprCollision = styles(nprCollision);
//		Button baseCollision = new Button(bcc);
//		baseCollision = styles(baseCollision);
//		Button esCollision = new Button(escc);
//		esCollision = styles(esCollision);
//		addKeyListener('e', escc);
//		Button droneCollision = new Button(dcc);
//		droneCollision = styles(droneCollision);
//		addKeyListener('g',dcc);
//		Button tick = new Button(ct);
//		tick = styles(tick);
//		addKeyListener('t', ct);
//		bottom.add(nprCollision);
//		bottom.add(baseCollision);
//		bottom.add(esCollision);
//		bottom.add(droneCollision);
//		bottom.add(tick);
		this.add(BorderLayout.SOUTH, bottom);
	}
	/*
	 * Function to pass a button in and return the button with a style
	 * */
	private Button styles(Button b) {
		b.getAllStyles().setBgTransparency(255);
		b.getAllStyles().setBgColor(ColorUtil.rgb(43, 135, 63));
		b.getAllStyles().setBorder(Border.createLineBorder(2));
		b.getAllStyles().setFgColor(ColorUtil.BLACK);
		b.getAllStyles().setPadding(5, 5, 1, 1); // t, b, l, r
//		b.getAllStyles().setMargin(1, 1, 1, 1);
		return b;
	}
	
	// Game timer
	public void timer() {	
		UITimer timer = new UITimer(this);
		timer.schedule(elapsedTime, true, this);
		run();
//		if(!gw.getPause()) {
//		run();
//	}
	}
	public void run() {
		if(!gw.getPause()) {
			gw.clockTick(elapsedTime);
		}
	}
//	public int getMVWidth() {
//		return mvWidth;
//	}
//	public int getElapsedTime() {
//		return elapsedTime;
//	}
}
