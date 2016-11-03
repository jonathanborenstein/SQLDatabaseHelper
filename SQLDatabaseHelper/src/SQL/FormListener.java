package SQL;

import java.sql.SQLException;
import java.util.EventListener;

public interface FormListener extends EventListener {
	public void formEventOccurred(FormEvent e);
}
