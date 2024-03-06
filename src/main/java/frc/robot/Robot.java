// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.drive;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.climb;
import frc.robot.subsystems.shooter;

public class Robot extends TimedRobot {

  private static Joystick m_stick = new Joystick(0);
  private final drive m_drive = new drive();
  private final climb m_climb = new climb();
  private final shooter m_shooter = new shooter();
  private final limelight m_Limelight = new limelight();
  private final Timer m_timer = new Timer();
  static int m_stage;
  @Override

  public void robotInit() {
    m_drive.init();
    m_climb.init();
    m_shooter.init();
  }
  @Override
  public void teleopPeriodic() {
    final double stickY = -m_stick.getY();
    final double stickX = -m_stick.getX();
    
    if(m_stick.getRawButton(4) == false){
      m_drive.arcadeDrive(stickY,stickX);}
    if(m_stick.getRawButton(4) == true){
      m_drive.arcadeDrive(stickY,m_Limelight.Turn_pid.calculate(m_Limelight.getX(), 0));}    
    m_climb.setMotor(m_stick.getPOV());
    m_shooter.setMotor(m_stick.getRawButtonPressed(1),m_stick.getRawButtonPressed(2),m_stick.getRawButtonPressed(3));
  }
  public void autonomousInit() {
    m_timer.start();
    m_stage = 1;
  }
  public void autonomousPeriodic() {
    switch(m_stage){
      case 1:
        m_shooter.setMotor(true, false, false);
        if(m_timer.get() > 3){
          m_shooter.setMotor(false, false, true);
          m_stage++;
          m_timer.restart();
        }
        break;
      case 2:
        m_drive.arcadeDrive(-1,0);
        if(m_timer.get() > 3){
          m_drive.arcadeDrive(0,0);
          m_stage++;
          m_timer.restart();
        }
        break;
    }

  }
}
