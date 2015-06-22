package Models;

public class Configurazione {
	
	private Integer fPos=15;
	private Integer fSms=10000;
	private boolean smsEnabled=false;
	private boolean emailEnabled=false;
	private Integer speedAlarm=90;
	
	public Configurazione(){
		
	}

	public Integer getfPos() {
		return fPos;
	}

	public void setfPos(Integer fPos) {
		this.fPos = fPos;
	}

	public Integer getfSms() {
		return fSms;
	}

	public void setfSms(Integer fSms) {
		this.fSms = fSms;
	}

	public boolean isSmsEnabled() {
		return smsEnabled;
	}

	public void setSmsEnabled(boolean smsEnabled) {
		this.smsEnabled = smsEnabled;
	}

	public boolean isEmailEnabled() {
		return emailEnabled;
	}

	public void setEmailEnabled(boolean emailEnabled) {
		this.emailEnabled = emailEnabled;
	}

	public Integer getspeedAlarm() {
		return speedAlarm;
	}

	public void setspeedAlarm(Integer speed) {
		this.speedAlarm = speed;
	}

	public void setAll(int fPos, int fSms, int speed, boolean smsEnabled,
			boolean emailEnabled) {
		this.setfPos(fPos);
		this.setfSms(fSms);
		this.setspeedAlarm(speed);
		this.setSmsEnabled(smsEnabled);
		this.setEmailEnabled(emailEnabled);		
	}
	public String toString(){
		return "FreqPos: "+fPos+"\nFreqNotifiche: "+fSms+"\nE-mail enabled: "+emailEnabled+"\nSms enabled: "+smsEnabled+"\nSpeedAlarm: "+speedAlarm;
	}
}
