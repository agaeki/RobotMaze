import uk.ac.warwick.dcs.maze.logic.IRobot;
import clojure.lang.RT;
import clojure.lang.Var;

public class Explorer {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		System.out.println("Thingy");
		controlRobot(null);
	}
	
	public static void controlRobot(IRobot robot) {
		 try {
			RT.loadResourceScript("CljExplorer.clj");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 Var nonWallExits = RT.var("Explorer", "nonWallExits");
		 Var deadEnd = RT.var("Explorer", "deadEnd");
		 Var corridor = RT.var("Explorer", "corridor");
		 Var junction = RT.var("Explorer", "junction");
		 
		 try {
			System.out.println(nonWallExits.invoke(robot) + "\n" + deadEnd.invoke(robot) + "\n" + corridor.invoke(robot) + "\n" + junction.invoke(robot));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
