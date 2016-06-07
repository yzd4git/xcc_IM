package com.zdh.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EnvConfigReader {
	private static final Log log = LogFactory.getLog(DateJsonValueProcessor.class);
	private static String ENV_FILE = "envconfig.properties";

	private File envfile = null;

	private long lastModifiedTime = 0L;

	private Properties envprops = null;

	private static EnvConfigReader instance = new EnvConfigReader();

	private EnvConfigReader() {
		try {
			this.lastModifiedTime = getFile().lastModified();
			if (this.lastModifiedTime == 0L) {
				System.err
						.println("### " + ENV_FILE + " file does not exist! ");
			}
			this.envprops = new Properties();
			this.envprops.load(new FileInputStream(getFile()));
		} catch (URISyntaxException e) {
			log.error("### " + ENV_FILE + " path is incorrect ,,, ");
			e.printStackTrace();
		} catch (Exception e) {
			log.error("### " + ENV_FILE + " read exception ,,, ");
			e.printStackTrace();
		}
	}

	public static EnvConfigReader getInstance() {
		return instance;
	}

	private File getFile() throws URISyntaxException {
		URI fileUri = getClass().getClassLoader().getResource(ENV_FILE).toURI();
		this.envfile = new File(fileUri);
		return this.envfile;
	}

	public String getConfigItem(String name) {
		checkEnvFile();
		String val = this.envprops.getProperty(name);
		if (val == null) {
			return "";
		}
		return val;
	}

	public Collection<String> getConfigItems(String name) {
		checkEnvFile();
		List items = new ArrayList();
		String val = this.envprops.getProperty(name);
		if (val != null) {
			items.addAll(Arrays.asList(val.split(",")));
		}
		return items;
	}

	public void checkEnvFile() {
		long newTime = this.envfile.lastModified();
		if (newTime == 0L) {
			if (this.lastModifiedTime == 0L)
				log.error("### " + ENV_FILE + " file does not exist! ");
			else
				log.error("### " + ENV_FILE + " file is deleted! ");
		} else if (newTime > this.lastModifiedTime) {
			this.envprops.clear();
			try {
				this.envprops.load(new FileInputStream(getFile()));
			} catch (Exception e) {
				log.error("### " + ENV_FILE + " reread exception !! ");
				e.printStackTrace();
			}
		}
		this.lastModifiedTime = newTime;
	}

	public static void main(String[] args) {
		log.error(getInstance().getConfigItem("uploadFilePath"));
		log.error(getInstance().getConfigItems("uploadFilePath"));
	}
}