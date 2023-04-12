package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.commands.AboutCommand;
import com.mycompany.commands.AccelerateCommand;
import com.mycompany.commands.BaseCollisionCommand;
import com.mycompany.commands.BrakeCommand;
import com.mycompany.commands.ChangeStrategyCommand;
import com.mycompany.commands.ClockTickCommand;
import com.mycompany.commands.DroneCollisionCommand;
import com.mycompany.commands.EnergyStationCollisionCommand;
import com.mycompany.commands.HelpButtonCommand;
import com.mycompany.commands.LeftTurnCommand;
import com.mycompany.commands.NPRCollisionCommand;
import com.mycompany.commands.QuitCommand;
import com.mycompany.commands.RightTurnCommand;
import com.mycompany.commands.SoundCommand;

public class Game extends Form {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private AccelerateCommand ac;
	private LeftTurnCommand left;
	private ChangeStrategyCommand cs;
	private BrakeCommand b;
	private RightTurnCommand right;
	private NPRCollisionCommand nprc;
	private BaseCollisionCommand bcc;
	private EnergyStationCollisionCommand escc;
	private DroneCollisionCommand dcc;
	private ClockTickCommand ct;
	
	// Initialize all objects needed 
	public Game() {
		this.setLayout(new BorderLayout());
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		ac = new AccelerateCommand(gw, "Accelerate");
		left = new LeftTurnCommand(gw, "Left");
		cs = new ChangeStrategyCommand(gw, "Change Strategies");
		b = new BrakeCommand(gw, "Brake");
		right = new RightTurnCommand(gw, "Right");
		nprc = new NPRCollisionCommand(gw, "NonPlayerRobot Collision");
		bcc = new BaseCollisionCommand(gw, "Base Collision");
		escc = new EnergyStationCollisionCommand(gw, "Energy Station Collision");
		dcc = new DroneCollisionCommand(gw, "Drone Collision");
		ct = new ClockTickCommand(gw, "Clock Tick");
//		s = new SoundCommand(gw, "Toggle Sound");
		
//		this.getAllStyles().setFont();
		this.add(BorderLayout.NORTH, sv);
		this.add(BorderLayout.CENTER, mv);
		topDiv();
		setLeftMenu();
		setRightMenu();
		setBottomMenu();
		
		this.show();
		
		gw.init();
	}
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
		SoundCommand sound = new SoundCommand(gw, "Toggle Sound");
		myToolBar.addCommandToLeftSideMenu(sound);
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
		Button nprCollision = new Button(nprc);
		nprCollision = styles(nprCollision);
		Button baseCollision = new Button(bcc);
		baseCollision = styles(baseCollision);
		Button esCollision = new Button(escc);
		esCollision = styles(esCollision);
		addKeyListener('e', escc);
		Button droneCollision = new Button(dcc);
		droneCollision = styles(droneCollision);
		addKeyListener('g',dcc);
		Button tick = new Button(ct);
		tick = styles(tick);
		addKeyListener('t', ct);
		bottom.add(nprCollision);
		bottom.add(baseCollision);
		bottom.add(esCollision);
		bottom.add(droneCollision);
		bottom.add(tick);
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
}
