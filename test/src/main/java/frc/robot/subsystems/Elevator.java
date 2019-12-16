package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	OI oi;

	public void moveUp() {
		// Moves the motors to grab ball
		double speed = oi.getElevatorSpeed();
		if (speed < 0.0) {
			speed = 0.0;
		}

		if (RobotMap.limitSwitchTop.get() == true) {
			RobotMap.Winch.set(0.0);
		} else {
			RobotMap.Winch.set(speed);
		}
	}

	public void moveDown() {

		double speed = oi.getElevatorSpeed();
		if (speed > 0.0) {
			speed = 0.0;
		}
		if (RobotMap.limitSwitchBottom.get() == true) {
			RobotMap.Winch.set(0.0);
		} else {
			RobotMap.Winch.set(speed);
		}
	}

	//Moves winch motor based on speed values from OI
	public void moveElevator(double speed) {
		SmartDashboard.putBoolean("limitSwitchTop ", RobotMap.limitSwitchTop.get());
		SmartDashboard.putBoolean("limitSwitchBottom ", RobotMap.limitSwitchBottom.get());

		//limit switches are FALSE when pressed, True when NOT pressed

		//speed from joystick seems to be reversed...therefore:
		speed=speed*-1.0;
		//motor going too fast, so half the speed
		speed=speed*0.8;
		//Going up
		if (speed > 0) {
			boolean top = RobotMap.limitSwitchTop.get();
			if (top == false) {
				// do nothing
			} else {
				RobotMap.Winch.set(speed);
			}
		}
		//Going down
		else if (speed < 0) {
			boolean bot = RobotMap.limitSwitchBottom.get();
			if (bot == false) {
				// do nothing
			} else {
				RobotMap.Winch.set(speed*0.5);
			}
		}
		//Not moving, stops motor
		else {
			RobotMap.Winch.set(0.0);
		}
	}

	public void stop() {
		RobotMap.Winch.set(0.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MoveElevator());
	}
}
