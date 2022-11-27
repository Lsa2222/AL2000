package fc;

import java.util.TimerTask;

public class MaTask extends TimerTask {
	public MaTask(LocationQR loc) {
		this.loc = loc;
	}
	LocationQR loc;
	@Override
  public void run() {
	//bd del loc
	//suprimer acces a l'url sur le site internet
    System.out.println("<----------timer location qr---------->");
    //
  }
}