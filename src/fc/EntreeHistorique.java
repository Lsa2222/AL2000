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
		return f.getId();
	}
	public Date getDate() {
		return debut;
	}
	public int getBRId() {
		return 0;
	}
	public int getPersonneId() {
		// TODO Auto-generated method stub
		return 0;
	}
}