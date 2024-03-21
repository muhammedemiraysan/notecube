// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.sql.Driver;
import java.util.Optional;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.subsystems.drive;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.climb;
import frc.robot.subsystems.shooter;
import frc.robot.subsystems.led;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
public class Robot extends TimedRobot {

  private static Joystick m_stick = new Joystick(0);
  private final drive m_drive = new drive();
  private final climb m_climb = new climb();
  private final shooter m_shooter = new shooter();
  private static limelight m_Limelight = new limelight();
  private final led m_led = new led();
  private final Timer m_timer = new Timer();
  private final Timer gyro_timer = new Timer();
  static int m_stage;
  static boolean autonomous_shooter = false;
  static boolean autonomous_stopShooter = false;
  @Override
  public void disabledPeriodic(){

      Optional<Alliance> ally = DriverStation.getAlliance();
      if (ally.isPresent()) {
          if (ally.get() == Alliance.Red) {
            m_led.anim(255,0,0,0,48);
          }
          if (ally.get() == Alliance.Blue) {
            m_led.anim(0,0,255,0,48);
          }
    }   
  }
  public void robotInit() {
    m_drive.init();
    m_climb.init();
    m_shooter.init();
    gyro_timer.start();
    m_led.init();
  }
  @Override
  public void teleopInit(){
    m_led.onecolor(255,0,0,0,90);
  }
  public void teleopPeriodic() {
    SmartDashboard.putNumber("sure: ", DriverStation.getMatchTime());
    if(m_shooter.Upshooterspeed == 1){
      m_led.onecolor(255, 100, 0,48,96);
    }
    if(m_shooter.Upshooterspeed == -1){
      m_led.onecolor(255, 255, 85,48,96);
    }
    if(m_shooter.Upshooterspeed == 0){
      m_led.onecolor(255, 255, 85,48,96);
    }
    if(m_shooter.mode_amfi == false){
      m_led.onecolor(255, 255, 0, 0, 48);
    }
    if(m_shooter.mode_amfi == true){
      m_led.onecolor(0, 255, 0, 0, 48);
    }
    final double stickY = -m_stick.getY();
    final double stickX = -m_stick.getRawAxis(2); 
    if(m_stick.getRawButton(4) == false){
      if(m_stick.getRawButton(7) == true){}
          m_drive.arcadeDrive((stickY/2), (stickX/2));
      if (m_stick.getRawButton(7) == false){
          m_drive.arcadeDrive(stickY,stickX);}}
    if(m_stick.getRawButton(4) == true){
      m_drive.arcadeDrive(stickY,m_Limelight.Turn_pid.calculate(m_Limelight.getX(), 0));}    
    m_climb.setMotor(m_stick.getPOV());
    m_shooter.setMotor(m_stick.getRawButton(5),m_stick.getRawButton(6),m_stick.getRawButtonReleased(5),m_stick.getRawButtonReleased(6),autonomous_shooter
    ,m_stick.getRawButtonPressed(2));
  }
  public void autonomousInit() {
    m_timer.start();
    m_stage = 1;
  }
  public void autonomousPeriodic() {
    SmartDashboard.putNumber("autonomous timer", m_timer.get());
    SmartDashboard.putNumber("autonomous stage", m_stage);
    SmartDashboard.putNumber("sure: ", DriverStation.getMatchTime());
    SmartDashboard.putBoolean("autonomous shooter", autonomous_shooter);
    SmartDashboard.putBoolean("autonomous stopShooter", autonomous_stopShooter);
    m_shooter.setMotor(autonomous_shooter, false, autonomous_stopShooter,false,autonomous_shooter,false);
    switch(m_stage){
      case 1:
        if(m_timer.get() < 1){
          autonomous_shooter = true;
        }
        if((m_timer.get() < 2) && (m_timer.get() > 1)){
          autonomous_shooter = false;
          autonomous_stopShooter = true;
        }
        if(m_timer.get() > 2){
          autonomous_shooter = false;
          autonomous_stopShooter = false;
          m_stage++;
          m_timer.restart();
        }
        break;
      case 2:
        m_drive.arcadeDrive(-1,0);
        if(m_timer.get() > 1.2){
          m_drive.arcadeDrive(0,0);
          m_stage++;
          m_timer.reset();
          m_timer.stop();
        }
        break;
    }

  }
}
