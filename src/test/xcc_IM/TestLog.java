package xcc_IM;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TestLog {

	private static final Log log = LogFactory.getLog(TestLog.class);
	
	public static void main(String[] args) {
		
		log.debug("---debug---");
		log.info("---info---");
		log.warn("---warn---");
		log.error("---error---");
		
	}
}
