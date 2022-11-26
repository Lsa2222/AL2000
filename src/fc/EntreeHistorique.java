package fc;
import java.util.Date;
public class EntreeHistorique {
	Personne p;
	Film f;
	Date debut;
	Date fin;
	int support;
	public Object getBRId;
	public int getFilmId() {
		// TODO Auto-generated method stub
		return f.getId();
	}
	public Date getDate() {
		// TODO Auto-generated method stub
		return debut;
	}
	public int getBRId() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getPersonneId() {
		// TODO Auto-generated method stub
		return p.getId();
	}
}